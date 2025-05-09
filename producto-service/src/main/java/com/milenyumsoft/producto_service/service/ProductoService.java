package com.milenyumsoft.producto_service.service;

import com.milenyumsoft.producto_service.controller.ProductoController;
import com.milenyumsoft.producto_service.modelo.Producto;
import com.milenyumsoft.producto_service.repository.IProductoRepository;
import org.antlr.v4.runtime.atn.SemanticContext;
import org.apache.catalina.valves.rewrite.InternalRewriteMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository productoRepo;



    @Override
    public List<Producto> findAll() {
        return productoRepo.findAll();
    }

    @Override
    public Producto getProductoById(Long idProducto) {

        return productoRepo.findById(idProducto).orElse(null);

    }


    @Override
    public String crearProducto(Producto producto) {
        // Buscar productos con el mismo código o nombre
        List<Producto> productosRepetidos = productoRepo.traerDatosNombreCodigoProducto(
                producto.getCodigoProducto(),
                producto.getNombreProducto()
        );

        System.out.println(producto.getCodigoProducto());
        System.out.println(producto.getNombreProducto());

        // Verificar si ya existe un producto con el mismo código o nombre
        for (Producto productoExistente : productosRepetidos) {
            if (productoExistente.getCodigoProducto().equals(producto.getCodigoProducto())) {
                System.out.println(productoExistente.getCodigoProducto());
                System.out.println();
                return "El código " + producto.getCodigoProducto() + " ya existe. Por favor, use otro código.";
            }
            if (productoExistente.getNombreProducto().equalsIgnoreCase(producto.getNombreProducto())) {
                System.out.println(productoExistente.getNombreProducto());
                return "El producto " + producto.getNombreProducto() + " ya existe.";
            }
        }

        // Si no hay duplicados, guardar el producto
        productoRepo.save(producto);
        return "Producto creado correctamente.";
    }


    @Override
    public String eliminarProducto(Long idProducto) {
        productoRepo.deleteById(idProducto);

        return "Producto eliminado correctamente.";
    }

    public class ProductoDuplicadoException extends RuntimeException {
        public ProductoDuplicadoException(String mensaje) {
            super(mensaje);
        }
    }


    @Override
    public String editProducto(Producto producto) {
        // Obtener el producto existente por su ID
        Producto producAntiguo = this.getProductoById(producto.getIdProducto());
        if (producAntiguo == null) {
            return "Producto no encontrado.";
        }

        // Verificar si el código del producto ha cambiado
        if (!producAntiguo.getCodigoProducto().equals(producto.getCodigoProducto())) {
            // Validar si el nuevo código ya existe
            Optional<Producto> productoConMismoCodigo = productoRepo.traerDatosCodigoProducto(producto.getCodigoProducto());
            System.out.println("Lo que trae productoConMismoCodigo: " + productoConMismoCodigo);
            if (productoConMismoCodigo.isPresent()) {
                return "El código " + producto.getCodigoProducto() + " ya existe. Por favor, use otro código.";
            }
        }

        // Verificar si el nombre del producto ha cambiado
        if (!producAntiguo.getNombreProducto().equalsIgnoreCase(producto.getNombreProducto())) {
            // Validar si el nuevo nombre ya existe
            Optional<Producto> productoConMismoNombre = productoRepo.traerDatosNombreProducto(producto.getNombreProducto());
            System.out.println("Lo que trae productoConMismoNombre: " + productoConMismoNombre);
            if (productoConMismoNombre.isPresent()) {
                return "El producto con el nombre " + producto.getNombreProducto() + " ya existe.";
            }
        }

        // Actualizar los campos del producto
        producAntiguo.setCodigoProducto(producto.getCodigoProducto());
        producAntiguo.setNombreProducto(producto.getNombreProducto());
        producAntiguo.setMarcaProducto(producto.getMarcaProducto());
        producAntiguo.setPrecioUnitarioProducto(producto.getPrecioUnitarioProducto());

        // Guardar el producto actualizado
        productoRepo.save(producAntiguo);

        return "Producto editado exitosamente: " + producAntiguo.toString();
    }

}
