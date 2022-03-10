package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.util.Random;

public abstract class Individuo<T> implements Cloneable , Comparable<Individuo<T>> {
    public double puntuacion;
    public T[] cromosoma;
    protected int[] tamGenes;

    protected Random rand = new Random();

    public abstract int tamGen(int n);

    public abstract void init();

    public abstract double getValor();

    public abstract double getFitness();

    public abstract double getFenotipo(int n);

    public abstract void mutar(int i);

    public String stringResult() {
        String s = "Fitness: " + getFitness();
        for (int i = 0; i < tamGenes.length; i++) {
            s += " Gen " + i + ": " + getFenotipo(i);
        }
        return s;
    }

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