package vista;

import modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class MainView extends JFrame {
    private JTable tableProductos;
    private JButton btnAgregarAlCarrito;
    private JButton btnVerCarrito;

    private DefaultTableModel modeloTabla;

    public MainView() {
        setTitle("Tienda Bodega");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Configuración de la tabla
        String[] columnas = {"ID", "Nombre", "Precio", "Stock"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tableProductos = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tableProductos);

        // Botones
        btnAgregarAlCarrito = new JButton("Agregar al Carrito");
        btnVerCarrito = new JButton("Ver Carrito");

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregarAlCarrito);
        panelBotones.add(btnVerCarrito);

        // Layout
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelBotones, BorderLayout.SOUTH);
    }

    public void actualizarTabla(List<Producto> listaProductos) {
        modeloTabla.setRowCount(0); // Limpiar tabla
        for (Producto p : listaProductos) {
            Object[] fila = {p.getIdProducto(), p.getNombre(), p.getPrecio(), p.getStock()};
            modeloTabla.addRow(fila);
        }
    }

    // Getters para los componentes
    public JTable getTableProductos() {
        return tableProductos;
    }

    public JButton getBtnAgregarAlCarrito() {
        return btnAgregarAlCarrito;
    }

    public JButton getBtnVerCarrito() {
        return btnVerCarrito;
    }
}
