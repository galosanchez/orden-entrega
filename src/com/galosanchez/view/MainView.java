package com.galosanchez.view;

import com.galosanchez.controller.DatosController;
import com.galosanchez.mymethods.ImprimirCadena;
import com.galosanchez.mymethods.LeerDatos;

/**
 * @author Galo Sánchez
 */
public class MainView {

    // Menú principal
    public void mainView() {
        int op = 0;
        while (op != 6) {
            ImprimirCadena.titulo("Empresa SA.");
            ImprimirCadena.itemsMenu("1. Producto", "2. Cliente", "3. Orden de entrega", "4. Reportes", "5. Cargar datos", "6. Salir");
            // Opción ==>
            ImprimirCadena.pedirOpcion();
            // Obtener opcion desde teclado
            op = LeerDatos.opcion();
            // Abrir submenus
            switch (op) {
                case 1:
                    ProductoView.menuProducto();
                    break;
                case 2:
                    ClienteView.menuCliente();
                    break;
                case 3:
                    OrdenEntregaView.menuOrdenEntrega();
                    break;
                case 4:
                    ReportesView.menuReportes();
                    break;
                case 5:
                    DatosController.cargarDatos();
                    break;
                case 6:
                    ImprimirCadena.bye();
                    break;
                default:
                    ImprimirCadena.errorOpcion();
                    break;
            }
        }
    }

}
