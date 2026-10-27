
package vista;

import java.util.Scanner;
import enums.Tipo_Producto;
import excepciones.*;
import java.io.*;
import modelo.*;
import controlador.Gestion_Biblioteca;
//Requisito: Persistencia
public class Main {

   
    public static void main(String[] args) {
        Gestion_Biblioteca gestion = new Gestion_Biblioteca();
        Scanner sc = new Scanner(System.in);
        int inicioWhile = 1;
        
        while(inicioWhile == 1){
            System.out.println("");
            System.out.println("Menu");
            System.out.println("1. Agregar Libros");
            System.out.println("2. Agregar DVD");
            System.out.println("3. Agregar Clientes");
            System.out.println("4. Realizar Prestamo");
            System.out.println("5. Registrar Devolucion");
            System.out.println("6. Listar Catalogo Productos");
            System.out.println("7. Listar Clientes");
            System.out.println("8. Listar Prestamos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una de las opciones: ");
            int opcion = Integer.parseInt(sc.nextLine());
            System.out.println("");
            
            switch(opcion){
                case 1:
                    System.out.print("Titulo del Libro: ");
                    String tituloLibro = sc.nextLine();
                    System.out.print("Autor del Libro: ");
                    String autorLibro = sc.nextLine();
                    System.out.print("Numero de paginas del libro: ");
                    int numeroPaginas = Integer.parseInt(sc.nextLine());
                    Libro nuevoLibro = new Libro(tituloLibro, autorLibro, numeroPaginas);
                    gestion.agregarProductos(nuevoLibro);
                    System.out.println("Libro registrado con un id asociado de: " + nuevoLibro.getId());
                    System.out.println("");
                    break;
                case 2:
                    System.out.print("Titulo del DVD: ");
                    String tituloDVD = sc.nextLine();
                    System.out.print("Director DVD: ");
                    String directorDVD = sc.nextLine();
                    System.out.print("Duracion en minutos: ");
                    int duracionDVD = Integer.parseInt(sc.nextLine());
                    DVD nuevoDVD = new DVD(tituloDVD, directorDVD, duracionDVD);
                    gestion.agregarProductos(nuevoDVD);
                    System.out.println("DVD registrado con un id asociado de: " + nuevoDVD.getId());
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
                    try{
                        gestion.agregarPrestamo(rut, id);
                        System.out.println("Se a realizado correctamente el prestamo");
                        System.out.println("");
                    }catch(LimitePrestamosExcedidoException | ProductoNoDisponibleException e){
                        System.out.println("Alerta: " + e.getMessage());
                    }
                    System.out.println("");
                    break;
                case 5:
                    System.out.print("Ingresar el rut del cliente: ");
                    String rutD = sc.nextLine();
                    System.out.print("Ingrese el id del producto a devolver (ej: Prod-1): ");
                    String idP = sc.nextLine();
                    gestion.registrarDevolucion(rutD, idP);
                    System.out.println("");
                    break;
                case 6:
                    System.out.println("Catalogo: ");
                    gestion.listarProductos();
                    System.out.println("");
                    break;
                case 7:
                    System.out.println("Listado Clientes: ");
                    gestion.listarClientes();
                    System.out.println("");
                    break;
                case 8:
                    System.out.println("Listado de Prestamos");
                    gestion.listarPrestamos();
                    System.out.println("");
                    break;
                case 0:
                    gestion.guardar();
                    System.out.println("Muchas gracias por usar el programa");
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
    