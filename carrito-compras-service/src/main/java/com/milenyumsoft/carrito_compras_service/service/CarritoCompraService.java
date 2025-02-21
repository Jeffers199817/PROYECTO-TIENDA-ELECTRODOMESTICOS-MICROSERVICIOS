package com.milenyumsoft.carrito_compras_service.service;

import com.milenyumsoft.carrito_compras_service.dto.CarritoCompraDTO;
import com.milenyumsoft.carrito_compras_service.dto.ProductoDTO;
import com.milenyumsoft.carrito_compras_service.dto.VentaDTO;
import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;
import com.milenyumsoft.carrito_compras_service.repository.ICarritoCompraRepository;
import com.milenyumsoft.carrito_compras_service.repository.IProductoRepository;
import com.milenyumsoft.carrito_compras_service.repository.IVentaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CarritoCompraService implements ICarritoCompraService {

    @Autowired
    private ICarritoCompraRepository carritoCompraRepo;

    @Autowired
    private IProductoRepository productoRepo;

    @Autowired
    private IVentaRepository ventaRepo;


    @Override
    public String crearCarritoCompra(CarritoCompra carritoCompra) {
        carritoCompraRepo.save(carritoCompra);
        System.out.println( "número de carrito" + carritoCompra.getIdCarritoCompra());

        return "Carrito creada correctamente. " + carritoCompra.getIdCarritoCompra() ;
    }

    @Override
    public String eliminarCarritoCompra(Long idCarritoCompra) {
        VentaDTO ventaDTO1=null;
        try {
            ventaDTO1 = ventaRepo.traerVenta(idCarritoCompra);
            System.out.println(ventaDTO1.toString());
            if(ventaDTO1!=null && ventaDTO1.isVentaRealizadoPagado()){
                return "No se puede añadir productos al carrito, la venta ha sido pagada y finalizada.";
            }
        }catch (Exception e){
            System.out.println("Erro al obtener el resultado de pago");
            return "Error al obtener información de pago, no existen productos en el carrito.";
        }


        carritoCompraRepo.deleteById(idCarritoCompra);
        return "Carrito eliminado exitosamente.";
    }

    @Override
    @CircuitBreaker(name="producto-service", fallbackMethod = "fallbackGetProductoService")
    @Retry(name="producto-service")
    public CarritoCompraDTO traerCarrito(Long idCarritoCompra) {

        CarritoCompra carritoCompra = null;
        try {
            carritoCompra = carritoCompraRepo.buscarCarritoCompraById(idCarritoCompra);
            if (carritoCompra == null) {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el carrito");
            return null;
        }

        //1.- Traer el carrito de compra


        //2.- Crear un lista de IdProducto tipo Long
        List<Long> listaDeId = carritoCompra.getListaIdProductos();

        //3.- Inicializar una lista de productosDTO
        List<ProductoDTO> listaProducto = new ArrayList<>();

        //4.- Iterar la lista y agregar los producto a lista de productos.
        for( Long idProducto: listaDeId){
            listaProducto.add(productoRepo.traerProducto(idProducto));
        }

        //5.- Integrar todo en el objeto CarritoCompraDTO

        CarritoCompraDTO carritoCompraDTO = new CarritoCompraDTO();
        carritoCompraDTO.setIdCarritoCompra(carritoCompra.getIdCarritoCompra());
        carritoCompraDTO.setSumaTotal(carritoCompra.getSumaTotal());
        carritoCompraDTO.setListaProductosDTO(listaProducto);

        //6. Retornoar el carritoCompraDTO

        //createException();
        return  carritoCompraDTO ;
    }

    @Override
    public List<CarritoCompraDTO> traerListaCarriot() {




        // 1.- Traer la lista de Carritos
        List<CarritoCompra> listaCarrito = carritoCompraRepo.findAll();
        List<CarritoCompraDTO> listaCarritoCompraDTO = new ArrayList();

        // 2.- Iterar la lista de carritos para agregar a lista de carritos los productos

        for(CarritoCompra carritoCompra: listaCarrito){

            // 3.- Lleno la lista con los ID de productos y Inicializo una lista de Productos
            List<Long> listaIdCarrito = carritoCompra.getListaIdProductos();
            List<ProductoDTO> listaProductos= new ArrayList<>();
            CarritoCompraDTO carritoCompraDTO = new CarritoCompraDTO();

            // 4.- Itero la lista de productos
            for(Long idProducto: listaIdCarrito){
                listaProductos.add(productoRepo.traerProducto(idProducto));
            }

            carritoCompraDTO.setIdCarritoCompra(carritoCompra.getIdCarritoCompra());
            carritoCompraDTO.setSumaTotal(carritoCompra.getSumaTotal());
            carritoCompraDTO.setListaProductosDTO(listaProductos);

            listaCarritoCompraDTO.add(carritoCompraDTO);
        }

        return listaCarritoCompraDTO;
    }


    @Autowired
    private EntityManager entityManager;

    @Override
    public String anadirCarritoCompraProducto(Long idCarritoCompra, Long idProducto) {


// 1. Validar que el carrito existe


        CarritoCompra carritoC = null;
        try {
            carritoC = carritoCompraRepo.buscarCarritoCompraById(idCarritoCompra);
            if (carritoC==null) {
                return "No existe carrito de compras.";
            }
        } catch (Exception e) {
            System.out.println("Error al obtener el carrito");
            return "Error al obtener el carrito, inténtelo de nuevo mas tarde";
        }

        VentaDTO ventaDTO1=null;
        try {
            ventaDTO1 = ventaRepo.traerVenta(idCarritoCompra);
            System.out.println(ventaDTO1.toString());
            if(ventaDTO1!=null && ventaDTO1.isVentaRealizadoPagado()){
                return "No se puede añadir productos al carrito, la venta ha sido pagada y finalizada.";
            }
        }catch (Exception e){
            System.out.println("Erro al obtener el resultado de pago");
            return "Error al obtener información de pago, no existen productos en el carrito.";
        }




        // 2. Validar que el producto existe
        ProductoDTO producto =null;
        try {
            producto = productoRepo.traerProducto(idProducto);
            if (producto == null) {
                return "No existe producto, vuelva a intentarlo.";
            }
        }catch (Exception e){
            System.out.println("Error al obtener el producto:" + e.getMessage());
            return "Error al obtener el producto, inténtelo de nuevo más tarde.";
        }


        // 3. Añadir el producto a la lista de productos del carrito


        // Verificar si el producto ya está en la lista
        if (carritoC.getListaIdProductos().contains(producto.getIdProducto())) {
            System.out.println("Estoy aqui 1" );


            return "El producto ya está en el carrito.";
        }

        boolean esPrimerProducto = carritoC.getListaIdProductos().isEmpty();

        System.out.println("estoy aqui 2");
        carritoC.getListaIdProductos().add(producto.getIdProducto());
        System.out.println("estoy aqui 3");
        // 4. Actualizar la suma total del carrito
        carritoC.setSumaTotal(carritoC.getSumaTotal() + producto.getPrecioUnitarioProducto());

        System.out.println("estoy aqui 4");
        // 5. Guardar los cambios en la base de datos
        carritoCompraRepo.save(carritoC);

        //5.5  Crear una venta. en otro microservico si solo si lista de producto esta vacia si tiene mas de uno ya no funciona.
        if(esPrimerProducto) {
            VentaDTO ventaDTO = new VentaDTO();
            ventaDTO.setVentaRealizadoPagado(false);
            ventaDTO.setIdCarritoCompra(carritoC.getIdCarritoCompra());
            ventaRepo.crearVenta(ventaDTO);
        }

        // 6. Retornar confirmación de producto añadido exitosamente
        return "Producto: " + producto.getNombreProducto() + " añadido exitosamente al carrito.";

    }

    @Override
    public String eliminarCarritoCompraProducto(Long idCarritoCompra, Long idProducto) {



        // 1. Validar que el carrito existe
        CarritoCompra carritoC = null;
        try {
            carritoC = carritoCompraRepo.buscarCarritoCompraById(idCarritoCompra);
            if (carritoC == null) {
                return "No existe carrito de compras.";
            }
        }catch (Exception e){
            System.out.println("Error no existe el carriot" + e.getMessage());
            return "No se encuentra el carrito, intente de nuevo.";
        }


        VentaDTO ventaDTO1=null;
        try {
            ventaDTO1 = ventaRepo.traerVenta(idCarritoCompra);
            System.out.println(ventaDTO1.toString());
            if(ventaDTO1!=null && ventaDTO1.isVentaRealizadoPagado()){
                return "No se puede eliminar productos al carrito, la venta ha sido pagada y finalizada.";
            }
        }catch (Exception e){
            System.out.println("Erro al obtener el resultado de pago");
            return "Error al obtener información de pago, no existen productos en el carrito.";
        }




        // 2.- Obtener la lista d IDs de productos
        List<Long> listaIdProducto= carritoC.getListaIdProductos();

        // 3.- Usar un Iterator para aliminar el producto de manera segura
        Iterator<Long> iterator = listaIdProducto.iterator();
        boolean productoEliminado = false;

        while(iterator.hasNext()){
            Long idProductos = iterator.next();
            if(idProductos.equals(idProducto)) {
                iterator.remove();
                productoEliminado = true;
                break;
            }
        }

        // 4. Verificar si el producto fue eliminado
        if (!productoEliminado) {
            System.out.println("No existe producto en el carrito.");
            return "No existe producto en el carrito.";
        }

        //4. Actualizar la lista de productos en el carrito
        carritoC.setListaIdProductos(listaIdProducto);

        //5. Obtener el producto eliminado
        ProductoDTO productoDTO=  productoRepo.traerProducto(idProducto);


        // 7. Verificar si el carrito quedó vacío después de eliminar el producto
        if (listaIdProducto.isEmpty()) {
            // 8. Eliminar la venta asociada al carrito
            try {
                ventaRepo.deleteVenta(carritoC.getIdCarritoCompra());
                System.out.println("Venta eliminada porque el carrito quedó vacío.");
            } catch (Exception e) {
                System.out.println("Error al eliminar la venta: " + e.getMessage());
                return "Error al eliminar la venta, inténtelo de nuevo más tarde.";
            }
        }

        //6. Guardar el carrtio  actulizaiond en la base de datos
        carritoCompraRepo.save(carritoC);

        //7. Retornar confirmación de producto eliminado exitosamente
        return "Producto: " + productoDTO.getNombreProducto()+ " eliminado exitosamente del carrito.";
    }


public CarritoCompraDTO fallbackGetProductoService(Throwable throwable){

        return new CarritoCompraDTO(  9999999L, 0.0,null);
}

public void createException(){

        throw new IllegalArgumentException("Prueba Resilience y Circuit Breaker");
}

}
