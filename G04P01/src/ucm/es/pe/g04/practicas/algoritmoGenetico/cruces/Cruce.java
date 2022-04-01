package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class Cruce implements Cloneable{

    Random r;

    public Cruce(){
        r = new Random();
    }

    public void reproduccion(Individuo[] poblacion, double probCruce){
        int tamPoblacion = poblacion.length;
        List<Integer> sel_cruce = new ArrayList<>();
        r = new Random();
        int num_sel_cruce = 0;
        for (int i = 0; i < tamPoblacion; i++) {
            if (r.nextDouble() < probCruce) {
                sel_cruce.add(i);
                num_sel_cruce++;
            }
        }
        if (num_sel_cruce % 2 == 1) num_sel_cruce--;

        for (int i = 0; i < num_sel_cruce; i+= 2){
            cruzar(poblacion[sel_cruce.get(i)], poblacion[sel_cruce.get(i+1)]);
        }
    }

    protected abstract void cruzar(Individuo individuo1, Individuo individuo2);
}
