package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Arbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol1;

public class CruceArbol extends Cruce{
    @Override
    protected void cruzar(Individuo individuo1, Individuo individuo2) {
        if (!(individuo1.cromosoma[0] instanceof Arbol) || !(individuo1.cromosoma[0] instanceof Arbol)) {
            System.out.println("Cuidado, llamastes al cruce aritmético con cromosomas no Double");
            return;
        }
    }
}
