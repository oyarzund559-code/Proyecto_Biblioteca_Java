
package vistaGUI;

import controlador.Gestion_Biblioteca;
import java.awt.*;
import javax.swing.*;

public class InicioVista extends BaseFrame {

    public InicioVista(Gestion_Biblioteca gestion) {
        super("Sistema de Gestión Biblioteca", gestion);
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
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 10, 15, 40));
        panelBotones.setBackground(ColoresGui.colorFondo);
        
        JButton btnCatalogo = crearBoton("Catalogo y Listados");
        JButton btnRegistrar = crearBoton("Registrar Productos");
        JButton btnPrestamos = crearBoton("Prestamos y Devoluciones");
        JButton btnSalir = crearBoton("Guardar y Salir");
        
        btnSalir.addActionListener(e ->{
            int r = JOptionPane.showConfirmDialog(
                    this, "¿Deseas guardar los datos y salir", "Confirmar", JOptionPane.YES_NO_OPTION
            );
            if (r == JOptionPane.YES_OPTION){
                gestion.guardar();
                dispose();
                System.exit(0);
            }
       });
        
        panelBotones.add(btnCatalogo);
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnPrestamos);
        panelBotones.add(btnSalir);
        
        add(panelBotones, BorderLayout.CENTER);
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
