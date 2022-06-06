package com.galosanchez.data;

import com.galosanchez.model.Cliente;
import com.galosanchez.model.OrdenEntrega;
import com.galosanchez.model.Producto;
import java.util.ArrayList;

/**
 * @author Galo Sánchez
 */
public class Datos {
    public static ArrayList<Producto> listaProductos = new ArrayList();
    public static ArrayList<Cliente> listaClientes = new ArrayList();
    public static ArrayList<OrdenEntrega> listaOrdenEntrega = new ArrayList();
    
    public static void cargarDatos(){
        Producto P;
        listaProductos.add(P = new Producto("P01", "Razer BlackShark", 12, 200.99f));
        listaProductos.add(P = new Producto("P02", "Gaming Headset", 100, 350.16f));
        listaProductos.add(P = new Producto("P03", "Acer Nitro", 18, 999.99f));
        listaProductos.add(P = new Producto("P04", "Lenovo - 2021", 12, 1003.20f));
        listaProductos.add(P = new Producto("P05", "MSI GV15", 19, 1200.56f));
        listaProductos.add(P = new Producto("P05", "DualShock 4", 200, 59.99f));
        listaProductos.add(P = new Producto("P05", "Meta Quest 2", 14, 399.99f));
        listaProductos.add(P = new Producto("P05", "NBZZ Adjustable", 6, 54.98f));
        
        Cliente C;
        listaClientes.add(C = new Cliente("1801", "Juan Villacis", "Ambato"));
        listaClientes.add(C = new Cliente("1802", "Diana Travis", "Quito"));
        listaClientes.add(C = new Cliente("1803", "Jose Solis", "Cevallos"));
        listaClientes.add(C = new Cliente("1804", "Alexander Maza", "Pinllo"));
        listaClientes.add(C = new Cliente("1804", "Galo Sánchez", "Ambato"));
    }
}