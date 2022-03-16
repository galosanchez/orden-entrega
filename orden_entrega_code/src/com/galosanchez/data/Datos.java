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
        listaProductos.add(P = new Producto("P01", "AVON", 12, 34.23f));
        listaProductos.add(P = new Producto("P02", "MENTOL", 100, 15.01f));
        listaProductos.add(P = new Producto("P03", "AVON", 12, 34.23f));
        listaProductos.add(P = new Producto("P04", "AVON", 12, 34.23f));
        listaProductos.add(P = new Producto("P05", "AVON", 12, 34.23f));
        
        Cliente C;
        listaClientes.add(C = new Cliente("1801", "Juan Villacis", "Ambato"));
        listaClientes.add(C = new Cliente("1802", "Diana Travis", "Quito"));
        listaClientes.add(C = new Cliente("1803", "Jose Solis", "Cevallos"));
        listaClientes.add(C = new Cliente("1804", "Alexander Maza", "Pinllo"));
    }
}