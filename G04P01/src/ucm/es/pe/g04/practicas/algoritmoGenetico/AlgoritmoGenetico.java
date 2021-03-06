package ucm.es.pe.g04.practicas.algoritmoGenetico;

import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.*;
import ucm.es.pe.g04.practicas.gui.Graficas;

import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Consumer;

public class AlgoritmoGenetico {
    public AlgoritmoGenetico(Graficas g) { grafica = g;}
    public AlgoritmoGenetico() {}
    public static AlgoritmoGenetico Init(Graficas g){
        instance = new AlgoritmoGenetico(g);
        return instance;
    }

    public static AlgoritmoGenetico instance;

    public int getTamPoblacion() {
        return tamPoblacion;
    }

    public void setTamPoblacion(int tamPoblacion) {
        this.tamPoblacion = tamPoblacion;
    }

    public int getMaxGeneraciones() {
        return maxGeneraciones;
    }

    public void setMaxGeneraciones(int maxGeneraciones) {
        this.maxGeneraciones = maxGeneraciones;
    }

    public double getProbCruce() {
        return probCruce;
    }

    public void setProbCruce(double probCruce) {
        this.probCruce = probCruce;
    }

    public double getProbMutacion() {
        return probMutacion;
    }

    public void setProbMutacion(double probMutacion) {
        this.probMutacion = probMutacion;
    }

    public int getTamTorneo() {
        return tamTorneo;
    }

    public void setTamTorneo(int tamTorneo) {
        this.tamTorneo = tamTorneo;
    }

    public double getTruncamiento() {
        return truncamiento;
    }

    public void setTruncamiento(double truncamiento) {
        this.truncamiento = truncamiento;
    }

    public double getPrecision() {
        return precision;
    }

    public void setPrecision(double precision) {
        this.precision = precision;
    }

    public double getElitismo() {
        return elitismo;
    }

    public void setElitismo(double elitismo) {
        this.elitismo = elitismo;
    }


    public Seleccion getSeleccion() {
        return seleccion;
    }

    public void setSeleccion(Seleccion seleccion) {
        this.seleccion = seleccion;
    }

    public Cruce getCruce() {
        return cruce;
    }

    public void setCruce(Cruce cruce) {
        this.cruce = cruce;
    }

    public Mutacion getMutacion() {
        return mutacion;
    }

    public void setMutacion(Mutacion mutacion) {
        this.mutacion = mutacion;
    }

    private int tamPoblacion = 100;

    public Individuo[] getPoblacion() {
        return poblacion;
    }

    private Individuo[] poblacion;
    private double[] fitness;
    public double fitnessMedio;
    private int maxGeneraciones = 100;
    private double probCruce = 0.6;
    private double probMutacion = 0.05;
    private int tamTorneo = 2;
    private double truncamiento = 0.5;

    public double extraData = Double.MIN_VALUE;

    public String extraDataName = "Extra Data";

    public Consumer<AlgoritmoGenetico> preFunction = null;
    public Consumer<AlgoritmoGenetico> postEvaluar = null;
    public Consumer<AlgoritmoGenetico> preEvaluar = null;

    public Consumer<AlgoritmoGenetico> extraDataFunction = null;

    public Individuo getMejorAbsoluto() {
        return mejorAbsoluto;
    }

    public Individuo mejorAbsoluto;

    public Individuo mejorGeneracion;
    private double elitismo = 0.05;
    private Individuo[] pobElite;
    private double precision = 0.001;

    public boolean getMaximizar() {
        return maximizar;
    }

    public void setMaximizar(boolean max) {
        maximizar = max;
    }

    private boolean maximizar = false;

    public Individuo getOriginal() {
        return original;
    }

    public void setOriginal(Individuo original) {
        this.original = original;
    }

    private Individuo original;


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

        //grafica = new Graficas();

        long startTime = System.nanoTime();

        System.out.println("Start");

        if(preEvaluar != null)
            preEvaluar.accept(this);

        evaluar();

        if(postEvaluar != null)
            postEvaluar.accept(this);

        while(generacionActual < this.maxGeneraciones) {

            if(preFunction != null)
                preFunction.accept(this);
            //Elitismo
            guardarElite(numElite);

            //Seleccion
            poblacion = seleccion.seleccionar(poblacion);

            //Cruce
            cruce.reproduccion(poblacion, probCruce);
//
            //Mutacion
            mutacion.mutar(poblacion, probMutacion);

            devolverElite();

            if(preEvaluar != null)
                preEvaluar.accept(this);

            evaluar();

            if(postEvaluar != null)
                postEvaluar.accept(this);

            if(extraDataFunction != null)
               extraDataFunction.accept(this);

            grafica.siguienteGeneracion(mejorAbsoluto.getFitness(), mejorGeneracion.getFitness() ,fitnessMedio, extraData);

            //Siguiente generacion
            generacionActual++;
        }

        grafica.generarGrafica(extraDataName);
        //System.out.println("Mejor:" + mejorAbsoluto.stringResult());

        long endTime = System.nanoTime();

        double duration = (endTime - startTime)/1e9;

        System.out.println("Tiempo total: " + duration);
        System.out.println("Mejor:" + mejorAbsoluto.getFitness());
        System.out.println(extraDataName +": " + extraData);
        System.out.println(mejorAbsoluto.stringResult());
    }


    private void evaluar() {
        double acc = 0;
        mejorGeneracion = poblacion[0];
        for (int i = 0; i < tamPoblacion; i++){
            double aux = poblacion[i].calculaFitness();
            fitness[i] = aux;
            acc += aux;
            if((aux > mejorGeneracion.getFitness() && maximizar) || (aux < mejorGeneracion.getFitness() && !maximizar))
            {
                double aux2 = mejorGeneracion.getFitness();
                mejorGeneracion = poblacion[i];
            }

        }
        mejorGeneracion = (Individuo) mejorGeneracion.clone();
        fitnessMedio = acc / tamPoblacion;
        if(mejorGeneracion.getFitness() > mejorAbsoluto.getFitness() && maximizar || mejorGeneracion.getFitness() < mejorAbsoluto.getFitness() && !maximizar)
            mejorAbsoluto = (Individuo) mejorGeneracion.clone();


        if(!maximizar){
            double max = Arrays.stream(fitness).max().getAsDouble();
            acc = 0;
            for (int i = 0; i < tamPoblacion; i++) {
                fitness[i] = max - fitness[i];
                acc += fitness[i];
            }
        }
        else {

            if(Arrays.stream(fitness).min().getAsDouble() < 0){
                double min = Arrays.stream(fitness).min().getAsDouble();
                for (int i = 0; i < tamPoblacion ; i++) {
                    fitness[i] -= min;
                }
            }

            double a = fitnessMedio/(fitnessMedio - Arrays.stream(fitness).min().getAsDouble());
            double b = (1 - a) * fitnessMedio;
            double aux;
            acc = 0;

            for (int i = 0; i < tamPoblacion; i++) {
                aux = a * fitness[i] + b;
                fitness[i] = aux;
                acc +=  aux;
            }
        }

        for(int i = 0;i <tamPoblacion; i++){
            poblacion[i].puntuacion = fitness[i]/acc;
        }
    }


    private void iniciarPoblacion() {
        poblacion = new Individuo[tamPoblacion];

        original.init();
        poblacion[0] = original;


        for (int i = 1; i < tamPoblacion; i++) {
            poblacion[i] = (Individuo) original.clone();
            poblacion[i].init();
        }
        //poblacion = FactoriaIndividuos.getPoblacionInicial(seleccionPob,tamPoblacion,precision);
        fitness = new double[tamPoblacion];
    }

    private void guardarElite(int numElite){
        if(numElite == 0)
            return;

        Individuo[] pobSort = poblacion.clone();
        Arrays.sort(pobSort, Comparator.reverseOrder());

        for (int i = 0; i < numElite; i++) {
            pobElite[i] = (Individuo) pobSort[i].clone();
        }
    }

    private void devolverElite(){
        if(pobElite.length == 0)
            return;

        Arrays.sort(poblacion, Comparator.reverseOrder());

        int tamPoblacion = poblacion.length - 1;
        for (int i = 0; i < pobElite.length; i++) {
            poblacion[tamPoblacion - i] = pobElite[i];
        }
    }
}
