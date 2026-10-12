
package interfaces; //Requisito: package

import java.io.Serializable;
//Requisito: interface
public interface Sis_Prestamo extends Serializable {
    boolean prestar();
    boolean devolver();
}
