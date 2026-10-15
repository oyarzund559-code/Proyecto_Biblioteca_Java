
package controlador; //Requisito: package

import modelo.*;
import excepciones.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import enums.Estado_Producto;

public class Gestion_Biblioteca {
    //Requisito: Colecciones
    private ArrayList<Producto> listaProductos;
    private ArrayList<Prestamo> listaPrestamos;
    private HashMap<String, Cliente> listaClientes;
    private static final String ARCHIVO_PRODUCTOS = "productos.dat";
    private static final String ARCHIVO_CLIENTES = "clientes.dat";
    private static final String ARCHIVO_PRESTAMOS = "prestamos.dat";

    public Gestion_Biblioteca() {
        this.listaProductos = new ArrayList<>();
        this.listaPrestamos = new ArrayList<>();
        this.listaClientes = new HashMap<>();
        
        File f = new File(ARCHIVO_PRODUCTOS);
        if(!f.exists()){
        cargarDatosPrecargados();
        }
    }
    
    private void cargarDatosPrecargados(){
        listaProductos.add(new Libro("Cien años de soledad", "Gabriel Marquez", 417));
        listaProductos.add(new Libro("Don Quijote de la Mancha", "Miguel de Cerbantes", 1256));
        listaProductos.add(new Libro("1984", "George", 352));
        
        listaProductos.add(new DVD("Matrix", "Wachowski", 136));
        listaProductos.add(new DVD("Inception", "Cristopher", 148));
        listaProductos.add(new DVD("El clup de la pelea", "David Fincher", 139));
        
        Cliente c1 = new Cliente("Daniel Oyarzun", "22.506.704-K");
        Cliente c2 = new Cliente("Javier Gutierrez", "22.487.216-0");
        Cliente c3 = new Cliente("Felipe Villalobos", "22.654.681-6");
        Cliente c4 = new Cliente("Nicolas Veas", "26.165.505-7");
        
    }
    public void agregarProductos(Producto producto){
        listaProductos.add(producto);
    }
    
    public void agregarPrestamo(String rut, String id){
        Cliente cliente = listaClientes.get(rut);
        if(cliente == null){
            System.out.println("No existen clientes asociados a este Rut");
            return;
        }
        if(cliente.getContadorPrestamos() >= Cliente.Max_Prestamos){
            throw new LimitePrestamosExcedidoException("El cliente " + cliente.getNombre() + " alcanzo el maximo de prestamos de " + Cliente.Max_Prestamos + " prestamos");
        }
        Producto productoEncontrado = null;
        for(Producto p: listaProductos){
            if(p.getId().equalsIgnoreCase(id)){
                productoEncontrado = p;
                break;
            }
        }
        if(productoEncontrado == null){
            System.out.println("Error no se encontro ningun producto asociado a esta id");
            return;
        }
        if(productoEncontrado.isPrestado()){
            throw new ProductoNoDisponibleException("El producto " + productoEncontrado.getTitulo() + " no se encuantra disponible");
        }
        productoEncontrado.prestar();
        cliente.incrementarPrestamos();
        Prestamo nuevoPrestamo = new Prestamo(cliente, productoEncontrado, Estado_Producto.Activo);
        listaPrestamos.add(nuevoPrestamo);
        System.out.println("Se registro correctamente el prestamo");
    }
    
    public void agregarCliente(Cliente cliente){
        listaClientes.put(cliente.getRut(), cliente);
        System.out.println("");
    }
    
    public void listarProductos(){
        if(listaProductos.isEmpty()){
            System.out.println("No existen productos registrados");
        }else{
            for(Producto p: listaProductos){
                p.obtenerFicha();
                System.out.println("");
            }
        }
    }
    
    public void listarClientes(){
        if(listaClientes.isEmpty()){
            System.out.println("No existen clientes registrados");
        }else{
            for(Cliente p: listaClientes.values()){
                p.obtenerFicha();
            }
        }
    }
    
    public void listarPestamos(){
        if(listaPrestamos.isEmpty()){
            System.out.println("No existen prestamos registrados");
        }else{
            for(Prestamo p: listaPrestamos){
                p.obtenerFicha();
            }
        }
    }
    
   //Requisito: Percistencia
    public void guardar(){
        try{
            ObjectOutputStream oosProd = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PRODUCTOS));
            oosProd.writeObject(listaProductos);
            oosProd.close();
            
            ObjectOutputStream oosClie = new ObjectOutputStream(new FileOutputStream(ARCHIVO_CLIENTES));
            oosClie.writeObject(listaClientes);
            oosClie.close();
            
            ObjectOutputStream oosPres = new ObjectOutputStream(new FileOutputStream(ARCHIVO_PRESTAMOS));
            oosPres.writeObject(listaPrestamos);
            oosPres.close();
            
            System.out.println("Los datos se han guardado");
        }catch(IOException e){
            System.out.println("Error a guardar los datos: " + e.getMessage());
        }
    }
    //Requisito: Percistencia
    @SuppressWarnings("unchecked")
    public void cargar(){
        try{
            File fProd = new File(ARCHIVO_PRODUCTOS);
            if(fProd.exists()){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fProd));
                listaProductos = (ArrayList<Producto>) ois.readObject();
                ois.close(); 
            }
            File fClie = new File(ARCHIVO_CLIENTES);
            if(fClie.exists()){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fClie));
                listaClientes = (HashMap<String, Cliente>) ois.readObject();
                ois.close();
            }
            File fPres = new File(ARCHIVO_PRESTAMOS);
            if(fPres.exists()){
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fPres));
                listaPrestamos = (ArrayList<Prestamo>) ois.readObject();
                ois.close();
            }
            System.out.println("Los datos se han cargado de manera exitosa");
        }catch(IOException | ClassNotFoundException e){
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }
}
