package ucm.es.pe.g04.practicas.algoritmoGenetico.Factorias;

import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.*;

public class FactoriaSeleccion {
    public static Seleccion getAlgoritmoSeleccion(String algoritmo, int participantes, double truncamiento){
        switch (algoritmo){
            case "Ruleta":
                return new SeleccionRuleta();
            case "Estocastica_Universal":
                return new SeleccionEstocasticaUniversal();
            case "Truncamiento":
                return new SeleccionTruncamiento(truncamiento);
            case "Torneo":
                return new SeleccionTorneoDet(participantes);
            case "Torneo_Probabilistico":
                return new SeleccionTorneoProb(participantes);
            case "Restos":
                return new SeleccionRestos();
            default:
                return new SeleccionRuleta();
        }
    }
}
