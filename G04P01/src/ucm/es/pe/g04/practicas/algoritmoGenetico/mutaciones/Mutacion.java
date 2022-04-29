package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Random;

public abstract class Mutacion implements Cloneable{
    protected Random r;
    public Mutacion(){
        r = new Random();
    }

    public abstract void mutar(Individuo[] poblacion, double probMutacion);

    public abstract String toString();
}
