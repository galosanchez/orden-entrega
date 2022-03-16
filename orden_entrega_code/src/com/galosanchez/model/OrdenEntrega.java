package com.galosanchez.model;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Galo Sánchez
 */
public class OrdenEntrega {
    private int numOrden;
    private static int contadorNumOrden;
    private Cliente cliente;
    private char estado;
    private Date fechaEmision;
    private Date fechaConcreta;
    private ArrayList<DetalleOrden> detallesOrden;

    public OrdenEntrega() {
    }

    public OrdenEntrega(Cliente cliente, char estado, Date fechaEmision, Date fechaConcreta) {
        OrdenEntrega.contadorNumOrden++;
        this.cliente = cliente;
        this.estado = estado;
        this.fechaEmision = fechaEmision;
        this.fechaConcreta = fechaConcreta;
        this.numOrden = OrdenEntrega.contadorNumOrden;
        this.detallesOrden = new ArrayList<>();
    }

    public static int getContadorNumOrden() {
        return contadorNumOrden;
    }

    public static void setContadorNumOrden(int contadorNumOrden) {
        OrdenEntrega.contadorNumOrden = contadorNumOrden;
    }
    
    public int getNumOrden() {
        return this.numOrden;
    }

    public void setNumOrden(int numOrden) {
        this.numOrden = numOrden;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public char getEstado() {
        return estado;
    }

    public void setEstado(char estado) {
        this.estado = estado;
    }

    public Date getFechaEmision() {
        return fechaEmision;
    }

    public void setFechaEmision(Date fechaEmision) {
        this.fechaEmision = fechaEmision;
    }

    public Date getFechaConcreta() {
        return fechaConcreta;
    }

    public void setFechaConcreta(Date fechaConcreta) {
        this.fechaConcreta = fechaConcreta;
    }

    public ArrayList<DetalleOrden> getDetallesOrden() {
        return detallesOrden;
    }

    public void setDetallesOrden(ArrayList<DetalleOrden> detallesOrden) {
        this.detallesOrden = detallesOrden;
    }

    @Override
    public String toString() {
        return "OrdenEntrega{" + "numOrden=" + numOrden + ", cliente=" + cliente.toString() + ", estado=" + estado + ", fechaEmision=" + fechaEmision + ", fechaConcreta=" + fechaConcreta + ", detallesOrden=" + detallesOrden.toString() + '}';
    }
    
    
    
}
