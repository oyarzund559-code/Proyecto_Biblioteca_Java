/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo; //Requerimiento: package

import enums.Tipo_Producto;
import interfaces.Consultable;
import interfaces.Sis_Prestamo;
import java.io.*;
//Requerimiento: Herencia
//Requerimiento: Persistencia
public abstract class Producto implements Consultable, Sis_Prestamo, Serializable{
    private static final long  serialVersionUID = 1L;
    private static int contadorID = 1;
    
    protected String titulo;
    protected String id;
    protected boolean prestado;
    protected Tipo_Producto tipo;

    public Producto(String titulo, Tipo_Producto tipo) {
        this.titulo = titulo;
        this.id = "Prod-" + contadorID++;
        this.prestado = false;
        this.tipo = tipo;
    }
    
    public abstract int calcularDiasPrestado();
    
    @Override
    public boolean prestar(){
        if(prestado == false){
            prestado = true;
            return true;
        } else{
            return false;
        }
    }
    
    @Override
    public boolean devolver(){
        if(prestado == true){
            prestado = false;
            return true;
        }else{
            return false;
        }
    }

    public String getTitulo() {
        return titulo;
    }

    public String getId() {
        return id;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public Tipo_Producto getTipo() {
        return tipo;
    }
}
