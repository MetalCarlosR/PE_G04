package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public abstract class Mutacion {

    public abstract void mutar(Individuo[] poblacion, double probMutacion);
}
