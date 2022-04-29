package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Arbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol;

import java.util.Random;

public class MutacionExpansion extends Mutacion{
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
                IndividuoArbol a = (IndividuoArbol)i ;
                Arbol arbol = a.getArbol().getRandomTerminal();
                switch(a.getTipoCreacion()) {
                    case "Creciente":
                        arbol.inicializacionCreciente(0, a);
                        break;
                    case "Completa":
                        arbol.inicializacionCompleta(0, a);
                        break;
                    case "RampedAndHalf":
                        arbol.setProfundidad(rand.nextInt(2, arbol.getProfundidad() + 1));
                        int ini = new Random().nextInt(2);
                        if(ini == 0) arbol.inicializacionCreciente(0,a);
                        else arbol.inicializacionCompleta(0,a);
                        break;
                    default:
                        System.out.println("Error, tipo de creación no encontrado");
                        arbol.inicializacionCompleta(0,a);
                        break;
                }
            }
        }
    }

    @Override
    public String toString() {
        return "Expansion";
    }
}
