package modelos;

import interfaces.Cancelable;
import interfaces.Despachable;

public class PedidoExpress extends Pedido implements Despachable, Cancelable {

    /**
     * Atributo
     */

    private boolean esPrioritario;

    /**
     * Constructor
     * @param idPedido
     * @param direccionEntrega
     * @param distanciaKm
     * @param esPrioritario
     */

    public PedidoExpress(int idPedido, String direccionEntrega, double distanciaKm, boolean esPrioritario) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.esPrioritario = esPrioritario;
    }

    /**
     * Polimorfismo y cálculo del tiempo
     */

    @Override
    public void asignarRepartidor() {
        this.repartidorAsignado = "Repartidor Express (Bisicleta/Moto Rápida - Automático)";
    }

    @Override
    public int calcularTiempoEntrega() {
        int base = (int) (distanciaKm * 3);
        return esPrioritario ? Math.max(base, 10) : base + 10;
    }
}
