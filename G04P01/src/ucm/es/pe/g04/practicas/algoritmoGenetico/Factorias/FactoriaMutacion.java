package ucm.es.pe.g04.practicas.algoritmoGenetico.Factorias;

import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.Mutacion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionBasica;

public class FactoriaMutacion {
    public static Mutacion getAlgoritmoMutacion(String algoritmo){
        switch (algoritmo){
            case "Basica":
                return new MutacionBasica();
            default:
                return new MutacionBasica();
        }
    }
}
