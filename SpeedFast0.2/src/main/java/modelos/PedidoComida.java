package modelos;

public class PedidoComida extends Pedido {

    /**
     * Atributo
     */

    private int tiempoPreparacionMin;

    /**
     * Costructor
     * @param idPedido
     * @param direccionEntrega
     * @param distanciaKm
     * @param tiempoPreparacionMin
     */

    public PedidoComida(int idPedido, String direccionEntrega, double distanciaKm, int tiempoPreparacionMin) {
        super(idPedido, direccionEntrega, distanciaKm);
        this.tiempoPreparacionMin = tiempoPreparacionMin;
    }

    /**
     * Polimorfismo y cálculo del tiempo
     */

    @Override
    public void asignarRepartidor() {
        this.repartidorAsignado = "Repartidor de Comida (Moto _ Automatico)";
    }

    @Override
    public int calcularTiempoEntrega() {
        return tiempoPreparacionMin + (int) (distanciaKm * 4);
    }
}
