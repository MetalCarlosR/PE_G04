package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Random;

public class MutacionBasica extends Mutacion {
    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {

        Random r = new Random();
        for (Individuo i : poblacion) {
            for (int j = 0; j < i.cromosoma.length; j++) {
                if(r.nextDouble() > probMutacion)
                    i.mutar(j);
            }
        }
    }
}
