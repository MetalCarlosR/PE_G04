package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Arbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol;

import java.util.ArrayList;
import java.util.Random;

public class MutacionFuncional extends Mutacion {
    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {
        if (!(poblacion[0].cromosoma[0] instanceof Arbol)) {
            System.out.println("Cuidado, llamaste a la mutacion funcional con individuos no arbol");
            return;
        }
        Random rand = new Random();
        for (Individuo i : poblacion) {
            if(r.nextDouble() < probMutacion)
            {
                IndividuoArbol a = (IndividuoArbol) i;
                Arbol arbol = a.getArbol().getRandomFuncion();
                ArrayList<String> lista = new ArrayList();
                String[] funciones = a.getFunciones();
                int[] elemsPorFuncion = a.getElemsPorFuncion();
                for (int j = 0; j < funciones.length; j++) {
                    if (elemsPorFuncion[j] == arbol.getNumHijos() && funciones[j] != arbol.getValor()){
                        lista.add(funciones[j]);
                    }
                }
                arbol.setValor(lista.get(rand.nextInt(lista.size())));
            }
        }
    }

    public String toString() {
        return "Funcional";
    }
}
