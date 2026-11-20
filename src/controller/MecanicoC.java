
package controller;

import java.util.ArrayList;
import java.util.List;

import model.Mecanico;
import model.Vehiculo;
import view.VentanaResultados;

public class MecanicoC {

    private Archivos gestor;
    private VentanaResultados vista;

    public MecanicoC(VentanaResultados vista) {
        gestor = new Archivos();
        this.vista = vista;
    }

    public void ejecutarProcesos() {

        List<Mecanico> mecanicos =
                gestor.leerMecanicos("mecanicos.txt");

        List<Vehiculo> vehiculos =
                gestor.leerVehiculos("vehiculos.txt");

        if (mecanicos.size() != 10) {
            System.out.println("Se necesitan 10 mecanicos.");
            return;
        }

        if (vehiculos.size() != 50) {
            System.out.println("Se necesitan 50 vehiculos.");
            return;
        }

        List<Thread> hilos = new ArrayList<>();

        for (int i = 0; i < mecanicos.size(); i++) {

            List<Vehiculo> asignados = vehiculos.subList(
                    i * 5, (i + 1) * 5
            );

            ProcesoMecanico proceso =
                    new ProcesoMecanico(
                            mecanicos.get(i),
                            asignados,
                            vista
                    );

            Thread hilo = new Thread(proceso);

            hilos.add(hilo);
            hilo.start();
        }

        for (Thread hilo : hilos) {

            try {
                hilo.join();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Proceso interrumpido.");
                return;
            }
        }

        System.out.println("\nTodos los procesos finalizaron.");
    }
}
