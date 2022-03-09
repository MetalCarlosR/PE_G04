package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Random;

public class CruceUniforme extends Cruce{
    @Override
    protected void cruzar(Individuo individuo1, Individuo individuo2) {
        var aux1 = individuo1.cromosoma.clone();
        var aux2 = individuo2.cromosoma.clone();
        for (int i = 0; i < individuo1.cromosoma.length ; i++) {
            if(r.nextDouble() > 0.5){
                individuo1.cromosoma[i] = aux2[i];
                individuo2.cromosoma[i] = aux1[i];
            }
        }

    }

    public String toString() {
        return "Cruce Uniforme";
    }
}
