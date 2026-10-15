
package excepciones; //Requisito: package

public class ProductoNoDisponibleException extends RuntimeException {
    public ProductoNoDisponibleException(String mensaje) {
        super(mensaje);
    }
}
