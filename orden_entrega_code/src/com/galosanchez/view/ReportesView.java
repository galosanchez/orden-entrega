package com.galosanchez.view;

import com.galosanchez.controller.ReporteController;
import com.galosanchez.mymethods.ImprimirCadena;
import com.galosanchez.mymethods.LeerDatos;

/**
 * @author Galo Sánchez
 */
class ReportesView {

    protected static void menuReportes() {
        int op = 0;
        while (op != 6) {
            ImprimirCadena.subTitulo("Reportes");
            ImprimirCadena.itemsMenu("1. Ordenes por cliente", "2. Entregas pendientes", "3. Entregas completadas", "4. Entregas canceladas", "5. Atras");
            ImprimirCadena.pedirOpcion();
            op = LeerDatos.opcion();
            switch (op) {
                case 1:
                    ReporteController.ordenesPorCliente();
                    break;
                case 2:
                    ReporteController.ordenesPendientes();
                    break;
                case 3:
                    ReporteController.ordenesCompletadas();
                    break;
                case 4:
                    ReporteController.ordenesCanceladas();
                    break;
                case 5:
                    ImprimirCadena.atras();
                    break;
                default:
                    ImprimirCadena.errorOpcion();
                    break;
            }
        }
    }

}
