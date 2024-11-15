import controlador.AdminController;
import controlador.CarritoController;
import controlador.ProductoController;
import modelo.ProductoDAO;
import vista.MainView;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            // Mostrar menú inicial
            String[] opciones = {"Cliente", "Administrador"};
            int eleccion = JOptionPane.showOptionDialog(null, "Seleccione el modo de ingreso:", "Inicio",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);

            if (eleccion == 0) {
                // Modo Cliente
                MainView mainView = new MainView();
                ProductoDAO productoDAO = new ProductoDAO();

                ProductoController productoController = new ProductoController(productoDAO, mainView);
                CarritoController carritoController = CarritoController.getInstance();
                carritoController.initController(mainView);

                mainView.setVisible(true);

            } else if (eleccion == 1) {
                // Modo Administrador
                AdminController adminController = new AdminController();
                adminController.mostrarLogin();
            }
        });
    }
}
