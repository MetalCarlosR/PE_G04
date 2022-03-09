package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Random;

public class CruceMonopunto extends Cruce {

    @Override
    protected void cruzar(Individuo individuo1, Individuo individuo2) {
        var aux1 = individuo1.cromosoma.clone();
        var aux2 = individuo2.cromosoma.clone();

        int punto = r.nextInt(individuo1.cromosoma.length);

        for (int i = 0; i < punto; i++) {
            individuo2.cromosoma[i] = aux1[i];
        }
        for (int i = punto; i < individuo1.cromosoma.length; i++) {
            individuo1.cromosoma[i] = aux2[i];
        }
    }

    public String toString() {
        return "Monopunto";
    }
}
