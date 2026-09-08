
import gestores.ControladorDeEnvios;
import interfaces.Cancelable;
import interfaces.Despachable;
import modelos.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {

    public static void main(String[] args) {
        System.out.println("===========================================");
        System.out.println("   ENTREGAS CONCURRENTE - SPEEDFAST  ");
        System.out.println("===========================================\n");

        // 1. Instanciación de pedidos (usando polimorfismo)
        Pedido pedido1 = new PedidoComida(101, "Av. España 123", 4.5, 18);
        Pedido pedido2 = new PedidoExpress(102, "Calle Brasil 456", 2.0, true);
        Pedido pedido3 = new PedidoEncomienda(103, "Av. Bernardo O'Higgins 789", 12.0, 3.5);

        Pedido pedido4 = new PedidoComida(104, "Calle Machalí 321", 5.0, 20);
        Pedido pedido5 = new PedidoExpress(105, "Av. Freire 654", 1.5, false);
        Pedido pedido6 = new PedidoEncomienda(106, "Calle Millán 987", 8.0, 2.0);

        // 2. Demostración de Desacoplamiento con Interfaces (Atendiendo retroalimentación)
        System.out.println("--- Prueba de Desacoplamiento mediante Interfaces ---");
        Despachable pedidoDespachable = new PedidoExpress(107, "Av. San Martín 111", 3.0, true);
        pedidoDespachable.despachar();

        Cancelable pedidoCancelable = new PedidoExpress(108, "Calle Independencia 222", 2.5, true);
        pedidoCancelable.cancelar("No se pudo despachar");
        System.out.println("----------------------------------------------------\n");

        // 3. Crear listas de pedidos para cada repartidor
        List<Pedido> pedidosRepartidor1 = new ArrayList<>();
        pedidosRepartidor1.add(pedido1);
        pedidosRepartidor1.add(pedido2);

        List<Pedido> pedidosRepartidor2 = new ArrayList<>();
        pedidosRepartidor2.add(pedido3);
        pedidosRepartidor2.add(pedido4);

        List<Pedido> pedidosRepartidor3 = new ArrayList<>();
        pedidosRepartidor3.add(pedido5);
        pedidosRepartidor3.add(pedido6);

        // 4. Crear los objetos Repartidor (Implementan Runnable)
        Repartidor repartidor1 = new Repartidor("Camila", pedidosRepartidor1);
        Repartidor repartidor2 = new Repartidor("Luis", pedidosRepartidor2);
        Repartidor repartidor3 = new Repartidor("Mateo", pedidosRepartidor3);

        // 5. Crear el ExecutorService con un pool de hilos
        // Usamos un pool de 3 hilos para ejecutar a los 3 repartidores en paralelo
        ExecutorService executor = Executors.newFixedThreadPool(3);

        System.out.println("--- Iniciando entregas simultáneas ---\n");

        // 6. Enviar las tareas de los repartidores al ExecutorService
        executor.execute(repartidor1);
        executor.execute(repartidor2);
        executor.execute(repartidor3);

        // 7. Apagar el ExecutorService para que no acepte nuevas tareas
        executor.shutdown();

        // 8. Esperar a que todas las tareas terminen su ejecución
        try {
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
            System.out.println("\n==================================================");
            System.out.println("   TODAS LAS ENTREGAS HAN SIDO COMPLETADAS");
            System.out.println("==================================================");
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}