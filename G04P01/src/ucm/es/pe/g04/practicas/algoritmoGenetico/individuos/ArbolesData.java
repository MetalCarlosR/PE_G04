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
    public static int casos [][];

    private static int media = -1;
    private static String casoPrueba = "EjemploPractica";


    public static void Init() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("input/P3/" + casoPrueba + ".txt" ));

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

    public static void CalcularMedia(AlgoritmoGenetico genetico){
        Individuo[] poblacion = genetico.getPoblacion();

        media = 0;

        for (Individuo i: poblacion) {
            IndividuoArbol a = (IndividuoArbol) i;
            media += a.getArbol().getNumNodos();
        }

        media /= poblacion.length;
    }


    public static int getMedia() {
        return media;
    }
}
