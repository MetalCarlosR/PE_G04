package ucm.es.pe.g04.practicas.algoritmoGenetico;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.Seleccion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.SeleccionRuleta;
import ucm.es.pe.g04.practicas.practica1.IndividuoFuncion1;

public class AlgoritmoGenetico {

    private int tamPoblacion = 100;
    private Individuo[] poblacion;
    private double[] fitness;
    private int maxGeneraciones = 100;
    private double probCruce;
    private double probMutacion;
    private int tamTorneo;
    private Individuo elMejor;
    private int pos_mejor;
    private float precision = 0.01f;
    private Seleccion seleccion;

    public void run() {
        iniciarPoblacion();

        int generacionActual = 0;

        //TODO hacer generico
        seleccion = new SeleccionRuleta();

        evaluar();
        while(generacionActual < this.maxGeneraciones) {
            //Seleccion
            poblacion = seleccion.seleccionar(poblacion);

            //Cruce

            //Mutacion

            evaluar();

//            generaGrafica();

        //Siguiente generacion
            generacionActual++;
        }
    }


    private void evaluar() {
        double acc = 0;
        for (int i = 0; i < tamPoblacion; i++){
            double aux = poblacion[i].getFitness();
            fitness[i] = aux;
            acc += aux;
        }
        for(int i = 0;i <tamPoblacion; i++){
            poblacion[i].puntuacion = fitness[i]/acc;
        }
    }

    private void iniciarPoblacion() {
        poblacion = new Individuo[tamPoblacion];
        fitness = new double[tamPoblacion];
        for (int i = 0; i < tamPoblacion; i++){
            poblacion[i] = new IndividuoFuncion1(precision);
        }
    }
}
