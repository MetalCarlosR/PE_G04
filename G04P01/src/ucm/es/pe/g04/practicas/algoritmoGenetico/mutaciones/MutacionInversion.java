package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class MutacionInversion extends Mutacion{
    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {
        for (Individuo i : poblacion) {
            if(r.nextDouble() < probMutacion)
            {
                int p1 = r.nextInt(i.cromosoma.length), p2 = r.nextInt(i.cromosoma.length);
                if (p1 > p2) {
                    int aux = p1;
                    p1 = p2;
                    p2 = aux;
                }
                while (p1 < p2){
                    Object aux = i.cromosoma[p1];
                    i.cromosoma[p1] = i.cromosoma[p2];
                    i.cromosoma[p2] = aux;
                    p1++; p2--;
                }
            }
        }
    }

    public String toString() {
        return "Inversion";
    }
}
