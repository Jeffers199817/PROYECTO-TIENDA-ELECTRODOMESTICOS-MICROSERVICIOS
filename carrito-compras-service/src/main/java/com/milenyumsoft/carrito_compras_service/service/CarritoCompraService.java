package com.milenyumsoft.carrito_compras_service.service;

import com.milenyumsoft.carrito_compras_service.dto.ProductoDTO;
import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;
import com.milenyumsoft.carrito_compras_service.modelo.Producto;
import com.milenyumsoft.carrito_compras_service.repository.ICarritoCompraRepository;
import com.milenyumsoft.carrito_compras_service.repository.IProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public String anadirCarritoCompraProducto(Long idCarritoCompra, Long idProducto) {


// 1. Validar que el carrito existe
        Optional<CarritoCompra> carCompra = carritoCompraRepo.buscarCarritoCompraById(idCarritoCompra);
        if (!carCompra.isPresent()) {
            return "No existe carrito de compras.";
        }

        // 2. Validar que el producto existe
        Optional<Producto> productoOptional = productoRepo.traerProducto(idCarritoCompra);
        if (!productoOptional.isPresent()) {
            return "No existe producto, vuelva a intentarlo.";
        }

        // 3. Añadir el producto a la lista de productos del carrito
        CarritoCompra carritoC = carCompra.get();
        Producto producto = productoOptional.get();

        // Verificar si el producto ya está en la lista
        if (carritoC.getListaProductos().contains(producto)) {
            return "El producto ya está en el carrito.";
        }

        carritoC.getListaProductos().add(producto);

        // 4. Actualizar la suma total del carrito
        carritoC.setSumaTotal(carritoC.getSumaTotal() + producto.getPrecioUnitarioProducto());

        // 5. Guardar los cambios en la base de datos
        carritoCompraRepo.save(carritoC);

        // 6. Retornar confirmación de producto añadido exitosamente
        return "Producto añadido exitosamente al carrito.";

    }

    @Override
    public String eliminarCarritoCompraProducto(Long idCarritoCompra, Long idProducto) {

        // 1. Validar que el carrito existe
        Optional<CarritoCompra> carCompra = carritoCompraRepo.buscarCarritoCompraById(idCarritoCompra);
        if (!carCompra.isPresent()) {
            return "No existe carrito de compras.";
        }

        // 2. Validar que el producto existe en el carrito
        CarritoCompra carritoC = carCompra.get();
        Optional<Producto> productoAEliminar = carritoC.getListaProductos().stream()
                .filter(producto -> producto.getIdProducto().equals(idProducto))
                .findFirst();

        if (!productoAEliminar.isPresent()) {
            return "El producto no está en el carrito.";
        }

        // 3. Eliminar el producto de la lista de productos del carrito
        Producto producto = productoAEliminar.get();
        carritoC.getListaProductos().remove(producto);

        // 4. Actualizar la suma total del carrito
        carritoC.setSumaTotal(carritoC.getSumaTotal() - producto.getPrecioUnitarioProducto());

        // 5. Guardar los cambios en la base de datos
        carritoCompraRepo.save(carritoC);

        // 6. Retornar confirmación de producto eliminado exitosamente
        return "Producto eliminado exitosamente del carrito.";
    }




}
