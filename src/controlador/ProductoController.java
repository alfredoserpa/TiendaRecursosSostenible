package controlador;

import modelo.Producto;
import modelo.ProductoDAO;
import vista.MainView;

import javax.swing.*;
import java.util.List;

public class ProductoController {
    private ProductoDAO productoDAO;
    private MainView mainView;

    public ProductoController(ProductoDAO productoDAO, MainView mainView) {
        this.productoDAO = productoDAO;
        this.mainView = mainView;
        initController();
        cargarProductos();
    }

    private void initController() {
        mainView.getBtnAgregarAlCarrito().addActionListener(e -> agregarProductoAlCarrito());
    }

    public void cargarProductos() {
        List<Producto> listaProductos = productoDAO.obtenerProductos();
        mainView.actualizarTabla(listaProductos);
    }

    private void agregarProductoAlCarrito() {
        int selectedRow = mainView.getTableProductos().getSelectedRow();
        if (selectedRow >= 0) {
            int idProducto = (int) mainView.getTableProductos().getValueAt(selectedRow, 0);
            String nombre = (String) mainView.getTableProductos().getValueAt(selectedRow, 1);
            double precio = (double) mainView.getTableProductos().getValueAt(selectedRow, 2);
            int stockDisponible = (int) mainView.getTableProductos().getValueAt(selectedRow, 3);

            String cantidadStr = JOptionPane.showInputDialog("Ingrese la cantidad:");
            int cantidad;
            try {
                cantidad = Integer.parseInt(cantidadStr);
                if (cantidad > stockDisponible) {
                    JOptionPane.showMessageDialog(mainView, "Cantidad no disponible en stock.");
                    return;
                }
                if (cantidad <= 0) {
                    JOptionPane.showMessageDialog(mainView, "Ingrese una cantidad válida.");
                    return;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(mainView, "Cantidad inválida.");
                return;
            }

            // Crear el producto seleccionado
            Producto productoSeleccionado = new Producto(idProducto, nombre, precio, stockDisponible);

            // Notificar al CarritoController para agregar el producto
            CarritoController.getInstance().agregarProductoAlCarrito(productoSeleccionado, cantidad);

            // Actualizar stock en base de datos
            int nuevoStock = stockDisponible - cantidad;
            productoDAO.actualizarStock(idProducto, nuevoStock);
            cargarProductos();

            JOptionPane.showMessageDialog(mainView, "Producto añadido al carrito.");
        } else {
            JOptionPane.showMessageDialog(mainView, "Seleccione un producto.");
        }
    }
}
