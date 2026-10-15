
package excepciones; //Requisito: package

public class LimitePrestamosExcedidoException extends RuntimeException {
    public LimitePrestamosExcedidoException(String mensaje) {
        super(mensaje);
    }
}
