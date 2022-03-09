package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Random;

public abstract class Seleccion implements Cloneable{

    protected Random r;

    public Seleccion(){
        r = new Random();
    }

    public abstract Individuo[] seleccionar(Individuo[] poblacion);
}
