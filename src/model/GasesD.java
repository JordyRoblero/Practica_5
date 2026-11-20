package model;


public class GasesD {

    private double lambda;
    private double o2;
    private double n2o;
    private double co2;

    public GasesD(double lambda, double o2, double n2o, double co2) {
        this.lambda = lambda;
        this.o2 = o2;
        this.n2o = n2o;
        this.co2 = co2;
    }

    public double getLambda() {
        return lambda;
    }

    public double getO2() {
        return o2;
    }

    public double getN2o() {
        return n2o;
    }

    public double getCo2() {
        return co2;
    }

}


