package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Arbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol;

import java.util.Random;

public class MutacionHoist extends Mutacion{
    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {
        if (!(poblacion[0].cromosoma[0] instanceof Arbol)) {
            System.out.println("Cuidado, necesito un Individuo<Arbol> para funcionar");
            return;
        }
        for (Individuo i : poblacion) {
            if(r.nextDouble() < probMutacion)
            {
                IndividuoArbol a = (IndividuoArbol) i;
                Arbol arbol = (Arbol) a.getArbol().getRandomHijo(0.9, false);
                arbol.clearPadre();
                i.cromosoma[0] = arbol.clone();
            }
        }
    }

    @Override
    public String toString() {
        return "Hoist";
    }
}
