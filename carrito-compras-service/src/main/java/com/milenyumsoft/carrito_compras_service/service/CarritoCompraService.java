package com.milenyumsoft.carrito_compras_service.service;

import com.milenyumsoft.carrito_compras_service.modelo.CarritoCompra;
import com.milenyumsoft.carrito_compras_service.repository.ICarritoCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarritoCompraService implements ICarritoCompraService {

    @Autowired
    private ICarritoCompraRepository carritoCompraRepo;


    @Override
    public String crearCarritoCompra(CarritoCompra carritoCompra) {
        carritoCompraRepo.save(carritoCompra);

        return "Carrito creada correctamente.";
    }

    @Override
    public String eliminarCarritoCompra(Long idCarritoCompra) {

        carritoCompraRepo.deleteById(idCarritoCompra);
        return "Carrito eliminado exitosamente.";
    }

    @Override
    public String anadirCarritoCompraProducto(Long idCarritoCompra, Long idProducto) {

        //1.- Válidar que id existe




        //2.-Válidar que existe el producto con su Id caso contraro no existe el producto


        //3.-Añadir producto a lista de carrito


        //4.- Retornar confirmación de producto añadido exitosamente





        return null;
    }

    @Override
    public String eliminarCarritoCompraProducto(Long idCarritoCompra, Long idProducto) {


        //1.- Válidar que id existe


        //2.-Válidar que existe el producto con su Id caso contraro no existe el producto para eliminar


        //3.-Añadir producto a lista de carrito


        //4.- Retornar confirmación de producto añadido exitosamente


        return null;
    }
}
