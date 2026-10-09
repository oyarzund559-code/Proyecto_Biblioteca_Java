/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador; //Requisito: package

import modelo.*;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
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
    }
    
    public void agregarProductos(Producto producto){
        listaProductos.add(producto);
    }
    
    public void agregarPrestamo(Prestamo prestamo){
        listaPrestamos.add(prestamo);
    }
    
    public void agregarCliente(Cliente cliente){
        listaClientes.put(cliente.getRut(), cliente);
    }
    
    public void listarProductos(){
        if(listaProductos.isEmpty()){
            System.out.println("No existen productos registrados");
        }else{
            for(Producto p: listaProductos){
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
