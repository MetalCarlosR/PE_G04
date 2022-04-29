package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Arbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol;

import java.util.Random;

public class MutacionTerminal extends Mutacion {
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
                Arbol arbol = a.getArbol().getRandomTerminal();
                arbol.setValor(a.getTerminales()[rand.nextInt(a.getTerminales().length)]);
            }
        }
    }

    public String toString() {
        return "Terminal";
    }
}