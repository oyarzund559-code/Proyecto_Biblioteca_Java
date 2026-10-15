
package modelo; //Requerimiento: package

import interfaces.Consultable;
import java.io.Serializable;
//Requerimiento: Persistencia
public class Cliente implements Consultable, Serializable{
    private static final long  serialVersionUID = 1L;
    public static final int Max_Prestamos = 3;
    private String nombre;
    private String rut;
    private int contadorPrestamos;
    
    public Cliente(String nombre, String rut) {
        this.nombre = nombre;
        this.rut = rut;
        this.contadorPrestamos = 0;
    }
    
    public void incrementarPrestamos(){
        contadorPrestamos++;
    }
    
    public void descontarPrestamo(){
        if(contadorPrestamos > 0){
            contadorPrestamos--;
        }
    }
    
     @Override
    public void obtenerFicha(){
        System.out.println("Nombre cliente: " + nombre);
        System.out.println("Rut cliente: " + rut);
        System.out.println("Prestamos Activos: " + contadorPrestamos);
         System.out.println("");
    }

    public int getContadorPrestamos() {
        return contadorPrestamos;
    }

    public String getNombre() {
        return nombre;
    }

    public String getRut() {
        return rut;
    }
          
}