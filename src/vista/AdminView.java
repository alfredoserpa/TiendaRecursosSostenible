package vista;

import modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminView extends JFrame {
    private JTable tableProductos;
    private JButton btnAgregar;
    private JButton btnEditar;
    private JButton btnEliminar;

    private DefaultTableModel modeloTabla;

    public AdminView() {
        setTitle("Panel de Administración");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Configuración de la tabla
        String[] columnas = {"ID", "Nombre", "Precio", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tableProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tableProductos);

        // Botones
        btnAgregar = new JButton("Agregar Producto");
        btnEditar = new JButton("Editar Producto");
        btnEliminar = new JButton("Eliminar Producto");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar);

        // Layout
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }

    public void actualizarTabla(List<Producto> listaProductos) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        for (Producto p : listaProductos) {
            Object[] fila = {
                p.getIdProducto(),
                p.getNombre(),
                p.getPrecio(),
                p.getStock()
            };
            modeloTabla.addRow(fila);
        }
    }

    // Getters
    public JTable getTableProductos() { return tableProductos; }
    public JButton getBtnAgregar() { return btnAgregar; }
    public JButton getBtnEditar() { return btnEditar; }
    public JButton getBtnEliminar() { return btnEliminar; }
}
