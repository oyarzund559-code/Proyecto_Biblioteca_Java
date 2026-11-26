
package vistaGUI;

import controlador.Gestion_Biblioteca;
import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import modelo.Producto;

public class CatalogoVista extends BaseFrame{

    public CatalogoVista(Gestion_Biblioteca gestion) {
        super("Catálogo de Productos", gestion);
    }
    
    @Override
    public void mostrar(){
        setSize(650, 380);
        setResizable(false);
        setLayout(new BorderLayout(10, 10));
        getContentPane().removeAll();
        
        JLabel lblTitulo = new JLabel("Catalogo de Biblioteca", SwingConstants.CENTER);
        lblTitulo.setFont(ColoresGui.fuenteTitulo);
        lblTitulo.setForeground(ColoresGui.colorPrimario);
        lblTitulo.setBorder(BorderFactory.createEmptyBorder(12, 20, 5, 20));
        add(lblTitulo, BorderLayout.NORTH);
        
        String[] columnas = {"ID", "Titulo", "Tipo", "Dias prestamo", "Estado"};
        DefaultTableModel modelo = new DefaultTableModel(columnas, 0){
            @Override
            public boolean isCellEditable(int row, int colum){
                return false;
            }
        };
        
        modelo.setRowCount(0);
        for (Producto p : gestion.getListaProductos()){
            String estado = p.isPrestado() ? "Prestado" : "Disponible";
            modelo.addRow(new Object[] { 
                p.getId(), p.getTitulo(), p.getTipo(), p.calcularDiasPrestado() + "días", estado
            });
        }
        
        JTable tabla = new JTable(modelo);
        tabla.setFont(ColoresGui.fuenteNormal);
        tabla.setRowHeight(22);
        tabla.getTableHeader().setFont(ColoresGui.fuenteSubtitulo);
        
        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(5, 20, 5, 20));
        add(scroll, BorderLayout.CENTER);
        
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
}
