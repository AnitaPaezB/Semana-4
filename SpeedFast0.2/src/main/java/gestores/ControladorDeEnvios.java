package gestores;

import interfaces.Rastreable;
import modelos.Pedido;
import java.util.ArrayList;
import java.util.List;

public class ControladorDeEnvios implements Rastreable {

    /**
     * Listas
     */

    private List<Pedido> pedidos = new ArrayList<>();
    private List<String> historialEntregas = new ArrayList<>();

    public void agregarPedido(Pedido pedido) {
        pedidos.add(pedido);
    }

    public void registrarDespacho(Pedido pedido) {
        if (pedido.despachar()) {
            historialEntregas.add("- " + pedido.getClass().getSimpleName() + " #" + pedido.getIdPedido() +
                    " - entregado por " + pedido.getRepartidorAsignado());
        }
    }

    @Override
    public List<String> verHistorial() {
        return new ArrayList<>(historialEntregas);
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }
}
