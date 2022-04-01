package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.ArrayList;
import java.util.Arrays;

public class CruceCX extends Cruce{
    @Override
    protected void cruzar(Individuo individuo1, Individuo individuo2) {
        int length = individuo1.cromosoma.length;
        var aux1 = individuo1.cromosoma.clone();
        var aux2 = individuo2.cromosoma.clone();
        ArrayList<Integer> visitadas = new ArrayList<Integer>();
        int index = 0;
        while (!visitadas.contains(index)){
            visitadas.add(index);
            index = Arrays.stream(aux1).toList().indexOf(aux2[index]);
        }
        for (Integer i : visitadas){
            individuo1.cromosoma[i] = aux2[i];
            individuo2.cromosoma[i] = aux1[i];
        }

    }

    public String toString() {
        return "CX";
    }
}
