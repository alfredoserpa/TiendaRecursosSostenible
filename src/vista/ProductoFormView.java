package vista;

import javax.swing.*;
import java.awt.*;

public class ProductoFormView extends JFrame {
    private JTextField txtNombre;
    private JTextField txtPrecio;
    private JTextField txtStock;
    private JButton btnGuardar;

    public ProductoFormView(String titulo) {
        setTitle(titulo);
        setSize(300, 250);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes
        JLabel lblNombre = new JLabel("Nombre:");
        JLabel lblPrecio = new JLabel("Precio:");
        JLabel lblStock = new JLabel("Stock:");

        txtNombre = new JTextField(20);
        txtPrecio = new JTextField(10);
        txtStock = new JTextField(10);

        btnGuardar = new JButton("Guardar");

        // Layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        gbc.insets = new Insets(5,5,5,5);
        gbc.anchor = GridBagConstraints.WEST;

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblNombre, gbc);

        gbc.gridx = 1; gbc.gridy = 0;
        panel.add(txtNombre, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblPrecio, gbc);

        gbc.gridx = 1; gbc.gridy = 1;
        panel.add(txtPrecio, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblStock, gbc);

        gbc.gridx = 1; gbc.gridy = 2;
        panel.add(txtStock, gbc);

        gbc.gridx = 1; gbc.gridy = 3;
        panel.add(btnGuardar, gbc);

        getContentPane().add(panel);
    }

    // Getters
    public JTextField getTxtNombre() { return txtNombre; }
    public JTextField getTxtPrecio() { return txtPrecio; }
    public JTextField getTxtStock() { return txtStock; }
    public JButton getBtnGuardar() { return btnGuardar; }
}
