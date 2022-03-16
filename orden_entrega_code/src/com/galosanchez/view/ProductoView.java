package com.galosanchez.view;

import com.galosanchez.controller.ProductoController;
import com.galosanchez.mymethods.ImprimirCadena;
import com.galosanchez.mymethods.LeerDatos;

/**
 * @author Galo Sánchez
 */
public class ProductoView {

    protected static void menuProducto() {
        int op = 0;
        while (op != 6) {
            ImprimirCadena.subTitulo("Gestión de producto");
            ImprimirCadena.itemsMenu("1. Buscar", "2. Nuevo", "3. Editar", "4. Eliminar", "5. Lista de productos", "6. Atras");
            ImprimirCadena.pedirOpcion();
            op = LeerDatos.opcion();
            switch (op) {
                case 1:
                    ProductoController.buscarProducto();
                    break;
                case 2:
                    ProductoController.agregarProducto();
                    break;
                case 3:
                    ProductoController.editarProducto();
                    break;
                case 4:
                    ProductoController.eliminarProducto();
                    break;
                case 5:
                    ProductoController.listarProductos();
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
