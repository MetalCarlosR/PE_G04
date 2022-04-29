package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Arbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol;

import java.util.ArrayList;
import java.util.Random;

public class MutacionPermutacion extends Mutacion {
    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {
        if (!(poblacion[0].cromosoma[0] instanceof Arbol)) {
            System.out.println("Cuidado, necesito un Individuo<Arbol> para funcionar");
            return;
        }
        Random rand = new Random();
        for (Individuo i : poblacion) {
            if(r.nextDouble() < probMutacion)
            {
                IndividuoArbol a = (IndividuoArbol) i;
                Arbol arbol = a.getArbol().getRandomFuncion();
                if (arbol == null) continue;

                for (int j = 0; j < arbol.getNumHijos(); j++) {
                    int k = rand.nextInt(arbol.getNumHijos());
                    if (k == j) continue;
                    Arbol aux = (Arbol) arbol.getHijos().get(k).clone();
                    arbol.insert((Arbol)arbol.getHijos().get(j).clone(), k);
                    arbol.insert(aux, j);
                }
            }
        }
    }

    public String toString() {
        return "Permutacion";
    }
}
