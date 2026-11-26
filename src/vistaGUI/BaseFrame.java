
package vistaGUI;

import controlador.Gestion_Biblioteca;
import javax.swing.JFrame;

public abstract class BaseFrame extends JFrame {
    protected Gestion_Biblioteca gestion;

    public BaseFrame(String titulo, Gestion_Biblioteca gestion) {
        super(titulo);
        this.gestion = gestion;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setBackground(ColoresGui.colorFondo);
    }
    public abstract void mostrar();  
}
