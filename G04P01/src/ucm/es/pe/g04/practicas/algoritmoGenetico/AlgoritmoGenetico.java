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
    public AlgoritmoGenetico(Graficas g){
        grafica = g;
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

    public String getSeleccionFact() {
        return seleccionFact;
    }

    public void setSeleccionFact(String seleccionFact) {
        this.seleccionFact = seleccionFact;
    }

    public String getCruceFact() {
        return cruceFact;
    }

    public void setCruceFact(String cruceFact) {
        this.cruceFact = cruceFact;
    }

    public String getMutacionFact() {
        return mutacionFact;
    }

    public void setMutacionFact(String mutacionFact) {
        this.mutacionFact = mutacionFact;
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
    private boolean maximizar = false;
    private String seleccionPob = "Funcion2";
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
        poblacion = FactoriaIndividuos.getPoblacionInicial(seleccionPob,tamPoblacion,precision);
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
