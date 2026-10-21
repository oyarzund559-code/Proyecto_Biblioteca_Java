
package modelo; //Requerimiento: package

import enums.Estado_Producto;
import interfaces.Consultable;
import interfaces.Sis_Prestamo;
import java.io.Serializable;
//Requerimiento: Persistencia
public class Prestamo implements Consultable, Sis_Prestamo, Serializable{
    private static final long  serialVersionUID = 1L;
    private Cliente cliente;
    private Producto producto;
    private Estado_Producto estado;

    public Prestamo(Cliente cliente, Producto producto, Estado_Producto estado) {
        this.cliente = cliente;
        this.producto = producto;
        this.estado = estado;
    }
    
    @Override
    public boolean prestar(){
        if (this.estado != Estado_Producto.Activo) {
            this.estado = Estado_Producto.Activo;
            return true;
        }
        return false;
    }

    @Override
    public boolean devolver(){
        if (this.estado == Estado_Producto.Activo) {
            this.estado = Estado_Producto.Devuelto;
            return true;
        }
        return false;
    }
    
    public void registrarDevoluciones(){
        this.estado = Estado_Producto.Devuelto;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public Producto getProducto() {
        return producto;
    }

    public Estado_Producto getEstado() {
        return estado;
    }
    
    @Override
    public void obtenerFicha(){
        System.out.println("Cliente: " + cliente.getNombre());
        System.out.println("Producto: " + producto.getTitulo());
        System.out.println("Estado del Producto: " + estado);
        System.out.println("");
    }
}
