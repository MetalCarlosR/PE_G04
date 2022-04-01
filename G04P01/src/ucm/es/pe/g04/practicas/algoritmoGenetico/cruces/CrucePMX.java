package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrucePMX extends Cruce{
    @Override
    protected void cruzar(Individuo individuo1, Individuo individuo2) {
        var aux1 = individuo1.cromosoma.clone();
        var aux2 = individuo2.cromosoma.clone();

        int p1 = r.nextInt(individuo1.cromosoma.length), p2 = r.nextInt(individuo1.cromosoma.length);

        if(p1 > p2){
            int aux = p1;
            p1 = p2;
            p2 = aux;
        }

        var subAux1 = Arrays.stream(Arrays.copyOfRange(aux1,p1,p2+1).clone()).toList();
        var subAux2 = Arrays.stream(Arrays.copyOfRange(aux2,p1,p2+1).clone()).toList();


        for (int i = 0; i < individuo1.cromosoma.length; i++) {
            if(i < p1 || i > p2){
                if(subAux1.contains(aux2[i])){
                    int index = subAux1.indexOf(aux2[i]);
                    while (subAux1.contains(subAux2.get(index))){
                        index = subAux1.indexOf(subAux2.get(index));
                    }
                    individuo2.cromosoma[i] = subAux2.get(index);
                }
                if(subAux2.contains(aux1[i])){
                    int index = subAux2.indexOf(aux1[i]);
                    while (subAux2.contains(subAux1.get(index))){
                        index = subAux2.indexOf(subAux1.get(index));
                    }
                    individuo1.cromosoma[i] = subAux1.get(index);
                }
            }
            else {
                individuo1.cromosoma[i] = subAux2.get(i - p1);
                individuo2.cromosoma[i] = subAux1.get(i - p1);
            }
        }

        for (int i = 0; i < individuo1.cromosoma.length; i++) {
            if(!Arrays.stream(individuo1.cromosoma).toList().contains(i) || !Arrays.stream(individuo1.cromosoma).toList().contains(i)){
                System.out.println("FUCK");
            }
        }
    }

    public String toString() {
        return "PMX";
    }
}
