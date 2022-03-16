package com.galosanchez.model;

import com.galosanchez.data.Datos;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Galo Sánchez
 */
public class OrdenEntregaModel {

    // Aguregar producto a lista de Datos
    public static void agregarAlista(OrdenEntrega ordenEntrega) {
        Datos.listaOrdenEntrega.add(ordenEntrega);
        ArrayList<DetalleOrden> listadetalle = ordenEntrega.getDetallesOrden();
        for (DetalleOrden detalle : listadetalle) {
            Producto producto;
            producto = ProductoModel.buscarCodigo(detalle.getProducto().getCodigo());
            int cantidad = producto.getCantidad();
            producto.setCantidad(cantidad - detalle.getCantidad());
        }

    }

    // Devuelve toda la lista de Ordenes
    public static ArrayList<OrdenEntrega> listadoOrdenes() {
        return Datos.listaOrdenEntrega;
    }

    // Devuelve una orden entrega con la busqueda del nnúmro de orden
    public static OrdenEntrega buscarNumero(int numOrden) {
        for (OrdenEntrega oe : Datos.listaOrdenEntrega) {
            if (oe.getNumOrden() == numOrden) {
                return oe;
            }
        }
        return null;
    }

    // Guardar datos de confirmar una orden
    public static void confirmar(OrdenEntrega ordenEntrega) {
        Date fechaConcreta = new Date();
        ordenEntrega.setEstado('C');
        ordenEntrega.setFechaConcreta(fechaConcreta);
    }

    // Guardar datos de cancelar una orden
    public static void cancelar(OrdenEntrega ordenEntrega) {
        Date fechaConcreta = new Date();
        ordenEntrega.setEstado('R');
        ordenEntrega.setFechaConcreta(fechaConcreta);
    }

    // Verifical que al menos existea una orden
    public static boolean existeOrdenes() {
        if (Datos.listaOrdenEntrega.size() > 0) {
            return true;
        }
        return false;
    }

    // Devuelve lista de ordenes que coincidan con la cédula del cliente recibe
    public static ArrayList<OrdenEntrega> ordenesCliente(String cedula) {
        ArrayList<OrdenEntrega> lista = new ArrayList();
        for (OrdenEntrega ordenEntrega : Datos.listaOrdenEntrega) {
            if (ordenEntrega.getCliente().getCedula().equals(cedula)) {
                lista.add(ordenEntrega);
            }
        }
        return lista;
    }

    // Devuerve lista de ordenes dependiendo del estado que reciba (P,R,C)
    private static ArrayList<OrdenEntrega> listadoOrdenesEstado(char estado) {
        ArrayList<OrdenEntrega> listadetalle = new ArrayList<>();
        for (OrdenEntrega ordenEntrega : Datos.listaOrdenEntrega) {
            if (ordenEntrega.getEstado() == estado) {
                listadetalle.add(ordenEntrega);
            }
        }
        return listadetalle;
    }

    // Devuerve lista de ordenes con estado P = Pendiente
    public static ArrayList<OrdenEntrega> listadoOrdenesPendientes() {
        return listadoOrdenesEstado('P');
    }

    // Devuerve lista de ordenes con estado C = Concluido
    public static ArrayList<OrdenEntrega> listadoOrdenesCompletadas() {
        return listadoOrdenesEstado('C');
    }

    // Devuerve lista de ordenes con estado R = Rechazado
    public static ArrayList<OrdenEntrega> listadoOrdenesCanceladas() {
        return listadoOrdenesEstado('R');
    }

}
