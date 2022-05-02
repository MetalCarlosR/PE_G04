package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ArbolesData {
    public static int casos[][];

    private static double k = Double.MIN_VALUE;
    private static String casoPrueba = "EjemploPractica";


    public static void Init() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("input/P3/" + casoPrueba + ".txt"));

            List<String> lines = Files.readAllLines(Path.of("input/P3/" + casoPrueba + ".txt"));
            casos = new int[lines.size()][];

            for (int i = 0; i < lines.size(); i++) {
                String[] split = lines.get(i).split(" ");
                casos[i] = new int[split.length];
                for (int j = 0; j < split.length; j++) {
                    casos[i][j] = Integer.parseInt(split[j]);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void CalcularFactor(AlgoritmoGenetico genetico) {
        Individuo[] poblacion = genetico.getPoblacion();
        int tamPoblacion = poblacion.length;
        int[] nodos = new int[tamPoblacion];
        double[] fitness = new double[tamPoblacion];
        k = Double.MIN_VALUE;

        double mediaFitness = 0;
        double mediaTamaño = 0;

        for (int i = 0; i < tamPoblacion; i++) {
            IndividuoArbol a = (IndividuoArbol) poblacion[i];
            int numNodos = a.getArbol().getNumNodos();
            double f = a.calculaFitness();
            nodos[i] = numNodos;
            fitness[i] = numNodos;
            mediaTamaño += numNodos;
            mediaFitness += f;
        }

        mediaTamaño /= tamPoblacion;
        mediaFitness /= tamPoblacion;

        double varianza = 0;
        double covarianza = 0;
        for (int i = 0; i < tamPoblacion; i++) {
            IndividuoArbol a = (IndividuoArbol) poblacion[i];

            varianza += Math.pow(nodos[i] - mediaTamaño, 2);
            covarianza += (nodos[i] - mediaTamaño)*(fitness[i] - mediaFitness);
        }

        varianza /= tamPoblacion;
        covarianza /= tamPoblacion;

        k = covarianza/varianza;

        genetico.getMejorAbsoluto().calculaFitness();
    }

    public static void DevolverValores(AlgoritmoGenetico genetico){
        k = Double.MIN_VALUE;
        Individuo[] poblacion = genetico.getPoblacion();

        double newMedia = 0;

        for (Individuo i: poblacion) {
            newMedia += i.calculaFitness();
        }

        genetico.fitnessMedio = newMedia/poblacion.length;
        genetico.mejorAbsoluto.calculaFitness();
        genetico.mejorGeneracion.calculaFitness();
    }


    public static double getK() {
        return k == Double.MIN_VALUE ? 0 : k;
    }
}
