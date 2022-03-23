package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;


import java.io.*;

public class IndividuoAviones extends Individuo<Integer>{


    @Override
    public void init() {

    }

    @Override
    public double getValor() {
        return 0;
    }

    @Override
    public double getFitness() {
        return 0;
    }

    @Override
    public double getFenotipo(int n) {
        return cromosoma[n];
    }

    @Override
    public void mutar(int i) {

    }

    @Override
    public int tamGen(int n) {
        return 1;
    }

}
