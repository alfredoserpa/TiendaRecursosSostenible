package modelo;

import java.util.ArrayList;
import java.util.List;

public class Carrito {
    private List<Producto> productos;

    public Carrito() {
        productos = new ArrayList<>();
    }

    public void agregarProducto(Producto producto, int cantidad) {
        for (Producto p : productos) {
            if (p.getIdProducto() == producto.getIdProducto()) {
                p.setStock(p.getStock() + cantidad);
                return;
            }
        }
        Producto productoEnCarrito = new Producto(
                producto.getIdProducto(),
                producto.getNombre(),
                producto.getPrecio(),
                cantidad
        );
        productos.add(productoEnCarrito);
    }

    public double calcularTotal() {
        double total = 0;
        for (Producto p : productos) {
            total += p.getPrecio() * p.getStock();
        }
        return total;
    }

    public List<Producto> getProductos() {
        return productos;
    }
}
