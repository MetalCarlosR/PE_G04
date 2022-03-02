package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.util.Random;

public abstract class Individuo<T> implements Cloneable {
    public double puntuacion;
    public double puntuacionAcc;
    public T[] cromosoma;
    protected int[] tamGenes;

    protected double[] min;
    protected double[] max;
    protected double precision;

    protected Random rand = new Random();

    public int tamGen(double min, double max) {
        return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
    }

    public abstract double getValor();

    public abstract double getFitness();

    public abstract double getFenotipo(int n);

    public abstract void mutar(int i);

    public Object clone() {
        try {
            return (Individuo<T>) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }


    }
