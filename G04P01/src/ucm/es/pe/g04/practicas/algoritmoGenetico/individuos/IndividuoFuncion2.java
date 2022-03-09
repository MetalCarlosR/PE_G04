package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Random;

public class IndividuoFuncion2 extends Individuo<Boolean> {

    @Override
    public void init(double precision) {
        this.precision = precision;
        this.tamGenes = new int[2];
        this.min = new double[2];
        this.max = new double[2];
        this.min[0] = -10;
        this.min[1] = -10;
        this.max[0] = 10;
        this.max[1] = 10;
        this.tamGenes[0] = this.tamGen(min[0], max[0]);
        this.tamGenes[1] = this.tamGen(min[1], max[1]);
        int tamTotal = tamGenes[0] + tamGenes[1];
        this.cromosoma = new Boolean[tamTotal];
        for(int i = 0; i < tamTotal; i++) this.cromosoma[i] = this.rand.nextBoolean();
    }

    @Override
    public double getValor() {
        double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
        double izq = 0, dcha = 0;
        for (int i = 1; i < 5; i++) {
            izq += i * Math.cos(x1 * (i+1) + i);
            dcha += i * Math.cos(x2 * (i+1) + i);
        }

        return izq*dcha;
    }

    @Override
    public double getFitness() {
        return getValor();
    }

    @Override
    public double getFenotipo(int n) {
        int ini = 0;
        String dec = "";
        for (int i = 0; i < n; i++) ini += this.tamGenes[i];
        for (int i = ini; i < ini + this.tamGenes[n]; i++) dec += cromosoma[i] ? '1' : '0';
        int val = Integer.parseInt(dec, 2);

        return min[n] + val * (max[n] - min[n]) / (Math.pow(2, this.tamGenes[n]) - 1);
    }

    @Override
    public void mutar(int i) {
        this.cromosoma[i] = !this.cromosoma[i];
    }

    @Override
    public String stringResult() {
        return "F: " + getFitness() + " x1: " + getFenotipo(0) + " x2: " + getFenotipo(1);
    }
}

