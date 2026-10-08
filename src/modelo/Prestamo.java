/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo; //Requerimiento: package

import enums.Estado_Producto;
import interfaces.Consultable;
import java.io.Serializable;
//Requerimiento: Persistencia
public class Prestamo implements Consultable, Serializable{
    private static final long  serialVersionUID = 1L;
    private Cliente cliente;
    private Producto producto;
    private Estado_Producto estado;

    public Prestamo(Cliente cliente, Producto producto, Estado_Producto estado) {
        this.cliente = cliente;
        this.producto = producto;
        this.estado = estado;
    }
    public void registrarDevoluciones(){
        this.estado = Estado_Producto.Devuelto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public Estado_Producto getEstado() {
        return estado;
    }
    
    @Override
    public void obtenerFicha(){
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Producto: " + producto.getTitulo());
        System.out.println("Estado del Producto: " + estado);
    }
}
