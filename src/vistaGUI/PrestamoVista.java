
package vistaGUI;

import controlador.Gestion_Biblioteca;
import javax.swing.*;
import java.awt.*;
import excepciones.LimitePrestamosExcedidoException;
import excepciones.ProductoNoDisponibleException;

public class PrestamoVista extends BaseFrame {

    public PrestamoVista(Gestion_Biblioteca gestion) {
        super("Prestamos y Devoluciones", gestion);
    }
    
    @Override
    public void mostrar(){
        setSize(480, 290);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        getContentPane().removeAll();
        
        JLabel lblTitulo = new JLabel("Prestamos y Devoluciones", SwingConstants.CENTER);
        lblTitulo.setFont(ColoresGui.fuenteTitulo);
        lblTitulo.setForeground(ColoresGui.colorPrimario);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));
        add(lblTitulo, BorderLayout.NORTH);
        
        JPanel panelCentro = new JPanel(new GridLayout(3, 2, 10, 10));
        panelCentro.setBackground(ColoresGui.colorFondo);
        panelCentro.setBorder(BorderFactory.createEmptyBorder(10, 30, 10, 30));
        
        JTextField txtRut = new JTextField();
        txtRut.setFont(ColoresGui.fuenteNormal);
        
        JTextField txtIdProducto = new JTextField();
        txtIdProducto.setFont(ColoresGui.fuenteNormal);
        
        JPanel panelRadio = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 0));
        panelRadio.setBackground(ColoresGui.colorFondo);
        
        JRadioButton rbPrestar = new JRadioButton("Prestamo", true);
        JRadioButton rbDevolver = new JRadioButton("Devolucion");
        rbPrestar.setFont(ColoresGui.fuenteNormal);
        rbPrestar.setBackground(ColoresGui.colorFondo);
        rbDevolver.setFont(ColoresGui.fuenteNormal);
        rbDevolver.setBackground(ColoresGui.colorFondo);
        
        ButtonGroup grupo = new ButtonGroup();
        grupo.add(rbPrestar);
        grupo.add(rbDevolver);
        
        panelRadio.add(rbPrestar);
        panelRadio.add(rbDevolver);
        
        panelCentro.add(crearEtiqueta("Rut Cliente:"));
        panelCentro.add(txtRut);
        panelCentro.add(crearEtiqueta("ID Producto (ej. Prod-1)"));
        panelCentro.add(txtIdProducto);
        panelCentro.add(crearEtiqueta("Operacion:"));
        panelCentro.add(panelRadio);
        
        add(panelCentro, BorderLayout.CENTER);
        
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelSur.setBackground(ColoresGui.colorFondo);
        
        JButton btnEjecutar = crearBoton("Confirmar");
        JButton btnVolver = crearBoton("Volver");
        
        btnEjecutar.addActionListener(e -> {
            String rut = txtRut.getText().trim();
            String idProd = txtIdProducto.getText().trim();
            
            if (rut.isEmpty() || idProd.isEmpty()){
                JOptionPane.showMessageDialog(
                        this, "Ingresa el Rut del Cliente y el ID del producto", "Campos incompletos", JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            String accion = rbPrestar.isSelected() ? "prestamo" : "devolucion";
            int resp = JOptionPane.showConfirmDialog(
                    this, "Confirmar " + accion + " para el Rut " + rut + " y el producto " + idProd + "?", "Confirmar operacion", JOptionPane.YES_NO_OPTION
            );
            if (resp != JOptionPane.YES_OPTION){
                return;
            }
            
            try{
                if (rbPrestar.isSelected()){
                    gestion.agregarPrestamo(rut, idProd);
                    JOptionPane.showMessageDialog(
                            this, "Prestamo registrado correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE
                    );
                }else{
                    gestion.registrarDevolucion(rut, idProd);
                    JOptionPane.showMessageDialog(
                            this, "Devolucion registrada correctamente", "Exito", JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } catch(Exception ex){
                JOptionPane.showMessageDialog(
                        this, "Error al procesar la operacion " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE
                );
            }
        });
        
        btnVolver.addActionListener(e -> {
            dispose();
            new InicioVista(gestion).mostrar();
        });
        
        panelSur.add(btnEjecutar);
        panelSur.add(btnVolver);
        add(panelSur, BorderLayout.SOUTH);
        
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
    
    private JLabel crearEtiqueta(String texto){
        JLabel lbl = new JLabel(texto);
        lbl.setFont(ColoresGui.fuenteNormal);
        lbl.setForeground(ColoresGui.colorTexto);
        return lbl;
    }
    
}
