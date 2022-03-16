package com.galosanchez.model;

import com.galosanchez.data.Datos;
import java.util.ArrayList;

/**
 * @author Galo Sánchez
 */
public class ClienteModel {

    // Devuelve el cliente de una busqueda con la cédula
    public static Cliente buscarCedula(String cedula) {
        for (Cliente c : Datos.listaClientes) {
            if (c.getCedula().equals(cedula)) {
                return c;
            }
        }
        return null;
    }

    // Devuelve lista de clinetes desde Datos
    public static ArrayList<Cliente> listadoClientes() {
        return Datos.listaClientes;
    }

    // Verifica si existe cliente con la cédula
    public static boolean existeClienteCedula(String cedula) {
        for (Cliente c : Datos.listaClientes) {
            if (c.getCedula().equals(cedula)) {
                return true;
            }
        }
        return false;
    }

    // Agrega nuevo a lista de Datos
    public static void agregarLista(Cliente cliente) {
        Datos.listaClientes.add(cliente);
    }

    // Elimina de lista de Datos
    public static void eliminar(Cliente cliente) {
        Datos.listaClientes.remove(cliente);
    }

    // Guarda los nuevos datos del cliente
    public static void editar(String cedula, Cliente clienteEdit) {
        Cliente cliente = buscarCedula(cedula);
        if (clienteEdit.getNombre().length() > 0) {
            cliente.setNombre(clienteEdit.getNombre());
        }
        if (clienteEdit.getDireccion().length() > 0) {
            cliente.setDireccion(clienteEdit.getDireccion());
        }
    }

}
