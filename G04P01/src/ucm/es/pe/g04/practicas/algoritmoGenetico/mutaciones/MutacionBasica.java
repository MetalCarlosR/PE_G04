package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;


public class MutacionBasica extends Mutacion {
    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {
        for (Individuo i : poblacion) {
            for (int j = 0; j < i.cromosoma.length; j++) {
                if(r.nextDouble() < probMutacion)
                    i.mutar(j);
            }
        }
    }

    public String toString() {
        return "Mutacion basica";
    }
}
