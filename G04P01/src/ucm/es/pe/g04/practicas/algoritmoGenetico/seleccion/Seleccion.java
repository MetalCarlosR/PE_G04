package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public abstract class Seleccion {

    public abstract Individuo[] seleccionar(Individuo[] poblacion);
}
