
package modelo; //Requerimiento: package

import enums.Tipo_Producto;
//Requerimiento: Herencia
public class DVD extends Producto{
    private int duracion;
    private String director;

    public DVD(String titulo, String director, int duracion) {
        super(titulo, Tipo_Producto.Audiovisual);
        this.duracion = duracion;
        this.director = director;
    }
    
    @Override
     public int calcularDiasPrestado(){
        return 7; 
     }
     
     @Override
    public void obtenerFicha(){
         System.out.println("DVD ID:" + id );
         System.out.println("Titulo: " + titulo);
         System.out.println("Duracion: " + duracion);
         System.out.println("Director: " + director);
         if (prestado == true){
             System.out.println("Prestado: Si");
         } else{
             System.out.println("Prestado: No");
         }
         System.out.println("");
    }
}
