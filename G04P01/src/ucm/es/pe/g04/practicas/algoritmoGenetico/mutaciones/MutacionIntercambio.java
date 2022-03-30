package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class MutacionIntercambio extends Mutacion{
    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {
        for (Individuo i : poblacion) {
            if(r.nextDouble() < probMutacion)
            {
                int p1 = r.nextInt(i.cromosoma.length), p2 = r.nextInt(i.cromosoma.length);
                Object aux = i.cromosoma[p1];
                i.cromosoma[p1] = i.cromosoma[p2];
                i.cromosoma[p2] = aux;
            }
        }
    }

    public String toString() {
        return "Intercambio";
    }
}
