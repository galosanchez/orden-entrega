package com.galosanchez.controller;

import com.galosanchez.model.Cliente;
import com.galosanchez.model.DetalleOrden;
import com.galosanchez.model.OrdenEntrega;
import com.galosanchez.model.OrdenEntregaModel;
import com.galosanchez.model.Producto;
import com.galosanchez.mymethods.Fechas;
import com.galosanchez.mymethods.ImprimirCadena;
import com.galosanchez.mymethods.LeerDatos;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Galo Sánchez
 */
public class OrdenEntregaController {

    // Pide el numero de orden para imprimir los detalles
    public static void buscarOrden() {
        int numOrden = 0;
        // Validar número de orden
        while (numOrden < 1) {
            ImprimirCadena.pedirDato("Número de orden");
            numOrden = LeerDatos.datoIntMayorCero();
            if (numOrden < 1) {
                ImprimirCadena.errorDato("Ingrese el número de orden");
            }
        }
        OrdenEntrega ordenEntrega;
        ordenEntrega = OrdenEntregaModel.buscarNumero(numOrden);
        if (ordenEntrega != null) {
            infoOrdenEntrega(ordenEntrega);
        } else {
            ImprimirCadena.aviso("No se encontro resultados");
        }
    }

    //Pedir datos para crear nueva orden
    public static void nuevaOrden() {
        OrdenEntrega ordenEntrega;
        ImprimirCadena.tituloOrdenEntrega("Nueva Orden");
        ImprimirCadena.itemTituloCliente("Lista de clientes");
        // Imprime lista de clientes
        ClienteController.clientes();
        // Buscar cliente
        String cedula = null;
        Cliente cliente = null;
        while (cedula == null && cliente == null) {
            ImprimirCadena.pedirDato("Cédula del cliente");
            cedula = LeerDatos.datoCadena();
            if (cedula == null) {
                ImprimirCadena.errorDato("Ingrese la cédula");
            } else {
                cliente = ClienteController.obtenerCliente(cedula);
                if (cliente == null) {
                    ImprimirCadena.aviso("No existe cliente");
                    cedula = null;
                }
            }
        }
        ArrayList<DetalleOrden> listaDetalle = new ArrayList<>();
        char op;
        // Imprime lista de productos disponibles
        ProductoController.listarProductos();
        String opString;
        // Agregar productos a lista de Detalles de orden
        do {
            op = '"';
            // Agregar Detalle de orden |producto,cantidad|
            DetalleOrden detalleOrden = nuevoDetalleOrden(listaDetalle);
            listaDetalle.add(detalleOrden);
            while (op == '"') {
                ImprimirCadena.pedirOpcion("Ingresar otro producto? (S/N)");
                op = LeerDatos.datoOpcionSN();
                if (op == '"') {
                    ImprimirCadena.errorDato("Opción no reconocida");
                }
            }
            opString = (String.valueOf(op)).toUpperCase();

        } while ("S".equals(opString));
        
        //Obtener fecha actual
        Date fechaEmision = new Date();
        
        ordenEntrega = new OrdenEntrega(cliente, 'P', fechaEmision, null);
        ordenEntrega.setDetallesOrden(listaDetalle);
        // Envia la nueva orden entrega para agregar
        OrdenEntregaModel.agregarAlista(ordenEntrega);

        // Imprimir información ingresada anteriormente
        System.out.println("\n**************************FACTURA*****************************");
        System.out.println("Factura #" + ordenEntrega.getNumOrden());
        System.out.println("Nombre: " + ordenEntrega.getCliente().getNombre());
        System.out.println("CI: " + ordenEntrega.getCliente().getCedula());
        System.out.println("Fecha: " + ordenEntrega.getFechaEmision());
        System.out.println("Dirección: " + ordenEntrega.getCliente().getDireccion());
        System.out.println("****************************************************************");
        ProductoController.productosFactura(ordenEntrega.getDetallesOrden());
        System.out.println("----------------");
        System.out.printf("%s%s\n", "TOTAL = ", sumaFactura(ordenEntrega.getDetallesOrden()));
        System.out.println("----------------");
    }

    // Devuelve suma total del precio por la cantidad del producto
    private static String sumaFactura(ArrayList<DetalleOrden> lista) {
        DecimalFormat formato = new DecimalFormat("#.00");
        float suma = 0;
        for (DetalleOrden detalleOrden : lista) {
            suma += (detalleOrden.getCantidad() * detalleOrden.getProducto().getPrecio());
        }
        return formato.format(suma);
    }

    // Devuelve detalle datos al pedir el producto y cantidad
    private static DetalleOrden nuevoDetalleOrden(ArrayList<DetalleOrden> listaDetalle) {
        DetalleOrden detalleOrden;
        String codigo = null;
        Producto producto = null;
        int cantidad = 0;

        // Buscar producto
        while (codigo == null && producto == null) {
            ImprimirCadena.pedirDato("Código de producto");
            codigo = LeerDatos.datoCadena();
            if (codigo == null) {
                ImprimirCadena.errorDato("Ingrese un código");
                continue;
            }

            boolean existe = false;
            for (DetalleOrden detalle : listaDetalle) {
                if (codigo.equals(detalle.getProducto().getCodigo())) {
                    existe = true;
                    break;
                }
            }
            
            // Validar producto ya esta en lista de orden entrega actual
            if (existe) {
                ImprimirCadena.aviso("Este producto ya esta en lista");
                codigo = null;
                continue;
            }
            
            producto = ProductoController.obtenerProductoCodigo(codigo);
            if (producto == null) {
                ImprimirCadena.aviso("No existe producto");
                codigo = null;
            }

        }

        while (cantidad < 1) {
            ImprimirCadena.pedirDato("Cantidad");
            cantidad = LeerDatos.datoIntMayorCero();
            // Valida que la cantidad que se pide no sea mayor al stock del producto
            if (cantidad < 1) {
                ImprimirCadena.errorDato("Ingrese la cantidad");
            } else if (cantidad > producto.getCantidad()) {
                ImprimirCadena.errorDato("Solo existe " + producto.getCantidad() + " productos en stock");
                cantidad = 0;
            }
        }
        detalleOrden = new DetalleOrden(producto, cantidad);
        return detalleOrden;
    }

    // Imprime lista Ordenes de entrega
    public static void listarOrdenEntrega() {
        ArrayList<OrdenEntrega> lista = OrdenEntregaModel.listadoOrdenes();
        if (lista.size() > 0) {
            ImprimirCadena.itemTitulo("Lista de ordenes");
            System.out.printf("%8s%25s%12s%18s%18s%14s\n",
                    "#", "Cliente", "C.I.", "Fecha emisión", "Fecha concreto", "Estado");
            lista.forEach(ordenEntrega -> {
                System.out.printf("%8s%25s%12s%18s%18s%14s\n",
                        ordenEntrega.getNumOrden(),
                        ordenEntrega.getCliente().getNombre(),
                        ordenEntrega.getCliente().getCedula(),
                        Fechas.formatoDMA(ordenEntrega.getFechaEmision()),
                        Fechas.formatoDMA(ordenEntrega.getFechaConcreta()),
                        tipoEstado(ordenEntrega.getEstado())
                );
            });
        } else {
            ImprimirCadena.aviso("No se encontraron resultados");
        }
    }

    // Devuelve el tipo de estado dependiendo el caracter de estado
    public static String tipoEstado(char estado) {
        if (estado == 'R') {
            return "RECHAZADA";
        }
        if (estado == 'C') {
            return "CONCLUIDA";
        }
        return "PENDIENTE";
    }

    // Imprime información de la ordentrega con lista de detalle
    private static void infoOrdenEntrega(OrdenEntrega ordenEntrega) {
        ImprimirCadena.itemTitulo("Detalle de la orden");
        System.out.printf("%8s%25s%12s%18s%18s%14s\n",
                "#", "Cliente", "C.I.", "Fecha emisión", "Fecha concreto", "Estado");
        System.out.printf("%8s%25s%12s%18s%18s%14s\n\n",
                ordenEntrega.getNumOrden(),
                ordenEntrega.getCliente().getNombre(),
                ordenEntrega.getCliente().getCedula(),
                Fechas.formatoDMA(ordenEntrega.getFechaEmision()),
                Fechas.formatoDMA(ordenEntrega.getFechaConcreta()),
                tipoEstado(ordenEntrega.getEstado())
        );
        ProductoController.productosFactura(ordenEntrega.getDetallesOrden());
        System.out.println("----------------");
        System.out.printf("%s%s\n", "TOTAL = ", sumaFactura(ordenEntrega.getDetallesOrden()));
        System.out.println("----------------");
    }

    // Imprime información de orden entrega sin lista de detalle
    public static void descipcionOrden(ArrayList<OrdenEntrega> listaOrdenEntrega) {
        System.out.printf("%8s%18s%18s%14s%14s\n",
                "#", "Fecha emisión", "Fecha concreto", "Estado", "Val. Pagar");
        for (OrdenEntrega ordenEntrega : listaOrdenEntrega) {
            System.out.printf("%8s%18s%18s%14s%14s\n",
                    ordenEntrega.getNumOrden(),
                    Fechas.formatoDMA(ordenEntrega.getFechaEmision()),
                    Fechas.formatoDMA(ordenEntrega.getFechaConcreta()),
                    tipoEstado(ordenEntrega.getEstado()),
                    sumaFactura(ordenEntrega.getDetallesOrden())
            );
        }
    }

    // Pide el número de orden para confirmar la orden
    public static void confirmarOrden() {
        // Verifica que exista al menos una orden
        if (!OrdenEntregaModel.existeOrdenes()) {
            ImprimirCadena.aviso("No existe ninguna orden entrega");
            return;
        }
        
        // Validar número de orden
        ImprimirCadena.itemTitulo("Confirmar orden");
        int numOrden = 0;
        while (numOrden < 1) {
            ImprimirCadena.pedirDato("Número de orden");
            numOrden = LeerDatos.datoIntMayorCero();
            if (numOrden < 1) {
                ImprimirCadena.errorDato("Ingrese el número de orden");
            }
        }
        
        OrdenEntrega ordenEntrega;
        ordenEntrega = OrdenEntregaModel.buscarNumero(numOrden);
        if (ordenEntrega != null) {
            char estado = ordenEntrega.getEstado();
            // Validar estado de la orden
            switch (estado) {
                case 'R':
                    ImprimirCadena.aviso("Esta orden está rechazada");
                    break;
                case 'C':
                    ImprimirCadena.aviso("Esta orde ya fué confirmada");
                    break;
                case 'P':
                    // Actualizar datos
                    OrdenEntregaModel.confirmar(ordenEntrega);
                    ImprimirCadena.completado("Orden confirmada");
                    break;
                default:
                    ImprimirCadena.aviso("A ocurrido un error con la orden");
                    break;
            }
        } else {
            ImprimirCadena.aviso("No se encontro resultados");
        }
    }

    // Pide el número de orden para calcelar la orden
    public static void cancelarOrden() {
        // Verifica que exista al menos una orden
        if (!OrdenEntregaModel.existeOrdenes()) {
            ImprimirCadena.aviso("No existe ninguna orden entrega");
            return;
        }
        
        // Validar número de orden
        ImprimirCadena.itemTitulo("Cancelar orden");
        int numOrden = 0;
        while (numOrden < 1) {
            ImprimirCadena.pedirDato("Número de orden");
            numOrden = LeerDatos.datoIntMayorCero();
            if (numOrden < 1) {
                ImprimirCadena.errorDato("Ingrese el número de orden");
            }
        }
        OrdenEntrega ordenEntrega;
        ordenEntrega = OrdenEntregaModel.buscarNumero(numOrden);
        if (ordenEntrega != null) {
            char estado = ordenEntrega.getEstado();
            // Validar estado de la orden
            switch (estado) {
                case 'R':
                    ImprimirCadena.aviso("Esta orde ya fué cancelada");
                    break;
                case 'C':
                    ImprimirCadena.aviso("Esta orden está confirmada");
                    break;
                case 'P':
                    // Actualizar datos
                    OrdenEntregaModel.cancelar(ordenEntrega);
                    ImprimirCadena.completado("Orden cancelada");
                    break;
                default:
                    ImprimirCadena.aviso("A ocurrido un error con la orden");
                    break;
            }
        } else {
            ImprimirCadena.aviso("No se encontro resultados");
        }
    }

}
