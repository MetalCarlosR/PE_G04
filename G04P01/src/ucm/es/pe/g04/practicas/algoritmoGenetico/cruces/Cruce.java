package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Cruce {

    public Individuo[] reproduccion(Individuo[] poblacion, double probCruce){
        int tamPoblacion = poblacion.length;
        List<Individuo> sel_cruce = new ArrayList<Individuo>();
        Random r = new Random();
        int num_sel_cruce = 0;
        for (int i = 0; i < tamPoblacion; i++) {
            if (r.nextDouble() < probCruce) {
                sel_cruce.add(poblacion[i]);
                num_sel_cruce++;
            }
        }
        if (num_sel_cruce % 2 == 1) num_sel_cruce--;

        for (int i = 0; i < num_sel_cruce; i+= 2){
            cruzar(sel_cruce.get(i), sel_cruce.get(i + 1));
        }
        return poblacion;
    }

    protected abstract void cruzar(Individuo individuo1, Individuo individuo2);
}
