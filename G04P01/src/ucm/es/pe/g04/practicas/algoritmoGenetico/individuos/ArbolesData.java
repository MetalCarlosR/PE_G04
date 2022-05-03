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

    public static double media = Double.MAX_VALUE;


    public static void Init(IndividuoArbol original) {
        try {
            String filename = casoPrueba + (original instanceof IndividuoArbol1 ? "1" : "2");
            BufferedReader file = new BufferedReader(new FileReader("input/P3/" + filename + ".txt"));

            List<String> lines = Files.readAllLines(Path.of("input/P3/" + filename + ".txt"));
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

        media = 0;

        for (Individuo i: poblacion) {
            IndividuoArbol a = (IndividuoArbol) i;
            a.getArbol().propagarNodos();
            media += a.getArbol().getNumNodos();
        }

        media /= poblacion.length;
    }

    public static void GraphicsData(AlgoritmoGenetico genetico){
        genetico.extraData = media;
    }

    public static void DevolverValores(AlgoritmoGenetico genetico){
        k = Double.MIN_VALUE;
        media = Double.MAX_VALUE;
        Individuo[] poblacion = genetico.getPoblacion();

        double newMedia = 0;

        for (Individuo i: poblacion) {
            IndividuoArbol a = (IndividuoArbol) i;
            newMedia += a.calculaFitness();
        }

        genetico.fitnessMedio = newMedia/poblacion.length;
        IndividuoArbol mG = (IndividuoArbol) genetico.mejorGeneracion;
        mG.calculaFitness();
    }


    public static double getK() {
        return k == Double.MIN_VALUE ? 0 : k;
    }
}
