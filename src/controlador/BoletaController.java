package controlador;

import modelo.Producto;
import vista.BoletaView;

import java.util.List;

public class BoletaController {
    private List<Producto> productos;
    private double total;
    private BoletaView boletaView;

    public BoletaController(List<Producto> productos, double total) {
        this.productos = productos;
        this.total = total;
        this.boletaView = new BoletaView(productos, total);
    }

    public void mostrarBoleta() {
        boletaView.setVisible(true);
    }
}
