package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.util.Random;

public abstract class Individuo<T> implements Cloneable , Comparable<Individuo<T>> {
    public double puntuacion;
    public T[] cromosoma;
    protected int[] tamGenes;
    protected double fitness;

    protected Random rand = new Random();

    public abstract void init();

    public abstract double getValor();

    public double getFitness() { return fitness;};

    public abstract double calculaFitness();

    public abstract double getFenotipo(int n);

    public abstract void mutar(int i);

    public abstract int tamGen(int n);

    public String stringResult() {
        String s = "Fitness: " + getFitness() + "\n" + "Cromosomas: ";
        for (int i = 0; i < cromosoma.length; i++) {
            s += cromosoma[i] + " - ";
        }
        s = s.substring(0,s.length() - 3);
        s += "\n" + "Fenotipo: ";
        for (int i = 0; i < tamGenes.length; i++) {
            s += getFenotipo(i) + "  ";
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