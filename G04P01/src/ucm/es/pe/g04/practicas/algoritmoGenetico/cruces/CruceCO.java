package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.AvionesData;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.ArrayList;

public class CruceCO extends Cruce {
    @Override
    protected void cruzar(Individuo individuo1, Individuo individuo2) {
        int length = individuo1.cromosoma.length;
        int[] aux1 = new int[length];
        int[] aux2 = new int[length];

        ArrayList<Integer> lista = new ArrayList<Integer>(length);
        for (int i = 0; i < length; i++) {
            lista.add(i);
        }
        for (int i = length - 1; i > 0; i--) {
            int index = r.nextInt(i + 1);
            if (index == i)
                continue;
            // Simple swap
            int a = lista.get(index);
            lista.set(index, lista.get(i));
            lista.set(i, a);
        }

        ArrayList<Integer> listaCopy = (ArrayList<Integer>) lista.clone();
        for (int i = 0; i < length; i++) {
            aux1[i] = listaCopy.indexOf(individuo1.cromosoma[i]);
            listaCopy.remove(aux1[i]);
        }
        listaCopy = (ArrayList<Integer>) lista.clone();
        for (int i = 0; i < length; i++) {
            aux2[i] = listaCopy.indexOf(individuo2.cromosoma[i]);
            listaCopy.remove(aux2[i]);
        }


        int punto = r.nextInt(length);

        for (int i = punto; i < length; i++) {
            var aux = aux1[i];
            aux1[i] = aux2[i];
            aux2[i] = aux;
        }


        listaCopy = (ArrayList<Integer>) lista.clone();
        for (int i = 0; i < length; i++) {
            individuo1.cromosoma[i] = listaCopy.get(aux1[i]);
            listaCopy.remove(aux1[i]);
        }
        for (int i = 0; i < length; i++) {
            individuo2.cromosoma[i] = lista.get(aux2[i]);
            lista.remove(aux2[i]);
        }



    }

    public String toString() {
        return "CO";
    }
}
