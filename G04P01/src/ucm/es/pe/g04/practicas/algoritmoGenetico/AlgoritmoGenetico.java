package ucm.es.pe.g04.practicas.algoritmoGenetico;

import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.*;
import ucm.es.pe.g04.practicas.gui.Graficas;

import java.util.Arrays;
import java.util.Comparator;

public class AlgoritmoGenetico {
    public AlgoritmoGenetico(){
        //Opciones predeterminadas
        seleccion = new SeleccionRuleta();
        cruce = new CruceMonopunto();
        mutacion = new MutacionBasica();
        original = new IndividuoFuncion1();
    }

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
    private Individuo[] poblacion;
    private double[] fitness;
    private double fitnessMedio;
    private int maxGeneraciones = 100;
    private double probCruce = 0.6;
    private double probMutacion = 0.05;
    private int tamTorneo = 2;
    private double truncamiento = 0.5;
    private Individuo mejorAbsoluto;
    private Individuo mejorGeneracion;
    private double elitismo = 0.05;
    private Individuo[] pobElite;
    private double precision = 0.001;

    public static boolean isMaximizar() {
        return maximizar;
    }

    public static void setMaximizar(boolean max) {
        maximizar = max;
    }

    private static boolean maximizar = false;

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

            grafica.generarGrafica(mejorAbsoluto.getFitness(), mejorGeneracion.getFitness() ,fitnessMedio);

            //Siguiente generacion
            generacionActual++;
        }

        System.out.println("Mejor:" + mejorAbsoluto.stringResult());
    }


    private void evaluar() {
        double acc = 0;
        mejorGeneracion = poblacion[0];
        for (int i = 0; i < tamPoblacion; i++){
            double aux = poblacion[i].getFitness();
            fitness[i] = aux;
            acc += aux;
            if(aux > mejorGeneracion.getFitness() && maximizar || aux < mejorGeneracion.getFitness() && !maximizar)
                mejorGeneracion = poblacion[i];
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
