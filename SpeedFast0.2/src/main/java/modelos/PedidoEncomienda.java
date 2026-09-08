package modelos;

import interfaces.Cancelable;
import interfaces.Despachable;

public class PedidoEncomienda extends Pedido implements Despachable, Cancelable {

    /**
     * Atributo
     */

    private double pesoKg;

    /**
     * Constructor
     * @param idPedido
     * @param direccionEntrega
     * @param distanciaKm
     * @param pesoKg
     */

    public PedidoEncomienda(int idPedido, String direccionEntrega, double distanciaKm, double pesoKg) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.pesoKg = pesoKg;
    }

    /**
     * Polimorfismo y Cálculo del tiempo
     */

    @Override
    public void asignarRepartidor() {
        this.repartidorAsignado = "Repartidor de Encomiendas (Furgón - Automatico)";
    }

    @Override
    public int calcularTiempoEntrega() {
        return (int) (distanciaKm * 5) + (int) + (pesoKg * 2);
    }
}
