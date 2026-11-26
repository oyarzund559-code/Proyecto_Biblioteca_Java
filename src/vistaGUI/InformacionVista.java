
package vistaGUI;

import controlador.Gestion_Biblioteca;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.*;

public class InformacionVista extends BaseFrame{
        private DefaultTableModel modeloProductos;
        private JTable tablaProductos;
        
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
        modeloProductos = crearModeloNoEditable(colProductos);
        tablaProductos = crearTabla(modeloProductos);
        cargarTabla();
        pestañas.add("Productos", new JScrollPane(tablaProductos));
        
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
        JButton btnEliminar = crearBoton("Eliminar una fila");
        
        btnActualizar.addActionListener(e -> mostrar());
        
        btnVolver.addActionListener(e -> {
            dispose();
            new InicioVista(gestion).mostrar();
        });
        
        btnEliminar.addActionListener(e -> eliminarProducto());
        
        panelSur.add(btnEliminar);
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
   
   private DefaultTableModel crearModeloNoEditable(String[] columnas) {
        return new DefaultTableModel(columnas, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
    }

    private JTable crearTabla(DefaultTableModel modelo) {
        JTable tabla = new JTable(modelo);
        tabla.setFont(ColoresGui.fuenteNormal);
        tabla.setRowHeight(22);
        tabla.getTableHeader().setFont(ColoresGui.fuenteSubtitulo);
        return tabla;
    }
    
    private void cargarTabla() {
        modeloProductos.setRowCount(0);
        for (Producto p : gestion.getListaProductos()) {
            String estado = p.isPrestado() ? "Prestado" : "Disponible";
            modeloProductos.addRow(new Object[]{
                p.getId(),
                p.getTitulo(),
                p.getTipo(),
                p.calcularDiasPrestado() + " días",
                estado
            });
        }
    }
    
    private void eliminarProducto() {
        int fila = tablaProductos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(
                    this,
                    "Selecciona un producto de la tabla para eliminar.",
                    "Sin selección",
                    JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        String id = modeloProductos.getValueAt(fila, 0).toString();
        String titulo = modeloProductos.getValueAt(fila, 1).toString();

        int resp = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar el producto \"" + titulo + "\" (" + id + ")?",
                "Confirmar eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (resp != JOptionPane.YES_OPTION) {
            return;
        }

        boolean eliminado = gestion.eliminarProducto(id);
        if (eliminado) {
            modeloProductos.removeRow(fila);
            JOptionPane.showMessageDialog(
                    this,
                    "Producto eliminado del catálogo.",
                    "Eliminado",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } else {
            JOptionPane.showMessageDialog(
                    this,
                    "No se pudo eliminar: el producto está actualmente prestado o no existe.",
                    "No eliminado",
                    JOptionPane.WARNING_MESSAGE
            );
        }
    }
}

