package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.ArrayList;
import java.util.Arrays;

public class CruceOX extends Cruce{
    @Override
    protected void cruzar(Individuo individuo1, Individuo individuo2) {
        int length = individuo1.cromosoma.length;
        var aux1 = individuo1.cromosoma.clone();
        var aux2 = individuo2.cromosoma.clone();

        int p1 = r.nextInt(length), p2 = r.nextInt(length);
        if (p1 > p2){
            int aux = p1;
            p1 = p2;
            p2 = aux;
        }
        //if (p1 == p2) return;
        ArrayList<Object> a1 = new ArrayList<Object>(), a2 = new ArrayList<Object>();
        for (int i = p1; i <= p2; i++) {
            a1.add(aux2[i]);
            a2.add(aux1[i]);
            individuo1.cromosoma[i] = aux2[i];
            individuo2.cromosoma[i] = aux1[i];
        }
        int i = (p2 + 1) % length, i1 = i, i2 = i;
        if (i > p1) p1 += length;
        while (i1 < p1 || i2 < p1){
            if (!a1.contains(aux1[i])){
                individuo1.cromosoma[i1 % length] = aux1[i];
                i1++;
            }

            if (!a2.contains(aux2[i])){
                individuo2.cromosoma[i2 % length] = aux2[i];
                i2++;
            }

            i++;
            i %= length;
        }
    }

    public String toString() {
        return "OX";
    }
}
