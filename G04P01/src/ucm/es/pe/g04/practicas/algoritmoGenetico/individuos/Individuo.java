package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.util.Random;

public abstract class Individuo<T> implements Cloneable , Comparable<Individuo<T>> {
    public double puntuacion;
    public T[] cromosoma;
    public double puntuacionAcc;
    protected int[] tamGenes;

    protected double[] min;
    protected double[] max;
    protected double precision;

    protected Random rand = new Random();

    public int tamGen(double min, double max) {
        return (int) (Math.log10(((max - min) / precision) + 1) / Math.log10(2));
    }

    public abstract void init(float precision);

    public abstract double getValor();

    public abstract double getFitness();

    public abstract double getFenotipo(int n);

    public abstract void mutar(int i);

    public Object clone() {
        try {
            Individuo<T> newClone = (Individuo<T>)super.clone();
            newClone.cromosoma = this.cromosoma.clone();
            return newClone;
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }

    @Override
    public int compareTo(Individuo<T> o) {
        return Double.compare(puntuacion, o.puntuacion);
    }
}
