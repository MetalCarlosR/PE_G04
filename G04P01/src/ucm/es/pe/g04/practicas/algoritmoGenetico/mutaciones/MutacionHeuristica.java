package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class MutacionHeuristica extends Mutacion{
    public int get_heuristica() {
        return _heuristica;
    }

    public void set_heuristica(int _inserciones) {
        this._heuristica = _inserciones;
    }

    private int _heuristica = 2;

    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {
        for (Individuo i : poblacion) {
            if(r.nextDouble() < probMutacion)
            {
                Individuo best = (Individuo) i.clone();
                permute(i, 0, best);
                i = best;
            }
        }
    }

    public String toString() {
        return "Heuristica";
    }

    private static void permute(Individuo individuo, int k, Individuo individuoBest){
        for(int i = k; i < individuo.cromosoma.length; i++){
            //Swap
            Object aux = individuo.cromosoma[i];
            individuo.cromosoma[i] = individuo.cromosoma[k];
            individuo.cromosoma[k] = aux;
            permute(individuo, k+1, individuoBest);
            //Swap back
            aux = individuo.cromosoma[i];
            individuo.cromosoma[i] = individuo.cromosoma[k];
            individuo.cromosoma[k] = aux;
        }
        if (k == individuo.cromosoma.length -1){
            double fitness = individuo.getFitness();
            double bestFitness = individuoBest.getFitness();
            if(fitness > bestFitness && AlgoritmoGenetico.isMaximizar() || fitness < bestFitness && !AlgoritmoGenetico.isMaximizar())
            {
                individuoBest = (Individuo) individuo.clone();
            }
        }
    }
}
