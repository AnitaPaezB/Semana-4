package modelos;

import interfaces.Cancelable;
import interfaces.Despachable;

public abstract class Pedido implements Despachable, Cancelable {

    /**
     * Atributos
     */

    protected int idPedido;
    protected String direccionEntrega;
    protected double distanciaKm;
    protected String repartidorAsignado;
    protected boolean estadoDespachado;
    protected boolean estadoCancelado;

    /**
     * Constructor
     *
     * @param idPedido
     * @param direccionEntrega
     * @param distanciaKm
     */

    public Pedido(int idPedido, String direccionEntrega, double distanciaKm) {
        this.idPedido = idPedido;
        this.direccionEntrega = direccionEntrega;
        this.distanciaKm = distanciaKm;
        this.repartidorAsignado = "Sin Asignar";
        this.estadoDespachado = false;
        this.estadoCancelado = false;
    }

    /**
     * Polimorfismo
     */

    public void asignarRepartidor() {
        this.repartidorAsignado = "Repartidor Sistema Automático";
    }

    public void asignarRepartidor(String nombreRepartidor) {
        this.repartidorAsignado = nombreRepartidor;
    }

    /**
     * Abstraccion
     * @return
     */

    public abstract int calcularTiempoEntrega();

    public void mostrarResumen() {

        System.out.println("----------------------------------------");
        System.out.println("ID Pedido: #" + idPedido);
        System.out.println("Dirección: " + direccionEntrega);
        System.out.println("Distancia: " + distanciaKm + " km");
        System.out.println("Repartidor: " + repartidorAsignado);
        System.out.println("Tiempo Estimado: " + calcularTiempoEntrega() + " minutos");
        System.out.println("Estado: " + (estadoCancelado ? "CANCELADO" : (estadoDespachado ? "DESPACHADO" : "PENDIENTE")));
    }

    /**
     * Cancelable
     * @param motivo
     * @return
     */

    @Override
    public boolean cancelar(String motivo) {

        if (estadoDespachado) {
            System.out.println("Error: no se puede cancelar el pedido #" + idPedido + " porque ya fue despachado.");
            return false;
        }

        this.estadoCancelado = true;
        System.out.println("Cancelando Pedido #" + idPedido + " ... -> Canselado exitosamente. Motivo: " + motivo);
        return true;
    }

    /**
     * Despachable
     * @return
     */

    @Override
    public  boolean despachar() {

        if (estadoCancelado) {
            System.out.println("Error: No se puede despachar el pedido #" + idPedido + " porque está cancelado.");
            return false;
        }

        this.estadoDespachado = true;
        System.out.println("Pedido #" + idPedido + " despachado correctamente.");
        return true;
    }

    /**
     * Getters
     * @return
     */

    public int getIdPedido() { return idPedido; }
    public String getRepartidorAsignado() { return repartidorAsignado; }

    public String getDireccionEntrega() {
        return direccionEntrega;
    }

    public boolean isEstadoDespachado() { return estadoDespachado; }
    public boolean isEstadoCancelado() { return estadoCancelado; }
}
