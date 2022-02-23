package ucm.es.pe.g04.practicas.algoritmoGenetico;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class AlgoritmoGenetico {

    private int tamPoblacion;
    private Individuo[] poblacion;
    private double[] fitness;
    private int maxGeneraciones;
    private double probCruce;
    private double probMutacion;
    private int tamTorneo;
    private Individuo elMejor;
    private int pos_mejor;

    public void run() {
        iniciarPoblacion();

        int generacionActual = 0;

        evaluar();
        while(generacionActual < this.maxGeneraciones) {
            //Seleccion

            //Cruce

            //Mutacion

            evaluar();

//            generaGrafica();

        //Siguiente generacion
            generacionActual++;
        }
    }

    private void evaluar() {

    }

    private void iniciarPoblacion() {

    }
}
