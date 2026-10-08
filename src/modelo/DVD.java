/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo; //Requerimiento: package

import enums.Tipo_Producto;
//Requerimiento: Herencia
public class DVD extends Producto{
    private int duracion;
    private String director;

    public DVD(int duracion, String director, String titulo, Tipo_Producto tipo) {
        super(titulo, tipo.Audiovisual);
        this.duracion = duracion;
        this.director = director;
    }
    
    @Override
     public int calcularDiasPrestado(){
        return 7; 
     }
     
     @Override
    public void obtenerFicha(){
         System.out.println("Libro ID:" + id );
         System.out.println("Titulo: " + titulo);
         System.out.println("Duracion: " + duracion);
         System.out.println("Director: " + director);
         if (prestado == true){
             System.out.println("Prestado: Si");
         } else{
             System.out.println("Prestado: No");
         }
    }
}
