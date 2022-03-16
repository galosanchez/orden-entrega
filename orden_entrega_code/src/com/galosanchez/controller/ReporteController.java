package com.galosanchez.controller;

import com.galosanchez.model.Cliente;
import com.galosanchez.model.ClienteModel;
import com.galosanchez.model.OrdenEntrega;
import com.galosanchez.model.OrdenEntregaModel;
import com.galosanchez.mymethods.Fechas;
import com.galosanchez.mymethods.ImprimirCadena;
import java.util.ArrayList;

/**
 * @author Galo Sánchez
 */
public class ReporteController {

    public static void ordenesPorCliente() {
        if (!OrdenEntregaModel.existeOrdenes()) {
            ImprimirCadena.aviso("No existe ninguna orden entrega");
            return;
        }
        for (Cliente cliente : ClienteModel.listadoClientes()) {
            ArrayList<OrdenEntrega> listaOrdenEntrega = new ArrayList();
            listaOrdenEntrega = OrdenEntregaModel.ordenesCliente(cliente.getCedula());
            if (listaOrdenEntrega.size() > 0) {
                for (int x = 0; x < 100; x++) {
                    System.out.print("-");
                }
                System.out.printf("\n\n%10s%s%s\n%10s%s%s\n%10s%s%s\n",
                        "", "CI: ", cliente.getCedula(),
                        "", "Cliente: ", cliente.getNombre(),
                        "", "Número Ordenes: ", String.valueOf(listaOrdenEntrega.size()));

                OrdenEntregaController.descipcionOrden(listaOrdenEntrega);
                System.out.println("");
            }
        }
        for (int x = 0; x < 100; x++) {
            System.out.print("-");
        }
    }

    public static void ordenesPendientes() {
        if (!OrdenEntregaModel.existeOrdenes()) {
            ImprimirCadena.aviso("No existe ninguna orden entrega");
            return;
        }
        ArrayList<OrdenEntrega> lista = OrdenEntregaModel.listadoOrdenesPendientes();
        if (lista.size() > 0) {
            ImprimirCadena.itemTitulo("Ordenes pendientes");
            System.out.printf("%8s%25s%12s%18s\n",
                    "#", "Cliente", "C.I.", "Fecha emisión");
            lista.forEach(ordenEntrega -> {
                System.out.printf("%8s%25s%12s%18s\n",
                        ordenEntrega.getNumOrden(),
                        ordenEntrega.getCliente().getNombre(),
                        ordenEntrega.getCliente().getCedula(),
                        Fechas.formatoDMA(ordenEntrega.getFechaEmision()),
                        Fechas.formatoDMA(ordenEntrega.getFechaConcreta())
                );
            });
        } else {
            ImprimirCadena.aviso("No se encontraron resultados");
        }
    }

    public static void ordenesCompletadas() {
        if (!OrdenEntregaModel.existeOrdenes()) {
            ImprimirCadena.aviso("No existe ninguna orden entrega");
            return;
        }
        ArrayList<OrdenEntrega> lista = OrdenEntregaModel.listadoOrdenesCompletadas();
        if (lista.size() > 0) {
            ImprimirCadena.itemTitulo("Ordenes completadas");
            System.out.printf("%8s%25s%12s%18s%18s\n",
                    "#", "Cliente", "C.I.", "Fecha emisión", "Fecha concreto");
            lista.forEach(ordenEntrega -> {
                System.out.printf("%8s%25s%12s%18s%18s\n",
                        ordenEntrega.getNumOrden(),
                        ordenEntrega.getCliente().getNombre(),
                        ordenEntrega.getCliente().getCedula(),
                        Fechas.formatoDMA(ordenEntrega.getFechaEmision()),
                        Fechas.formatoDMA(ordenEntrega.getFechaConcreta())
                );
            });
        } else {
            ImprimirCadena.aviso("No se encontraron resultados");
        }
    }

    public static void ordenesCanceladas() {
        if (!OrdenEntregaModel.existeOrdenes()) {
            ImprimirCadena.aviso("No existe ninguna orden entrega");
            return;
        }
        ArrayList<OrdenEntrega> lista = OrdenEntregaModel.listadoOrdenesCanceladas();
        if (lista.size() > 0) {
            ImprimirCadena.itemTitulo("Ordenes canceladas");
            System.out.printf("%8s%25s%12s%18s%18s\n",
                    "#", "Cliente", "C.I.", "Fecha emisión", "Fecha concreto");
            lista.forEach(ordenEntrega -> {
                System.out.printf("%8s%25s%12s%18s%18s\n",
                        ordenEntrega.getNumOrden(),
                        ordenEntrega.getCliente().getNombre(),
                        ordenEntrega.getCliente().getCedula(),
                        Fechas.formatoDMA(ordenEntrega.getFechaEmision()),
                        Fechas.formatoDMA(ordenEntrega.getFechaConcreta())
                );
            });
        } else {
            ImprimirCadena.aviso("No se encontraron resultados");
        }
    }

}
