package modelos;

import interfaces.Despachable;

import java.util.List;
import java.util.Random;

public class Repartidor implements Runnable {

    private String nombre;
    private List<Pedido> pedidosAsignados;
    private Random random;

    public Repartidor(String nombre, List<Pedido> pedidosAsignados) {
        this.nombre = nombre;
        this.pedidosAsignados = pedidosAsignados;
        this.random = new Random();
    }

    public String getNombre() {
        return nombre;
    }

    @Override
    public void run() {
        System.out.println(">>> [" + nombre + "] ha iniciado su turno de entregas.");

        for (Pedido pedido : pedidosAsignados) {
            try {
                System.out.println("[" + nombre + "] Entregando " + pedido.getClass().getSimpleName()
                + " #" + pedido.getIdPedido() + " a: " + pedido.getDireccionEntrega());

                if (pedido instanceof Despachable) {
                    ((Despachable) pedido).despachar();
                }

                int tiempoSimulado = (random.nextInt(3) + 1) * 1000;
                Thread.sleep(tiempoSimulado);

                System.out.println("✔ [" + nombre + "] Pedido #" + pedido.getIdPedido()
                        + " entregado exitosamente.");
            } catch (InterruptedException e) {
                System.err.println("❌ [" + nombre + "] La entrega del pedido #"
                        + pedido.getIdPedido() + " fue interrumpida.");
                break;
            }
        }

        System.out.println("<<< [" + nombre + "] ha finalizado todos sus pedidos asignados.");
    }
}
