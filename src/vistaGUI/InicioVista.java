
package vistaGUI;

import controlador.Gestion_Biblioteca;
import java.awt.*;
import javax.swing.*;

public class InicioVista extends BaseFrame {

    public InicioVista(Gestion_Biblioteca gestion) {
        super("Sistema de Gestión Biblioteca", gestion);
    }
      
    @Override
    public void mostrar(){
        setSize(450, 320);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        getContentPane().removeAll();
        
        JLabel lblTitulo = new JLabel("Gestion de Biblioteca", SwingConstants.CENTER);
        lblTitulo.setFont(ColoresGui.fuenteTitulo);
        lblTitulo.setForeground(ColoresGui.colorPrimario);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 20, 10, 20));
        add(lblTitulo, BorderLayout.NORTH);
        
        JPanel panelBotones = new JPanel(new GridLayout(4, 1, 10, 10));
        panelBotones.setBorder(BorderFactory.createEmptyBorder(10, 40, 15, 40));
        panelBotones.setBackground(ColoresGui.colorFondo);
        
        JButton btnCatalogo = crearBoton("Catalogo y Listados");
        JButton btnRegistrar = crearBoton("Registrar Productos");
        JButton btnPrestamos = crearBoton("Prestamos y Devoluciones");
        JButton btnSalir = crearBoton("Guardar y Salir");
        
        btnCatalogo.addActionListener(e ->{
            dispose();
        });
        
        btnRegistrar.addActionListener(e ->{
            dispose();
            new RegistroProductos(gestion).mostrar();
        });
        
        btnPrestamos.addActionListener(e ->{
            dispose();
        });
        
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
        
        revalidate();
        repaint();
        setLocationRelativeTo(null);
        setVisible(true);
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
}
