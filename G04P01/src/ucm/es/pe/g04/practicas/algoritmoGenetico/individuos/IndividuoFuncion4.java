package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Random;

public class IndividuoFuncion4 extends Individuo<Boolean> {

    int n = 2;

    @Override
    public void init(double precision) {
        this.precision = precision;
        this.tamGenes = new int[n];
        this.min = new double[n];
        this.max = new double[n];
        for (int i = 0; i < n; i++){
            this.min[i] = -10;
            this.max[i] = 10;
            this.tamGenes[i] = this.tamGen(min[i], max[i]);
        }
        int tamTotal = tamGenes[0]*n;
        this.cromosoma = new Boolean[tamTotal];
        for(int i = 0; i < tamTotal; i++) this.cromosoma[i] = this.rand.nextBoolean();
    }

    @Override
    public double getValor() {
        double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
        return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
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
        String s = "F: " + getFitness();
        for (int i = 0; i < n; i++) {
            s += " x" + i + ": " + getFenotipo(i);
        }
        return s;
    }
}

