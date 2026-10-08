/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo; //Requerimiento: package

import interfaces.Consultable;
import java.io.Serializable;
//Requerimiento: Persistencia
public class Cliente implements Consultable, Serializable{
    private static final long  serialVersionUID = 1L;
    private static final int Max_Prestamos = 3;
    private String nombre;
    private String rut;

    public Cliente(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
    }
    
    @Override
    public void obtenerFicha(){
        System.out.println("Nombre cliente: " + nombre);
        System.out.println("Rut cliente: " + rut);
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }
          
}