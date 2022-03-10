package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Random;

public class IndividuoFuncion4 extends Individuo<Boolean> {

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    int n = 2;

    @Override
    public void init(double precision) {
        this.precision = precision;
        this.tamGenes = new int[n];
        this.min = new double[n];
        this.max = new double[n];
        for (int i = 0; i < n; i++){
            this.min[i] = 0;
            this.max[i] = Math.PI;
            this.tamGenes[i] = this.tamGen(min[i], max[i]);
        }
        int tamTotal = tamGenes[0]*n;
        this.cromosoma = new Boolean[tamTotal];
        for(int i = 0; i < tamTotal; i++) this.cromosoma[i] = this.rand.nextBoolean();
    }

    @Override
    public double getValor() {
        double valor = 0;
        for (int i = 1; i <= n; i++) {
            double x = this.getFenotipo(n-1);
            valor += Math.sin(x) * Math.pow(Math.sin((i+1)*Math.pow(x, 2)/Math.PI), 20);
        }
        valor = -valor;
        return valor;
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

    public String toString() {
        return "Funcion 4";
    }
}

