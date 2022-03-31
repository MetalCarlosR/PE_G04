package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.ArrayList;

public class CruceOX_PP extends Cruce{
    private int _numCruce = 3;
    @Override
    protected void cruzar(Individuo individuo1, Individuo individuo2) {
        int length = individuo1.cromosoma.length;
        var aux1 = individuo1.cromosoma.clone();
        var aux2 = individuo2.cromosoma.clone();

        ArrayList<Object> elems1 = new ArrayList<Object>(_numCruce);
        ArrayList<Object> elems2 = new ArrayList<Object>(_numCruce);
        ArrayList<Integer> lista = new ArrayList<Integer>(length);
        for(int i = 0; i < length; i++) {
            lista.add(i);
        }
        for (int i = 0; i < _numCruce; i++){
            int aux =  lista.remove(r.nextInt(lista.size()));
            individuo1.cromosoma[aux] = aux2[aux];
            individuo2.cromosoma[aux] = aux1[aux];
            elems1.add(individuo1.cromosoma[aux]);
            elems2.add(individuo2.cromosoma[aux]);
        }
        for (int i = 0, i1 = 0, i2 = 0; i < length; i++) {
//            i1 %= length;
//            i2 %= length;
            //Significa que no son posiciones vetadas
            if (lista.contains(i)){
                while (elems1.contains(aux1[i1])){
                    i1++;
//                    i1 %= length;
                }
                individuo1.cromosoma[i] = aux1[i1];

                while (elems2.contains(aux2[i2])){
                    i2++;
//                    i2 %= length;
                }
                individuo2.cromosoma[i] = aux2[i1];

                i1++; i2++;
            }
        }
    }

    public String toString() {
        return "OX_PP";
    }
}
