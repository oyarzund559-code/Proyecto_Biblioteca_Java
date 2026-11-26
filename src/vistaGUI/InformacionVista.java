
package vistaGUI;

import controlador.Gestion_Biblioteca;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.*;

public class InformacionVista extends BaseFrame{

    public InformacionVista(Gestion_Biblioteca gestion) {
        super("Catálogo, Clientes y Prestamos", gestion);
    }
    
    @Override
    public void mostrar(){
        setSize(650, 380);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        getContentPane().removeAll();
        
        JLabel lblTitulo = new JLabel("Listado General de Biblioteca", SwingConstants.CENTER);
        lblTitulo.setFont(ColoresGui.fuenteTitulo);
        lblTitulo.setForeground(ColoresGui.colorPrimario);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12, 20, 5, 20));
        add(lblTitulo, BorderLayout.NORTH);
        
        JTabbedPane pestañas = new JTabbedPane();
        pestañas.setFont(ColoresGui.fuenteSubtitulo);
        
        String[] colProductos = {"ID", "Titulo", "Tipo", "Dias prestamo", "Estado"};
        DefaultTableModel modeloProductos = crearModelo(colProductos);
        for (Producto p : gestion.getListaProductos()){
            String estado = p.isPrestado() ? "Prestado" : "Disponible";
            modeloProductos.addRow(new Object[] { 
                p.getId(), p.getTitulo(), p.getTipo(), p.calcularDiasPrestado() + "días", estado
            });
        }
        pestañas.addTab("Productos", crearTablaScroll(modeloProductos));
        
        String[] colClientes = {"Rut", "Nombre", "Prestamos Activos", "Maximo"};
        DefaultTableModel modeloClientes = crearModelo(colClientes);
        for (Cliente c : gestion.getListaClientes().values()){
                modeloClientes.addRow(new Object[]{
                    c.getRut(), c.getNombre(), c.getContadorPrestamos(), Cliente.Max_Prestamos
            });
        }
        pestañas.addTab("Clientes", crearTablaScroll(modeloClientes));
        
        String[] colPrestamos = {"Rut Cliente", "Cliente", "ID Producto", "Producto"};
        DefaultTableModel modeloPrestamos = crearModelo(colPrestamos);
        for (Prestamo p : gestion.getListaPrestamos()){
                modeloPrestamos.addRow(new Object[]{
                    p.getCliente().getRut(), p.getCliente().getNombre(), p.getProducto().getId(), p.getProducto().getTitulo(), p.getEstado()
            });
        }
        pestañas.addTab("Prestamos", crearTablaScroll(modeloPrestamos));
        
        JPanel panelCentro = new JPanel(new BorderLayout());
        panelCentro.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        panelCentro.setBackground(ColoresGui.colorFondo);
        panelCentro.add(pestañas, BorderLayout.CENTER);
        add(panelCentro, BorderLayout.CENTER);
        
        JPanel panelSur = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        panelSur.setBackground(ColoresGui.colorFondo);
        
        JButton btnActualizar = crearBoton("Actualizar");
        JButton btnVolver = crearBoton("Volver");
        
        btnActualizar.addActionListener(e -> mostrar());
        
        btnVolver.addActionListener(e -> {
            dispose();
            new InicioVista(gestion).mostrar();
        });
        
        panelSur.add(btnActualizar);
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
    
   private DefaultTableModel crearModelo(String[] columnas){
       return new DefaultTableModel(columnas, 0){
           @Override
           public boolean isCellEditable(int row, int colum){
               return false;
           }
       };
   }
   
   private JScrollPane crearTablaScroll(DefaultTableModel modelo){
    JTable tabla = new JTable(modelo);
    tabla.setFont(ColoresGui.fuenteNormal);
    tabla.setRowHeight(22);
    tabla.getTableHeader().setFont(ColoresGui.fuenteSubtitulo);
    return new JScrollPane(tabla);
    }
}
