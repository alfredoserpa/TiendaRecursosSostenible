package controlador;

import modelo.Carrito;
import modelo.Producto;
import vista.CarritoView;
import vista.MainView;

import javax.swing.*;

public class CarritoController {
    private static CarritoController instance;
    private Carrito carrito;
    private CarritoView carritoView;

    private CarritoController() {
        this.carrito = new Carrito();
    }

    public static CarritoController getInstance() {
        if (instance == null) {
            instance = new CarritoController();
        }
        return instance;
    }

    public void initController(MainView mainView) {
        mainView.getBtnVerCarrito().addActionListener(e -> mostrarCarrito());
    }

    public void agregarProductoAlCarrito(Producto producto, int cantidad) {
        carrito.agregarProducto(producto, cantidad);
    }

    private void mostrarCarrito() {
        double total = carrito.calcularTotal();
        carritoView = new CarritoView(carrito.getProductos(), total);
        carritoView.setVisible(true);

        carritoView.getBtnGenerarBoleta().addActionListener(e -> generarBoleta());
    }

    private void generarBoleta() {
        double total = carrito.calcularTotal();
        BoletaController boletaController = new BoletaController(carrito.getProductos(), total);
        boletaController.mostrarBoleta();
        carritoView.dispose();
    }
}
