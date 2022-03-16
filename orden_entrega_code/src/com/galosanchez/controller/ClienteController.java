package com.galosanchez.controller;

import com.galosanchez.model.Cliente;
import com.galosanchez.model.ClienteModel;
import com.galosanchez.mymethods.ImprimirCadena;
import com.galosanchez.mymethods.LeerDatos;
import java.util.ArrayList;

/**
 * @author Galo Sánchez
 */
public class ClienteController {

    // Pedir la cédula para buscar producto
    public static void buscarCliente() {
        String cedula = null;
        // Validar codigo
        while (cedula == null) {
            ImprimirCadena.pedirDato("Cédula");
            cedula = LeerDatos.datoCadena();
            if (cedula == null) {
                ImprimirCadena.errorDato("Ingrese la cédula");
            }
        }
        // Existe cliente
        Cliente cliente;
        cliente = ClienteModel.buscarCedula(cedula);
        if (cliente != null) {
            infoCliente(cliente);
        } else {
            ImprimirCadena.aviso("No se encontro resultados");
        }
    }

    // Pedir datos para crear un nuevo cliente
    public static void nuevoCliente() {
        String cedula = null;
        String nombre = null;
        String direccion = null;

        // Validar datos del Cliente
        while (cedula == null) {
            ImprimirCadena.pedirDato("Cédula");
            cedula = LeerDatos.datoCadena();
            if (cedula == null) {
                ImprimirCadena.errorDato("Ingrese la cédula");
            } else if (ClienteModel.existeClienteCedula(cedula)) { // Comprobar no exista codigo en lista
                ImprimirCadena.errorDato("Cédula ya existe");
                cedula = null;
            }
        }

        while (nombre == null) {
            ImprimirCadena.pedirDato("Nombre");
            nombre = LeerDatos.datoCadena();
            if (nombre == null) {
                ImprimirCadena.errorDato("Ingrese un nombre");
            }
        }

        while (direccion == null) {
            ImprimirCadena.pedirDato("Dirección");
            direccion = LeerDatos.datoCadena();
            if (direccion == null) {
                ImprimirCadena.errorDato("Ingrese una dirección");
            }
        }
        // Agregar a lista
        ClienteModel.agregarLista(new Cliente(cedula, nombre, direccion));
        ImprimirCadena.completado("Creado exitosamente");
    }

    // Edida los datos del cliente
    public static void editarCliente() {
        String cedula = null;
        // Validar cedula
        while (cedula == null) {
            ImprimirCadena.pedirDato("Cédula");
            cedula = LeerDatos.datoCadena();
            if (cedula == null) {
                ImprimirCadena.errorDato("Ingrese la cédula");
            }
        }
        //Existe cliente
        Cliente cliente;
        cliente = ClienteModel.buscarCedula(cedula);

        if (cliente == null) {
            ImprimirCadena.aviso("No existe cliente");
        } else {
            ImprimirCadena.itemTituloCliente("Editar cliente");
            infoCliente(cliente);
            // Obtiene los nuevos valores a editar
            Cliente clienteEdit = validarNuevosDatos();
            if (clienteEdit == null) {
                ImprimirCadena.aviso("Ningún valor editado");
            } else {
                ClienteModel.editar(cedula, clienteEdit);
                ImprimirCadena.completado("Edidato con exito");
            }

        }
    }

    //Valida los nuevos datos para editar al cliente
    private static Cliente validarNuevosDatos() {
        String nombre = null;
        String direccion = null;

        while (nombre == null) {
            ImprimirCadena.pedirDato("Nuevo nombre");
            nombre = LeerDatos.datoCadenaVacio();
            if (nombre == null) {
                ImprimirCadena.errorDato("Ingrese un nombre");
            }
        }
        while (direccion == null) {
            ImprimirCadena.pedirDato("Nueva dirección");
            direccion = LeerDatos.datoCadenaVacio();
            if (direccion == null) {
                ImprimirCadena.errorDato("Ingrese una dirección");
            }
        }

        // Si los datos estan vacios no edita
        if (nombre.length() == 0 && direccion.length() == 0) {
            return null;
        }
        return new Cliente("", nombre, direccion);
    }

    // Pide la cédula para buscar y eliminar el cliente
    public static void eliminarCliente() {
        String cedula = null;
        // Validar cedula
        while (cedula == null) {
            ImprimirCadena.pedirDato("Cédula");
            cedula = LeerDatos.datoCadena();
            if (cedula == null) {
                ImprimirCadena.errorDato("Ingrese la cédula");
            }
        }
        // Obtiene al cliente
        Cliente cliente;
        cliente = ClienteModel.buscarCedula(cedula);
        if (cliente == null) {
            ImprimirCadena.aviso("No existe cliente");
        } else {
            ClienteModel.eliminar(cliente);
            ImprimirCadena.completado("Cliente eliminado");
        }
    }

    // Imprime toda la lista de clientes
    public static void listarClientes() {
        // Obtiene toda la lista de clientes
        ArrayList<Cliente> lista = ClienteModel.listadoClientes();
        if (lista.size() > 0) {
            //Imprimir datos de cada cliente
            ImprimirCadena.itemTituloCliente("Lista de clientes");
            System.out.printf("%15s%25s%26s\n",
                    "Cédula", "Nombre", "Dirección");
            lista.forEach(cliente -> {
                System.out.printf("%15s%25s%26s\n",
                        cliente.getCedula(),
                        cliente.getNombre(),
                        cliente.getDireccion()
                );
            });
        } else {
            ImprimirCadena.aviso("No se encontraron resultados");
        }
    }

    // Devuelve el cliente de una busqueda con la cédula
    public static Cliente obtenerCliente(String cedula) {
        return ClienteModel.buscarCedula(cedula);
    }

    // Imprime la información del cliente
    public static void infoCliente(Cliente cliente) {
        System.out.printf("%15s%25s%26s\n",
                "Cédula", "Nombre", "Dirección");
        System.out.printf("%15s%25s%26s\n",
                cliente.getCedula(),
                cliente.getNombre(),
                cliente.getDireccion()
        );
    }

    // Imprime lista de clientes sin título
    public static void clientes() {
        ArrayList<Cliente> lista = ClienteModel.listadoClientes();
        if (lista.size() > 0) {
            System.out.printf("%15s%25s%26s\n",
                    "Cédula", "Nombre", "Dirección");
            lista.forEach(cliente -> {
                System.out.printf("%15s%25s%26s\n",
                        cliente.getCedula(),
                        cliente.getNombre(),
                        cliente.getDireccion()
                );
            });
        } else {
            ImprimirCadena.aviso("No se encontraron resultados");
        }
    }

}
