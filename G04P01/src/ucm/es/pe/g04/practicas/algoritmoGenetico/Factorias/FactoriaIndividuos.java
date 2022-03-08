package ucm.es.pe.g04.practicas.algoritmoGenetico.Factorias;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoFuncion1;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.Mutacion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionBasica;

public class FactoriaIndividuos {
    public static Individuo[] getPoblacionInicial(String funcion, int tamPoblacion, double precision){
        Individuo original;

        switch (funcion){
            case "Funcion1":
                original = new IndividuoFuncion1();
//            case "Funcion2":
//                original = new IndividuoFuncion2(precision);
//            case "Funcion3":
//                original = new IndividuoFuncion3(precision);
//            case "Funcion4":
//                original = new IndividuoFuncion4(precision);
//            case "Funcion4Real":
//                original = new IndividuoFuncion4Real(precision);
            default:
                original = new IndividuoFuncion1();
        }
        Individuo[] poblacion = new Individuo[tamPoblacion];

        original.init(precision);
        poblacion[0] = original;


        for (int i = 1; i < tamPoblacion; i++) {
            poblacion[i] = (Individuo) original.clone();
            poblacion[i].init(precision);
        }

        return poblacion;
    }
}
