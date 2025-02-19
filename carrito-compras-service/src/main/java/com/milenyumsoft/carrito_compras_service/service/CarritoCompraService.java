package com.milenyumsoft.carrito_compras_service.service;

import com.milenyumsoft.carrito_compras_service.dto.CarritoCompraDTO;
import com.milenyumsoft.carrito_compras_service.dto.ProductoDTO;
import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;
import com.milenyumsoft.carrito_compras_service.repository.ICarritoCompraRepository;
import com.milenyumsoft.carrito_compras_service.repository.IProductoRepository;
import jakarta.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarritoCompraService implements ICarritoCompraService {

    @Autowired
    private ICarritoCompraRepository carritoCompraRepo;

    @Autowired
    private IProductoRepository productoRepo;


    @Override
    public String crearCarritoCompra(CarritoCompra carritoCompra) {
        carritoCompraRepo.save(carritoCompra);
        System.out.println( "número de carrito" + carritoCompra.getIdCarritoCompra());

        return "Carrito creada correctamente. " + carritoCompra.getIdCarritoCompra() ;
    }

    @Override
    public String eliminarCarritoCompra(Long idCarritoCompra) {

        carritoCompraRepo.deleteById(idCarritoCompra);
        return "Carrito eliminado exitosamente.";
    }

    @Override
    public CarritoCompraDTO traerCarrito(Long idCarritoCompra) {

        //1.- Traer el carrito de compra
        CarritoCompra carritoCompra= carritoCompraRepo.buscarCarritoCompraById(idCarritoCompra);

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
        return carritoCompraDTO ;
    }

    @Override
    public List<CarritoCompra> traerListaCarriot() {

        // 1.- Traer la lista de Carritos
        List<CarritoCompra> listaCarrito = carritoCompraRepo.findAll();

        // 2.- Iterar la lista de carritos para agregar a lista de carritos los productos






        return null;
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

        System.out.println("estoy aqui 2");
        carritoC.getListaIdProductos().add(producto.getIdProducto());
        System.out.println("estoy aqui 3");
        // 4. Actualizar la suma total del carrito
        carritoC.setSumaTotal(carritoC.getSumaTotal() + producto.getPrecioUnitarioProducto());

        System.out.println("estoy aqui 4");
        // 5. Guardar los cambios en la base de datos
        carritoCompraRepo.save(carritoC);

        // 6. Retornar confirmación de producto añadido exitosamente
        return "Producto: " + producto.getNombreProducto() + " añadido exitosamente al carrito.";

    }

    @Override
    public String eliminarCarritoCompraProducto(Long idCarritoCompra, Long idProducto) {

        // 1. Validar que el carrito existe
        CarritoCompra carritoC = carritoCompraRepo.buscarCarritoCompraById(idCarritoCompra);
        if (carritoC==null) {
            return "No existe carrito de compras.";
        }

        // 2. Validar que el producto existe en el carrito





        // 3. Eliminar el producto de la lista de productos del carrito


        // 4. Actualizar la suma total del carrito


        // 5. Guardar los cambios en la base de datos


        // 6. Retornar confirmación de producto eliminado exitosamente
        return "Producto eliminado exitosamente del carrito.";
    }




}
