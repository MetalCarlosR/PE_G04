package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.ArrayList;
import java.util.Arrays;

public class CruceOX_OP extends Cruce{
    @Override
    protected void cruzar(Individuo individuo1, Individuo individuo2) {
        int length = individuo1.cromosoma.length;
        var aux1 = individuo1.cromosoma.clone();
        var aux2 = individuo2.cromosoma.clone();

        int numCruce = r.nextInt(length -1 ) + 1;
        numCruce = 5;

        ArrayList<Object> subAux1 = new ArrayList<Object>(numCruce);
        ArrayList<Object> subAux2 = new ArrayList<Object>(numCruce);
        ArrayList<Object> opuestoSubAux1 = new ArrayList<>(numCruce);
        ArrayList<Object> opuestoSubAux2 = new ArrayList<>(numCruce);
        ArrayList<Integer> lista = new ArrayList<Integer>(length);
        for(int i = 0; i < length; i++) {
            lista.add(i);
        }
        for (int i = 0; i < numCruce; i++){
            int aux = lista.remove(r.nextInt(lista.size()));
            subAux1.add(individuo1.cromosoma[aux]);
            subAux2.add(individuo2.cromosoma[aux]);
            opuestoSubAux1.add(Arrays.stream(aux2).toList().indexOf(individuo1.cromosoma[aux]));
            opuestoSubAux2.add(Arrays.stream(aux1).toList().indexOf(individuo2.cromosoma[aux]));
        }

        Object c1,c2;
        for (int i = 0; i < individuo1.cromosoma.length; i++) {
            if(!opuestoSubAux1.contains(i)){
                c1 = aux2[i];
            }
            else{
                c1 = subAux1.remove(0);
            }
            if(!opuestoSubAux2.contains(i)){
                c2 = aux1[i];
            }
            else{
                c2 = subAux2.remove(0);
            }
            individuo1.cromosoma[i] = c1;
            individuo2.cromosoma[i] = c2;
        }
    }

    public String toString() {
        return "OX_OP";
    }
}
