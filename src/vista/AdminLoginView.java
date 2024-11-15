package vista;

import javax.swing.*;
import java.awt.*;

public class AdminLoginView extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtContrasena;
    private JButton btnLogin;

    public AdminLoginView() {
        setTitle("Login Administrador");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Componentes
        JLabel lblUsuario = new JLabel("Usuario:");
        JLabel lblContrasena = new JLabel("Contraseña:");

        txtUsuario = new JTextField(15);
        txtContrasena = new JPasswordField(15);

        btnLogin = new JButton("Iniciar Sesión");

        // Layout
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(lblUsuario);
        panel.add(txtUsuario);
        panel.add(lblContrasena);
        panel.add(txtContrasena);
        panel.add(new JLabel());
        panel.add(btnLogin);

        getContentPane().add(panel);
    }

    // Getters
    public JTextField getTxtUsuario() { return txtUsuario; }
    public JPasswordField getTxtContrasena() { return txtContrasena; }
    public JButton getBtnLogin() { return btnLogin; }
}
