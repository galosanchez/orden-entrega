package com.galosanchez.view;

import com.galosanchez.controller.ClienteController;
import com.galosanchez.mymethods.ImprimirCadena;
import com.galosanchez.mymethods.LeerDatos;

/**
 * @author Galo Sánchez
 */
public class ClienteView {
    
    protected static void menuCliente() {
        int op = 0;
        while (op != 6) {
            ImprimirCadena.subTitulo("Gestión de clientes");
            ImprimirCadena.itemsMenu("1. Buscar", "2. Nuevo", "3. Editar", "4. Eliminar", "5. Lista de clientes", "6. Atras");
            ImprimirCadena.pedirOpcion();
            op = LeerDatos.opcion();
            switch (op) {
                case 1:
                    ClienteController.buscarCliente();
                    break;
                case 2:
                    ClienteController.nuevoCliente();
                    break;
                case 3:
                    ClienteController.editarCliente();
                    break;
                case 4:
                    ClienteController.eliminarCliente();
                    break;
                case 5:
                    ClienteController.listarClientes();
                    break;
                case 6:
                    ImprimirCadena.atras();
                    break;
                default:
                    ImprimirCadena.errorOpcion();
                    break;
            }
        }
    }
    
}
