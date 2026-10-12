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

   
    public static void main(String[] args) {
        Gestion_Biblioteca gestion = new Gestion_Biblioteca();
        Scanner sc = new Scanner(System.in);
        gestion.cargar();
        gestion.agregarProductos(new Libro("Don Quijote", "Cervantes", 1000));
        gestion.agregarProductos(new DVD("Cars 2", "John Lasseter", 107));
        gestion.agregarCliente(new Cliente("Guti", "11.544.251-0"));
        int inicioWhile = 1;
        
        while(inicioWhile == 1){
            System.out.println("");
            System.out.println("Menu");
            System.out.println("1. Agregar Libros");
            System.out.println("2. Agregar DVD");
            System.out.println("3. Agregar Clientes");
            System.out.println("4. Realizar Prestamo");
            System.out.println("5. Listar Catalogo Productos");
            System.out.println("6. Listar Clientes");
            System.out.println("7. Listar Prestamos");
            System.out.println("8. Guardar Estado");
            System.out.println("9. Cargar Estado");
            System.out.println("0. Salir");
            System.out.println("Seleccione una de las opciones");
            System.out.println("");
            
            int opcion = Integer.parseInt(sc.nextLine());
            switch(opcion){
                case 1:
                    System.out.print("Titulo del Libro:");
                    String tituloLibro = sc.nextLine();
                    System.out.print("Autor del Libro:");
                    String autorLibro = sc.nextLine();
                    System.out.print("Numero de paginas del libro: ");
                    int numeroPaginas = Integer.parseInt(sc.nextLine());
                    gestion.agregarProductos(new Libro(tituloLibro, autorLibro, numeroPaginas));
                    System.out.println("Libro registrado");
                    System.out.println("");
                    break;
                case 2:
                    System.out.print("Titulo del DVD: ");
                    String tituloDVD = sc.nextLine();
                    System.out.print("Director DVD: ");
                    String directorDVD = sc.nextLine();
                    System.out.print("Duracion en minutos: ");
                    int duracionDVD = Integer.parseInt(sc.nextLine());
                    gestion.agregarProductos(new DVD(tituloDVD, directorDVD, duracionDVD));
                    System.out.println("DVD registrado");
                    System.out.println("");
                    break;
                case 3:
                    System.out.print("Nombre del Cliente: ");
                    String nombreCliente = sc.nextLine();
                    System.out.print("Rut del cliente: ");
                    String rutCliente = sc.nextLine();
                    gestion.agregarCliente(new Cliente(nombreCliente, rutCliente));
                    System.out.println("Cliente registrado");
                    System.out.print("");
                    break;
                case 4:
                    System.out.print("Ingresar el Rut del cliente: ");
                    String rut = sc.nextLine();
                    System.out.print("Ingrese la ID del Producto(ej: Prod-1): ");
                    String id = sc.nextLine();
                    gestion.agregarPrestamo(rut, id);
                    break;
                case 5:
                    System.out.println("Catalogo: ");
                    gestion.listarProductos();
                    System.out.println("");
                    break;
                case 6:
                    System.out.println("Listado Clientes: ");
                    gestion.listarClientes();
                    System.out.println("");
                    break;
                case 7:
                    System.out.println("Listado de Prestamos");
                    gestion.listarPestamos();
                    System.out.println("");
                    break;
                case 8:
                    gestion.guardar();
                    System.out.println("");
                    break;
                case 9:
                    gestion.cargar();
                    System.out.println("");
                    break;
                case 0:
                    gestion.guardar();
                    System.out.println("");
                    inicioWhile = 0;
                    break;
                default:
                    System.out.println("La opción no es valida intente nuevamente");
                    System.out.println("");
                }
            }
           sc.close();
        }
    }
    