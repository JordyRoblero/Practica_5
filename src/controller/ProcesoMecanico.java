package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import model.Mecanico;
import model.Vehiculo;
import model.GasesD;
import view.VentanaResultados;

public class ProcesoMecanico implements Runnable {

    private Mecanico mecanico;
    private List<Vehiculo> vehiculos;
    private EvaluadorGases evaluador;
    private VentanaResultados vista;

    public ProcesoMecanico(Mecanico mecanico,
            List<Vehiculo> vehiculos, VentanaResultados vista) {

        this.mecanico = mecanico;
        this.vehiculos = vehiculos;
        this.vista = vista;
        this.evaluador = new EvaluadorGases();
    }

    @Override
    public void run() {

        Random random = new Random();
        List<GasesD> loteGases = new ArrayList<>();

        System.out.println("\nMECANICO: " + mecanico.getNombre());

        for (Vehiculo vehiculo : vehiculos) {

            try {
                Thread.sleep(random.nextInt(3001));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            GasesD gases = new GasesD(
                random.nextDouble() * 2,
                random.nextDouble() * 5,
                random.nextDouble() * 60,
                random.nextDouble() * 25
            );

            loteGases.add(gases);
        }

        String resultado = evaluador.evaluarLote(loteGases);

        for (int i = 0; i < vehiculos.size(); i++) {

            Vehiculo vehiculo = vehiculos.get(i);
            GasesD gases = loteGases.get(i);

            vista.agregarResultado(
                mecanico.getNombre(),
                vehiculo.getMarca(),
                vehiculo.getPlaca(),
                gases.getLambda(),
                gases.getO2(),
                gases.getN2o(),
                gases.getCo2(),
                resultado
            );
        }

        System.out.println("Resultado: " + resultado);
    }
}
