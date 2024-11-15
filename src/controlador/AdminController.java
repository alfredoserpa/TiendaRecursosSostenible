package controlador;

import modelo.Administrador;
import modelo.AdministradorDAO;
import modelo.Producto;
import modelo.ProductoDAO;
import vista.AdminLoginView;
import vista.AdminView;
import vista.ProductoFormView;

import javax.swing.*;
import java.util.List;

public class AdminController {
    private AdminLoginView loginView;
    private AdminView adminView;
    private ProductoFormView productoFormView;
    private AdministradorDAO adminDAO;
    private ProductoDAO productoDAO;

    public AdminController() {
        this.loginView = new AdminLoginView();
        this.adminDAO = new AdministradorDAO();
        this.productoDAO = new ProductoDAO();
        initController();
    }

    private void initController() {
        loginView.getBtnLogin().addActionListener(e -> validarCredenciales());
    }

    public void mostrarLogin() {
        loginView.setVisible(true);
    }

    private void validarCredenciales() {
        String usuario = loginView.getTxtUsuario().getText();
        String contrasena = new String(loginView.getTxtContrasena().getPassword());

        Administrador admin = adminDAO.validarCredenciales(usuario, contrasena);
        if (admin != null) {
            JOptionPane.showMessageDialog(loginView, "Login exitoso.");
            loginView.dispose();
            mostrarPanelAdministracion();
        } else {
            JOptionPane.showMessageDialog(loginView, "Credenciales incorrectas.");
        }
    }

    private void mostrarPanelAdministracion() {
        adminView = new AdminView();
        cargarProductos();
        adminView.setVisible(true);

        // Listeners
        adminView.getBtnAgregar().addActionListener(e -> mostrarFormularioAgregar());
        adminView.getBtnEditar().addActionListener(e -> mostrarFormularioEditar());
        adminView.getBtnEliminar().addActionListener(e -> eliminarProducto());
    }

    private void cargarProductos() {
        List<Producto> listaProductos = productoDAO.obtenerProductos();
        adminView.actualizarTabla(listaProductos);
    }

    private void mostrarFormularioAgregar() {
        productoFormView = new ProductoFormView("Agregar Producto");
        productoFormView.setVisible(true);

        productoFormView.getBtnGuardar().addActionListener(e -> agregarProducto());
    }

    private void agregarProducto() {
        String nombre = productoFormView.getTxtNombre().getText();
        double precio;
        int stock;

        try {
            precio = Double.parseDouble(productoFormView.getTxtPrecio().getText());
            stock = Integer.parseInt(productoFormView.getTxtStock().getText());

            Producto producto = new Producto(0, nombre, precio, stock);
            boolean exito = productoDAO.agregarProducto(producto);

            if (exito) {
                JOptionPane.showMessageDialog(productoFormView, "Producto agregado exitosamente.");
                productoFormView.dispose();
                cargarProductos();
            } else {
                JOptionPane.showMessageDialog(productoFormView, "Error al agregar el producto.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(productoFormView, "Datos inválidos.");
        }
    }

    private void mostrarFormularioEditar() {
        int selectedRow = adminView.getTableProductos().getSelectedRow();
        if (selectedRow >= 0) {
            int idProducto = (int) adminView.getTableProductos().getValueAt(selectedRow, 0);
            String nombre = (String) adminView.getTableProductos().getValueAt(selectedRow, 1);
            double precio = (double) adminView.getTableProductos().getValueAt(selectedRow, 2);
            int stock = (int) adminView.getTableProductos().getValueAt(selectedRow, 3);

            productoFormView = new ProductoFormView("Editar Producto");
            productoFormView.getTxtNombre().setText(nombre);
            productoFormView.getTxtPrecio().setText(String.valueOf(precio));
            productoFormView.getTxtStock().setText(String.valueOf(stock));
            productoFormView.setVisible(true);

            productoFormView.getBtnGuardar().addActionListener(e -> editarProducto(idProducto));

        } else {
            JOptionPane.showMessageDialog(adminView, "Seleccione un producto para editar.");
        }
    }

    private void editarProducto(int idProducto) {
        String nombre = productoFormView.getTxtNombre().getText();
        double precio;
        int stock;

        try {
            precio = Double.parseDouble(productoFormView.getTxtPrecio().getText());
            stock = Integer.parseInt(productoFormView.getTxtStock().getText());

            Producto producto = new Producto(idProducto, nombre, precio, stock);
            boolean exito = productoDAO.actualizarProducto(producto);

            if (exito) {
                JOptionPane.showMessageDialog(productoFormView, "Producto actualizado exitosamente.");
                productoFormView.dispose();
                cargarProductos();
            } else {
                JOptionPane.showMessageDialog(productoFormView, "Error al actualizar el producto.");
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(productoFormView, "Datos inválidos.");
        }
    }

    private void eliminarProducto() {
        int selectedRow = adminView.getTableProductos().getSelectedRow();
        if (selectedRow >= 0) {
            int idProducto = (int) adminView.getTableProductos().getValueAt(selectedRow, 0);

            int confirmacion = JOptionPane.showConfirmDialog(adminView, "¿Estás seguro de eliminar este producto?", "Confirmar", JOptionPane.YES_NO_OPTION);
            if (confirmacion == JOptionPane.YES_OPTION) {
                boolean exito = productoDAO.eliminarProducto(idProducto);
                if (exito) {
                    JOptionPane.showMessageDialog(adminView, "Producto eliminado exitosamente.");
                    cargarProductos();
                } else {
                    JOptionPane.showMessageDialog(adminView, "Error al eliminar el producto.");
                }
            }
        } else {
            JOptionPane.showMessageDialog(adminView, "Seleccione un producto para eliminar.");
        }
    }
}
