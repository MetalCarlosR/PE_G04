package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Random;

public class SeleccionRuleta extends Seleccion {

    @Override
    public Individuo[] seleccionar(Individuo[] poblacion) {
        int tamPoblacion = poblacion.length;
        Random r = new Random();
        Individuo sel_super[] = new Individuo[tamPoblacion];//seleccionados para sobrevivir
        double prob; // probabilidad de seleccion
        for(int i = 0; i < tamPoblacion; i++)
        {
            prob = r.nextDouble();
            int j = 0;
            while ((prob > poblacion[j].puntuacion) && (j  < tamPoblacion)) {prob -= poblacion[j].puntuacion; j++;}
            sel_super[i] = (Individuo) poblacion[j].clone();
        }
        return sel_super;
    }
}
