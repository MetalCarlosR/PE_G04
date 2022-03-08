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
import java.util.Comparator;

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
    private Individuo mejorAbsoluto;
    private int mejorGeneracion;
    private float elitismo = 0.1f;
    private Individuo[] pobElite;
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
        int numElite = (int) (tamPoblacion * elitismo);
        pobElite = new Individuo[numElite];
        mejorAbsoluto = (Individuo) poblacion[0].clone();

        //TODO hacer generico
        seleccion = FactoriaSeleccion.getAlgoritmoSeleccion(seleccionFact,tamTorneo, truncamiento);
        cruce = FactoriaCruce.getAlgoritmoCruce(cruceFact);
        mutacion = FactoriaMutacion.getAlgoritmoMutacion(mutacionFact);
        grafica = new Graficas();

        evaluar();
        while(generacionActual < this.maxGeneraciones) {
            //Elitismo
            guardarElite(numElite);

            //Seleccion
            poblacion = seleccion.seleccionar(poblacion);

            //Cruce
            cruce.reproduccion(poblacion, probCruce);

            //Mutacion
            mutacion.mutar(poblacion, probMutacion);

            devolverElite();

            evaluar();

            grafica.generarGrafica(mejorAbsoluto.getFitness(), fitness[mejorGeneracion] ,fitnessMedio);

        //Siguiente generacion
            generacionActual++;
        }

        System.out.println("Mejor:" + mejorAbsoluto.stringResult());
    }


    private void evaluar() {
        double acc = 0;
        mejorGeneracion = 0;
        int mejG = 0;
        for (int i = 0; i < tamPoblacion; i++){
            double aux = poblacion[i].getFitness();
            fitness[i] = aux;
            acc += aux;
            if(aux > fitness[mejorGeneracion] && maximizar || aux < fitness[mejorGeneracion] && !maximizar)
                mejorGeneracion =i;
        }
        fitnessMedio = acc / tamPoblacion;
        if(fitness[mejorGeneracion] > mejorAbsoluto.getFitness() && maximizar || fitness[mejorGeneracion] < mejorAbsoluto.getFitness() && !maximizar)
            mejorAbsoluto = (Individuo) poblacion[mejorGeneracion].clone();
        double aux = 0;
        if(!maximizar){
            double max = Arrays.stream(fitness).max().getAsDouble();
            acc = 0;
            for (int i = 0; i < tamPoblacion; i++) {
                fitness[i] = max - fitness[i];
                acc += fitness[i];
            }
        }
        for(int i = 0;i <tamPoblacion; i++){
            poblacion[i].puntuacion = fitness[i]/acc;
        }
    }

    private void iniciarPoblacion() {
        poblacion = FactoriaIndividuos.getPoblacionInicial(seleccionPob,tamPoblacion,precision);
        fitness = new double[tamPoblacion];
    }

    private void guardarElite(int numElite){
        if(numElite == 0)
            return;
        Arrays.sort(poblacion, Comparator.reverseOrder());

        for (int i = 0; i < numElite; i++) {
            pobElite[i] = (Individuo) poblacion[i].clone();
        }
    }

    private void devolverElite(){
        if(pobElite.length == 0)
            return;

        int tamPoblacion = poblacion.length - 1;
        for (int i = 0; i < pobElite.length; i++) {
            poblacion[tamPoblacion - i] = pobElite[i];
        }
    }
}
