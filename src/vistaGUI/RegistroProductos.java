
package vistaGUI;

import controlador.Gestion_Biblioteca;
import java.awt.*;
import javax.swing.*;
import modelo.*;

public class RegistroProductos extends BaseFrame{

    public RegistroProductos(Gestion_Biblioteca gestion) {
        super("Registrar Producto", gestion);
    }
    
    @Override
    public void mostrar(){
        setSize(480, 330);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        getContentPane().removeAll();
        
        JLabel lblTitulo = new JLabel("Registrar Producto", SwingConstants.CENTER);
        lblTitulo.setFont(ColoresGui.fuenteTitulo);
        lblTitulo.setForeground(ColoresGui.colorPrimario);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));
        add(lblTitulo, BorderLayout.NORTH);
        
        JPanel panelCentro = new JPanel(new GridLayout(5, 2, 10, 10));
        panelCentro.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));
        panelCentro.setBackground(ColoresGui.colorFondo);
        
        JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Libro", "DVD"});
        cmbTipo.setFont(ColoresGui.fuenteNormal);
        cmbTipo.setForeground(ColoresGui.colorTexto);
        
        JTextField txtTitulo = new JTextField();
        txtTitulo.setFont(ColoresGui.fuenteNormal);
        
        JLabel lblCreador = crearEtiqueta("Autor: ");
        JTextField txtCreador = new JTextField();
        txtCreador.setFont(ColoresGui.fuenteNormal);
        
        JLabel lblExtension = crearEtiqueta("Numero de paginas: ");
        JTextField txtExtension = new JTextField();
        lblExtension.setFont(ColoresGui.fuenteNormal);
        
        JCheckBox chkNovedad = new JCheckBox("Marcar como novedad");
        chkNovedad.setFont(ColoresGui.fuenteNormal);
        chkNovedad.setBackground(ColoresGui.colorFondo);
        chkNovedad.setForeground(ColoresGui.colorTexto);
        
        cmbTipo.addActionListener(e ->{
            if("DVD".equals(cmbTipo.getSelectedItem())) {
                lblCreador.setText("Director:");
                lblExtension.setText("Duracion:");
            } else{
                lblCreador.setText("Autor:");
                lblExtension.setText("Numero de paginas:");
            }
        });
        
        panelCentro.add(crearEtiqueta("Tipo de Producto:"));
        panelCentro.add(cmbTipo);
        panelCentro.add(crearEtiqueta("Titulo:"));
        panelCentro.add(txtTitulo);
        panelCentro.add(lblCreador);
        panelCentro.add(txtCreador);
        panelCentro.add(lblExtension);
        panelCentro.add(txtExtension);
        panelCentro.add(crearEtiqueta("Etiqueta:"));
        panelCentro.add(chkNovedad);
        
        add(panelCentro, BorderLayout.CENTER);
        
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 12));
        panelBotones.setBackground(ColoresGui.colorFondo);
        
        JButton btnRegistrar = crearBoton("Registrar");
        JButton btnVolver = crearBoton("Volver");
        
        btnRegistrar.addActionListener(e -> {
            String tipo = (String) cmbTipo.getSelectedItem();
            String titulo = txtTitulo.getText().trim();
            String creador = txtCreador.getText().trim();
            String extensionTexto = txtExtension.getText().trim();
            boolean esNovedad = chkNovedad.isSelected();
            
            if (titulo.isEmpty() || creador.isEmpty() || extensionTexto.isEmpty()){
                JOptionPane.showMessageDialog(
                        this, "Completa todos los campos para continuar", "Campos incompletos", JOptionPane.WARNING_MESSAGE
                );
                return;
            }
            
            int cantidad;
            try{
                cantidad = Integer.parseInt(extensionTexto);
                if (cantidad <= 0){
                    JOptionPane.showMessageDialog(
                        this, "El valor debe ser mayor que 0", "Valor invalido", JOptionPane.WARNING_MESSAGE
                );
                    return;
                } 
            } catch (NumberFormatException ex){
                JOptionPane.showMessageDialog(
                        this, "Ingresa un numero entero valido", "Formato invalido", JOptionPane.WARNING_MESSAGE
                );
                    return;
            }
            
            int confirmacion = JOptionPane.showConfirmDialog(
                    this, "¿Deseas registrar este " + titulo + "? \n" + "Titulo: " + titulo + "\n" + lblCreador.getText() + " " + creador + "\n" + lblExtension.getText() + " " + cantidad + (esNovedad ? "\nMarcado como novedad" : ""), "Confirmar registro", JOptionPane.YES_NO_OPTION 
            );
            
            if (confirmacion != JOptionPane.YES_OPTION){
                return;
            }
            
            try{
                Producto nuevoProd = "DVD".equals(tipo) ? new DVD(titulo, creador, cantidad) : new Libro(titulo, creador, cantidad);
                gestion.agregarProductos(nuevoProd);
                JOptionPane.showMessageDialog(
                this, tipo + "registrado con exido con un ID de " + nuevoProd.getId(), "Registro exitoso", JOptionPane.INFORMATION_MESSAGE
                );
            
                txtTitulo.setText("");
                txtCreador.setText("");
                txtExtension.setText("");
                chkNovedad.setSelected(false);
                cmbTipo.setSelectedIndex(0);
            } catch (Exception ex){
                JOptionPane.showMessageDialog(
                        this, "Error al registrar el producto: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE
                );
            }
        });
        
        btnVolver.addActionListener(e -> {
            dispose();
            new InicioVista(gestion).mostrar();
        });
        
        panelBotones.add(btnRegistrar);
        panelBotones.add(btnVolver);
        add(panelBotones, BorderLayout.SOUTH);
        
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
