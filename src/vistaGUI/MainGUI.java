
package vistaGUI;

import controlador.Gestion_Biblioteca;
import javax.swing.*;

public class MainGUI {
    public static void main(String[] args) {
        Gestion_Biblioteca gestion = new Gestion_Biblioteca();
        
        SwingUtilities.invokeLater(() -> {
           new InicioVista(gestion).mostrar();
        });
    }
    
}
