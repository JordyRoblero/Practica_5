package controller;

import java.util.List;
import model.GasesD;

public class EvaluadorGases {

    public String evaluarLote(List<GasesD> gases) {

        int fallasLambda = 0;
        int fallasO2 = 0;
        int fallasN2O = 0;
        int fallasCO2 = 0;

        for (GasesD dato : gases) {

            if (dato.getLambda() > 1) {
                fallasLambda++;
            }

            if (dato.getO2() >= 2) {
                fallasO2++;
            }

            if (dato.getN2o() >= 40) {
                fallasN2O++;
            }

            if (dato.getCo2() >= 15) {
                fallasCO2++;
            }
        }

        double porcentajeLambda =
                (fallasLambda * 100.0) / gases.size();

        double porcentajeO2 =
                (fallasO2 * 100.0) / gases.size();

        double porcentajeN2O =
                (fallasN2O * 100.0) / gases.size();

        double porcentajeCO2 =
                (fallasCO2 * 100.0) / gases.size();

        System.out.println("\nPORCENTAJES DE ERROR");
        System.out.println("Lambda: " + porcentajeLambda + "%");
        System.out.println("O2: " + porcentajeO2 + "%");
        System.out.println("N2O: " + porcentajeN2O + "%");
        System.out.println("CO2: " + porcentajeCO2 + "%");

        if (porcentajeLambda > 70 ||
            porcentajeO2 > 30 ||
            porcentajeN2O > 80 ||
            porcentajeCO2 > 50) {

            return "Segunda Revision Asignada";
        }

        return "Pasa en Primera Revision";
    }
}