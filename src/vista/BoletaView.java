package vista;

import modelo.Producto;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class BoletaView extends JFrame {
    public BoletaView(List<Producto> productos, double total) {
        setTitle("Boleta de Compra");
        setSize(600, 400);

        JTextArea areaBoleta = new JTextArea();
        areaBoleta.setEditable(false);

        StringBuilder sb = new StringBuilder();
        sb.append("***** Boleta de Compra *****\n\n");
        for (Producto p : productos) {
            sb.append(p.getNombre())
              .append(" x ")
              .append(p.getStock())
              .append(" = $")
              .append(String.format("%.2f", p.getPrecio() * p.getStock()))
              .append("\n");
        }
        sb.append("\nTotal: $").append(String.format("%.2f", total));

        areaBoleta.setText(sb.toString());

        getContentPane().add(new JScrollPane(areaBoleta), BorderLayout.CENTER);
    }
}
