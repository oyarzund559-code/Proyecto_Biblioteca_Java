
package modelo; //Requerimiento: package

import enums.Tipo_Producto;
//Requisito: Herencia
public class Libro extends Producto{
    private String autor;
    private int numeroPaginas;

    public Libro(String titulo, String autor, int numeroPaginas) {
        super(titulo, Tipo_Producto.Libro_Fisico);
        this.autor = autor;
        this.numeroPaginas = numeroPaginas;
    }
    
    @Override
    public int calcularDiasPrestado(){
        return 14; 
    }
    
    @Override
    public void obtenerFicha(){
         System.out.println("Libro ID:" + id );
         System.out.println("Titulo: " + titulo);
         System.out.println("Autor: " + autor);
         System.out.println("Paginas: " + numeroPaginas);
         if (prestado == true){
             System.out.println("Prestado: Si");
         } else{
             System.out.println("Prestado: No");
         }
    }
}
