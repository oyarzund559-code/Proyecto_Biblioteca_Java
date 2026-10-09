/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.Main to edit this template
 */
package vista;

import java.util.Scanner;
import enums.Tipo_Producto;
import java.io.*;
import modelo.*;
import controlador.Gestion_Biblioteca;
//Requisito: Persistencia
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Gestion_Biblioteca gestion = new Gestion_Biblioteca();
        Scanner sc = new Scanner(System.in);
        gestion.cargar();
        gestion.agregarProductos(new Libro("Don Quijote", "Cervantes", 1000));
        gestion.agregarProductos(new DVD("Cars 2", "John Lasseter", 107));
        gestion.agregarCliente(new Cliente("Guti", "11.544.251-0"));
        int opcion = 1;
        
        while(opcion == 1){
            System.out.println("");
            System.out.println("Menu");
            System.out.println("1. Agregar Libros");
            System.out.println("2. Agregar DVD");
            System.out.println("3. Agregar Clientes");
            System.out.println("4. Listar Catalogo Productos");
            System.out.println("5. Listar Clientes");
            System.out.println("6. Guardar Estado");
            System.out.println("7. Cargar Estado");
            System.out.println("0. Salir");
            System.out.println("Seleccione una de las opciones");
            System.out.println("");
            
            opcion = Integer.parseInt(sc.nextLine());
            switch(opcion){
                case 1:
                    System.out.println("Titulo del Libro:");
                    String tituloLibro = sc.nextLine();
                    System.out.println("Autor del Libro:");
                    String autorLibro = sc.nextLine();
                    System.out.println("Numero de paginas del libro: ");
                    int numeroPaginas = Integer.parseInt(sc.nextLine());
                    gestion.agregarProductos(new Libro(tituloLibro, autorLibro, numeroPaginas));
                    System.out.println("Libro registrado");
                    break;
                case 2:
                    System.out.println("Titulo del DVD: ");
                    String tituloDVD = sc.nextLine();
                    System.out.println("Director DVD: ");
                    String directorDVD = sc.nextLine();
                    System.out.println("Duracion en minutos: ");
                    int duracionDVD = Integer.parseInt(sc.nextLine());
                    gestion.agregarProductos(new DVD(tituloDVD, directorDVD, duracionDVD));
                    System.out.println("DVD registrado");
                    break;
                case 3:
                    System.out.println("Nombre del Cliente: ");
                    String nombreCliente = sc.nextLine();
                    System.out.println("Rut del cliente: ");
                    String rutCliente = sc.nextLine();
                    gestion.agregarCliente(new Cliente(nombreCliente, rutCliente));
                    System.out.println("Cliente registrado");
                    break;
                case 4:
                    System.out.println("Catalogo: ");
                    gestion.listarProductos();
                    break;
                case 5:
                    System.out.println("Listado Clientes: ");
                    gestion.listarClientes();
                    break;
                case 6:
                    gestion.guardar();
                    break;
                case 7:
                    gestion.cargar();
                    break;
                case 0:
                    gestion.guardar();
                    opcion = 0;
                    break;
                default:
                    System.out.println("La opción no es valida intente nuevamente");
                }
            sc.close();
            }
        }
    }
    