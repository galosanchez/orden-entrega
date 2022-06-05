package com.galosanchez.controller;

import com.galosanchez.model.DetalleOrden;
import com.galosanchez.model.Producto;
import com.galosanchez.model.ProductoModel;
import com.galosanchez.mymethods.ImprimirCadena;
import com.galosanchez.mymethods.LeerDatos;
import java.util.ArrayList;

/**
 * @author Galo Sánchez
 */
public class ProductoController {

    // Busqueda de producto desde el código de producto
    public static void buscarProducto() {
        String codigo = null;
        // Validar codigo
        while (codigo == null) {
            ImprimirCadena.pedirDato("Código");
            codigo = LeerDatos.datoCadena();
            if (codigo == null) {
                ImprimirCadena.errorDato("Ingrese un código");
            }
        }
        // Existe código
        Producto producto;
        producto = ProductoModel.buscarCodigo(codigo);
        if (producto != null) {
            infoProducto(producto);
        } else {
            ImprimirCadena.aviso("No se encontro resultados");
        }
    }

    // Nuevo producto
    public static void agregarProducto() {
        String codigo = null;
        String nombre = null;
        int cantidad = 0;
        float precio = 0f;

        // Validar datos del Producto
        while (codigo == null) {
            ImprimirCadena.pedirDato("Código");
            codigo = LeerDatos.datoCadena();
            if (codigo == null) {
                ImprimirCadena.errorDato("Ingrese un código");
            } else if (ProductoModel.existeProductoCodigo(codigo)) { // Comprobar que no exista producto
                ImprimirCadena.errorDato("Código ya existe");
                codigo = null;
            }
        }

        while (nombre == null) {
            ImprimirCadena.pedirDato("Nombre");
            nombre = LeerDatos.datoCadena();
            if (nombre == null) {
                ImprimirCadena.errorDato("Ingrese un nombre");
            }
        }

        while (cantidad < 1) {
            ImprimirCadena.pedirDato("Cantidad");
            cantidad = LeerDatos.datoIntMayorCero();
            if (cantidad < 1) {
                ImprimirCadena.errorDato("Ingrese la cantidad");
            }
        }

        while (precio <= 0) {
            ImprimirCadena.pedirDato("Precio");
            precio = LeerDatos.datoFloatMayorCero();
            if (precio <= 1) {
                ImprimirCadena.errorDato("Ingrese el precio");
            }
        }

        //Agrega a lista
        ProductoModel.agregarLista(new Producto(codigo, nombre, cantidad, precio));
        ImprimirCadena.completado("Creado exitosamente");
    }

    // Editar valores del producto
    public static void editarProducto() {
        String codigo = null;
        // Validar codigo
        while (codigo == null) {
            ImprimirCadena.pedirDato("Código");
            codigo = LeerDatos.datoCadena();
            if (codigo == null) {
                ImprimirCadena.errorDato("Ingrese un código");
            }
        }
        // Existe producto
        Producto producto;
        producto = ProductoModel.buscarCodigo(codigo);
        if (producto == null) {
            ImprimirCadena.aviso("No existe el producto");
        } else {
            ImprimirCadena.itemTitulo("Editar producto");
            infoProducto(producto);
            // Método para validar los nuevos datos
            Producto productoEdit = validarNuevosDatos();
            if (productoEdit == null) {
                ImprimirCadena.aviso("Ningún valor editado");
            } else {
                // Guardar cambios
                ProductoModel.editar(codigo, productoEdit);
                ImprimirCadena.completado("Edidato con exito");
            }
        }
    }

    // validar los datos a editar que devuelve los nuevos valores
    private static Producto validarNuevosDatos() {
        // Validar nuevos datos del producto
        String nombre = null;
        int cantidad = -2;
        float precio = -1f;

        while (nombre == null) {
            ImprimirCadena.pedirDato("Nuevo nombre");
            nombre = LeerDatos.datoCadenaVacio();
            if (nombre == null) {
                ImprimirCadena.errorDato("Ingrese un nombre");
            }
        }

        while (cantidad < -1) {
            ImprimirCadena.pedirDato("Nueva cantidad");
            cantidad = LeerDatos.datoIntPositivoVacio();
            if (cantidad < -1) {
                ImprimirCadena.errorDato("Ingrese una cantidad");
            }
        }

        while (precio < 0) {
            ImprimirCadena.pedirDato("Nueva precio");
            precio = LeerDatos.datoFloatMayorCeroVacio();
            if (precio < 0) {
                ImprimirCadena.errorDato("Ingrese una precio");
            }
        }
        if (nombre.length() == 0 && cantidad == -1 && precio == 0) {
            return null;
        }

        return new Producto(nombre, nombre, cantidad, precio);
    }

    // Eliminar producto
    public static void eliminarProducto() {
        String codigo = null;

        // Validar código
        while (codigo == null) {
            ImprimirCadena.pedirDato("Código");
            codigo = LeerDatos.datoCadena();
            if (codigo == null) {
                ImprimirCadena.errorDato("Ingrese un código");
            }
        }
        // Existe producto para eliminar
        Producto producto;
        producto = ProductoModel.buscarCodigo(codigo);
        if (producto == null) {
            ImprimirCadena.aviso("No existe producto");
        } else {
            ProductoModel.eliminar(producto);
            ImprimirCadena.completado("Producto eliminado");
        }
    }

    // Imprime todos los productos registrados
    public static void listarProductos() {
        ArrayList<Producto> lista = ProductoModel.listadoProductos();
        // Valida si existe productos
        if (lista.size() > 0) {
            ImprimirCadena.itemTitulo("Lista de productos");
            System.out.printf("%10s%15s%12s%12s\n",
                    "Código", "Nombre", "Cantidad", "Precio $");
            lista.forEach(producto -> {
                System.out.printf("%10s%15s%12s%12s\n",
                        producto.getCodigo(),
                        producto.getNombre(),
                        producto.getCantidad(),
                        producto.getPrecio()
                );
            });
        } else {
            ImprimirCadena.aviso("No se encontraron resultados");
        }
    }

    // Imprime todos los productos de una lista
    public static void productosFactura(ArrayList<DetalleOrden> lista) {
        if (lista.size() > 0) {
            System.out.printf("%10s%15s%12s%12s%15s\n",
                    "Código", "Nombre", "Cantidad", "Precio", "Monto");
            lista.forEach(detalleOrden -> {
                System.out.printf("%10s%15s%12s%12s%15s\n",
                        detalleOrden.getProducto().getCodigo(),
                        detalleOrden.getProducto().getNombre(),
                        detalleOrden.getCantidad(),
                        detalleOrden.getProducto().getPrecio(),
                        (detalleOrden.getProducto().getPrecio() * detalleOrden.getCantidad())
                );
            });
        } else {
            ImprimirCadena.aviso("No se encontraron resultados");
        }
    }

    // Imprime información de un producto
    public static void infoProducto(Producto producto) {
        System.out.printf("\n%10s%15s%12s%12s\n",
                "Código", "Nombre", "Cantidad", "Precio $");
        System.out.printf("%10s%15s%12s%12s\n",
                producto.getCodigo(),
                producto.getNombre(),
                producto.getCantidad(),
                producto.getPrecio()
        );
    }

    // Devuelve un producto buscando con el código
    public static Producto obtenerProductoCodigo(String codigo) {
        return ProductoModel.buscarCodigo(codigo);
    }

}
