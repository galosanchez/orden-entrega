package com.galosanchez.model;

import com.galosanchez.data.Datos;
import java.util.ArrayList;

/**
 * @author Galo Sánchez
 */
public class ProductoModel {

    // Agregar a la lista de datos
    public static void agregarLista(Producto producto) {
        Datos.listaProductos.add(producto);
    }

    // Busca en la lista de datos por medio del código
    public static Producto buscarCodigo(String codigo) {
        for (Producto p : Datos.listaProductos) {
            if (p.getCodigo().equals(codigo)) {
                return p;
            }
        }
        return null;
    }

    // Elimina producto de lista de Datos
    public static void eliminar(Producto producto) {
        Datos.listaProductos.remove(producto);
    }

    // Comprueba si esxiste un producto por medio del código 
    public static boolean existeProductoCodigo(String codigo) {
        for (Producto p : Datos.listaProductos) {
            if (p.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    }

    // Devuelve toda la listas de productos en Datos
    public static ArrayList<Producto> listadoProductos() {
        return Datos.listaProductos;
    }

    // Actualiza los datos de un producto
    public static void editar(String codigo, Producto productoEdit) {
        Producto producto = buscarCodigo(codigo);
        if (productoEdit.getNombre().length() > 0) {
            producto.setNombre(productoEdit.getNombre());
        }
        if (productoEdit.getCantidad() > -1) {
            producto.setCantidad(productoEdit.getCantidad());
        }
        if (productoEdit.getPrecio() > 0) {
            producto.setPrecio(productoEdit.getPrecio());
        }
    }

}
