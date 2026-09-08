# SpeedFast - Sistema de Gestión y Simulación de Envíos Concurrentes

Este repositorio contiene la solución desarrollada para la asignatura **Desarrollo Orientado a Objetos II** de Duoc UC (Semana 4).

El proyecto amplía la arquitectura del sistema **SpeedFast**, incorporando **programación concurrente y multihilo en Java** mediante el uso de `Runnable` y `ExecutorService`.

---

## 📌 Propósito del Proyecto

El objetivo principal de esta entrega es simular un entorno de entregas simultáneas y en tiempo real, donde múltiples repartidores procesan de forma independiente y concurrente sus listas de pedidos asignados.

Además, el diseño atiende las recomendaciones de arquitectura orientada a objetos:
- **Desacoplamiento profundo:** Uso explícito de variables de tipo interfaz (`Despachable`, `Cancelable`, `Rastreable`).
- **Polimorfismo y Herencia:** Jerarquía de clases basada en la clase abstracta `Pedido`.
- **Concurrencia Segura:** Control de hilos y ciclo de vida de ejecución gestionados mediante `ExecutorService`.

---

## 🏗️ Estructura del Proyecto

```text
src/main/java/
├── gestores/
│   └── ControladorDeEnvios.java
├── interfaces/
│   ├── Cancelable.java
│   ├── Despachable.java
│   └── Rastreable.java
├── modelos/
│   ├── Pedido.java (Abstracta)
│   ├── PedidoComida.java
│   ├── PedidoEncomienda.java
│   ├── PedidoExpress.java
│   └── Repartidor.java (Implementa Runnable)
└── Main.java
