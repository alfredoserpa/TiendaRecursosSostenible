package vista;

import modelo.Producto;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CarritoView extends JFrame {
    private JTable tableCarrito;
    private JLabel lblTotal;
    private JButton btnGenerarBoleta;
    private DefaultTableModel modeloTabla;

    public CarritoView(List<Producto> productos, double total) {
        setTitle("Carrito de Compras");
        setSize(600, 400);

        String[] columnas = {"ID", "Nombre", "Precio", "Cantidad"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tableCarrito = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tableCarrito);

        for (Producto p : productos) {
            Object[] fila = {p.getIdProducto(), p.getNombre(), p.getPrecio(), p.getStock()};
            modeloTabla.addRow(fila);
        }

        lblTotal = new JLabel("Total a pagar: $" + String.format("%.2f", total));
        btnGenerarBoleta = new JButton("Generar Boleta");

        JPanel panelInferior = new JPanel();
        panelInferior.add(lblTotal);
        panelInferior.add(btnGenerarBoleta);

        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(panelInferior, BorderLayout.SOUTH);
    }

    public JButton getBtnGenerarBoleta() {
        return btnGenerarBoleta;
    }
}
