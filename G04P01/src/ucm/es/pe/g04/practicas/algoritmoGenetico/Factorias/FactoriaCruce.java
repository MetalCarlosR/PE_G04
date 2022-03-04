package ucm.es.pe.g04.practicas.algoritmoGenetico.Factorias;


import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.Cruce;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceMonopunto;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceUniforme;

public class FactoriaCruce {

    public static Cruce getAlgoritmoCruce(String algoritmo){
        switch (algoritmo){
            case "Monopunto":
                return new CruceMonopunto();
            case "Uniforme":
                return new CruceUniforme();
            default:
                return new CruceMonopunto();
        }
    }
}
