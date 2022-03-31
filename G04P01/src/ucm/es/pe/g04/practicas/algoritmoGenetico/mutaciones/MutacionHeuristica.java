package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.ArrayList;


public class MutacionHeuristica extends Mutacion{
    public int get_heuristica() {
        return _heuristica;
    }

    public void set_heuristica(int _inserciones) {
        this._heuristica = _inserciones;
    }

    private int _heuristica = 3;
    private int[] posiciones;

    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {
        for (Individuo i : poblacion) {
            if(r.nextDouble() < probMutacion)
            {
                posiciones = new int[_heuristica];
                ArrayList<Integer> lista = new ArrayList<Integer>(i.cromosoma.length);
                for(int j = 0; j < i.cromosoma.length; j++) {
                    lista.add(j);
                }
                int numeros = 0;
                while (numeros < _heuristica){
                    int index = r.nextInt(lista.size());
                    posiciones[numeros] = lista.get(index);
                    lista.remove(index);
                    numeros++;
                }
                i = permute(posiciones, posiciones.clone(), 0, i, (Individuo) i.clone());
                ;
            }
        }
    }

    public String toString() {
        return "Heuristica";
    }

    private Individuo permute(int[] posiciones, int[] originales, int k, Individuo individuo, Individuo individuoBest){
        for(int i = k; i < posiciones.length; i++){
            //Swap
            int aux = posiciones[i];
            posiciones[i] = posiciones[k];
            posiciones[k] = aux;
            individuoBest = permute(posiciones, originales, k+1, individuo, individuoBest);
            //Swap back
            aux = posiciones[i];
            posiciones[i] = posiciones[k];
            posiciones[k] = aux;
        }
        if (k == posiciones.length - 1){
            Individuo aux = (Individuo) individuo.clone();
            for (int i = 0; i < posiciones.length; i++) {
                aux.cromosoma[posiciones[i]] = individuo.cromosoma[originales[i]];
             }
            double fitness = aux.getFitness();
            double bestFitness = individuoBest.getFitness();
            if(fitness > bestFitness && AlgoritmoGenetico.instance.getMaximizar() || fitness < bestFitness && !AlgoritmoGenetico.instance.getMaximizar())
            {
                individuoBest = (Individuo) aux.clone();
            }
        }
        return individuoBest;
    }
}
