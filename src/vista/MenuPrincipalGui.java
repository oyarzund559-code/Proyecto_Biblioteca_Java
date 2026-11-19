
package vista;

import controlador.Gestion_Biblioteca;
import java.awt.*;
import javax.swing.*;

public class MenuPrincipalGui extends BaseFrame {

    public MenuPrincipalGui(Gestion_Biblioteca gestion) {
        super("Sis B", gestion);
        setSize(450, 320);
        setResizable(false);
        inicializarComponentes();
    }
    
    private void inicializarComponentes(){
        setLayout(new BorderLayout(10, 10));
        
        JLabel lblTitulo = new JLabel("Gestion de Biblioteca", SwingConstants.CENTER);
        lblTitulo.setFont(ColoresGui.fuenteTitulo);
        lblTitulo.setForeground(ColoresGui.colorPrimario);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(10, 40, 15, 40));
        add(lblTitulo, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        
        JButton btnCatalogo = crearBoton("Catalogo");
        
        
    }
    
    private JButton crearBoton(String texto){
        JButton btn = new JButton(texto);
        btn.setFont(ColoresGui.fuenteBoton);
        btn.setBackground(ColoresGui.colorPrimario);
        btn.setForeground(ColoresGui.colorBotonTexto);
        btn.setOpaque(true);
        btn.setBorderPainted(false);
        return btn;
    }
    
    @Override
    public void mostrar(){
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
