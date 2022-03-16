package com.galosanchez.view;

import com.galosanchez.controller.OrdenEntregaController;
import com.galosanchez.mymethods.ImprimirCadena;
import com.galosanchez.mymethods.LeerDatos;

/**
 * @author Galo Sánchez
 */
public class OrdenEntregaView {

    protected static void menuOrdenEntrega() {
        int op = 0;
        while (op != 6) {
            ImprimirCadena.subTitulo("Gestión de ordenes");
            ImprimirCadena.itemsMenu("1. Buscar", "2. Nuevo", "3. Confirmar", "4. Cancelar", "5. Lista de ordenes", "6. Atras");
            ImprimirCadena.pedirOpcion();
            op = LeerDatos.opcion();
            switch (op) {
                case 1:
                    OrdenEntregaController.buscarOrden();
                    break;
                case 2:
                    OrdenEntregaController.nuevaOrden();
                    break;
                case 3:
                    OrdenEntregaController.confirmarOrden();
                    break;
                case 4:
                    OrdenEntregaController.cancelarOrden();
                    break;
                case 5:
                    OrdenEntregaController.listarOrdenEntrega();
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
