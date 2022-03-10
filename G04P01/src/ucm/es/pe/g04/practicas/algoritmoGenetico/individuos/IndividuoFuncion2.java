package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Random;

public class IndividuoFuncion2 extends IndividuoBool {

    @Override
    public void init() {
        this.tamGenes = new int[2];
        this.min = new double[2];
        this.max = new double[2];
        this.min[0] = -10;
        this.min[1] = -10;
        this.max[0] = 10;
        this.max[1] = 10;
        this.tamGenes[0] = this.tamGen(0);
        this.tamGenes[1] = this.tamGen(1);
        int tamTotal = tamGenes[0] + tamGenes[1];
        this.cromosoma = new Boolean[tamTotal];
        for(int i = 0; i < tamTotal; i++) this.cromosoma[i] = this.rand.nextBoolean();
    }

    @Override
    public double getValor() {
        double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
        double izq = 0, dcha = 0;
        for (int i = 1; i <= 5; i++) {
            izq += i * Math.cos(x1 * (i+1) + i);
            dcha += i * Math.cos(x2 * (i+1) + i);
        }

        return izq*dcha;
    }

    @Override
    public double getFitness() {
        return getValor();
    }


    public String toString() {
        return "Funcion 2";
    }
}

