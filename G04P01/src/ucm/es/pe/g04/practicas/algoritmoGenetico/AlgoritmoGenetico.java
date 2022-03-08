package ucm.es.pe.g04.practicas.algoritmoGenetico;

import ucm.es.pe.g04.practicas.algoritmoGenetico.Factorias.FactoriaCruce;
import ucm.es.pe.g04.practicas.algoritmoGenetico.Factorias.FactoriaIndividuos;
import ucm.es.pe.g04.practicas.algoritmoGenetico.Factorias.FactoriaMutacion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.Factorias.FactoriaSeleccion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.Cruce;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceMonopunto;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceUniforme;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoFuncion1;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.Mutacion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionBasica;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.Seleccion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.SeleccionEstocasticaUniversal;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.SeleccionRuleta;
import ucm.es.pe.g04.practicas.gui.Graficas;

import java.util.Arrays;

public class AlgoritmoGenetico {

    private int tamPoblacion = 100;
    private Individuo[] poblacion;
    private double[] fitness;
    private double fitnessMedio;
    private int maxGeneraciones = 100;
    private double probCruce = 0.6;
    private double probMutacion = 0.05;
    private int tamTorneo = 2;
    private float truncamiento = 0.5f;
    private double mejorAbsoluto;
    private double mejorGeneracion;
    private int posMejorGeneracion;
    private float precision = 0.001f;
    private boolean maximizar = true;
    private String seleccionPob = "Funcion1";
    private String seleccionFact = "Ruleta";
    private String cruceFact = "Monopunto";
    private String mutacionFact = "Basica";
    private Seleccion seleccion;
    private Cruce cruce;
    private Mutacion mutacion;
    private Graficas grafica;

    public void run() {
        iniciarPoblacion();

        int generacionActual = 0;
        mejorAbsoluto = poblacion[0].getFitness();

        //TODO hacer generico
        seleccion = FactoriaSeleccion.getAlgoritmoSeleccion(seleccionFact,tamTorneo, truncamiento);
        cruce = FactoriaCruce.getAlgoritmoCruce(cruceFact);
        mutacion = FactoriaMutacion.getAlgoritmoMutacion(mutacionFact);
        grafica = new Graficas();

        evaluar();
        while(generacionActual < this.maxGeneraciones) {
            //Seleccion
            poblacion = seleccion.seleccionar(poblacion);

            //Cruce
            cruce.reproduccion(poblacion, probCruce);

            //Mutacion
            mutacion.mutar(poblacion, probMutacion);

            evaluar();

            grafica.generarGrafica(mejorAbsoluto, mejorGeneracion,fitnessMedio);

        //Siguiente generacion
            generacionActual++;
        }

        System.out.println("Mejor:" + mejorAbsoluto);
    }


    private void evaluar() {
        double acc = 0;
        mejorGeneracion = poblacion[0].getFitness();
        for (int i = 0; i < tamPoblacion; i++){
            double aux = poblacion[i].getFitness();
            fitness[i] = aux;
            acc += aux;
            if(aux > mejorGeneracion && maximizar || aux < mejorGeneracion && !maximizar)
                mejorGeneracion = fitness[i];
        }
        fitnessMedio = acc / tamPoblacion;
        if(mejorGeneracion > mejorAbsoluto && maximizar || mejorGeneracion < mejorAbsoluto && !maximizar)
            mejorAbsoluto = mejorGeneracion;
        double aux = 0, auxAcc = 0;
        if(!maximizar){
            double max = Arrays.stream(fitness).max().getAsDouble();
            acc = 0;
            for (int i = 0; i < tamPoblacion; i++) {
                fitness[i] = max - fitness[i];
                acc += fitness[i];
            }
        }
        for(int i = 0;i <tamPoblacion; i++){
            aux = fitness[i]/acc;
            auxAcc += aux;
            poblacion[i].puntuacion = aux;
            poblacion[i].puntuacionAcc = auxAcc;
        }
    }

    private void iniciarPoblacion() {
        poblacion = FactoriaIndividuos.getPoblacionInicial(seleccionPob,tamPoblacion,precision);
        fitness = new double[tamPoblacion];
    }
}
