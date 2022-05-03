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
                int index = -1;
                if(arbol.getPadre() != null)
                    index = arbol.getPadre().getHijos().indexOf(arbol);

                ArrayList<String> lista = new ArrayList();
                String[] funciones = a.getFunciones();
                int[] elemsPorFuncion = a.getElemsPorFuncion();
                for (int j = 0; j < funciones.length; j++) {
                    if (elemsPorFuncion[j] == arbol.getNumHijos() && funciones[j] != arbol.getValor()){
                        lista.add(funciones[j]);
                    }
                }
                if(lista.size() > 0){
                    arbol.setValor(lista.get(rand.nextInt(lista.size())));
                    if(index != -1)
                        arbol.getPadre().insert(arbol, index);
                }
            }
        }
    }

    public String toString() {
        return "Funcional";
    }
}
