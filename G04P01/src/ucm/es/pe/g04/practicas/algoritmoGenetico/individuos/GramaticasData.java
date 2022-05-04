package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class GramaticasData {
    public static int casos[][];

    public static Map<String, List<List<String>>> gramatica;
    public static List<String> reglas;
    private static String casoGramatica = "Gramatica";
    private static String casoPrueba = "EjemploPractica";


    public static void Init() {
        try {
            String filename = casoGramatica + (AlgoritmoGenetico.instance.getOriginal() instanceof IndividuoGramatica1 ? "1" : "2");
            BufferedReader file = new BufferedReader(new FileReader("input/P3/" + filename + ".txt"));

            String s = Files.readString(Path.of("input/P3/" + filename + ".txt"));
            gramatica = new HashMap<String, List<List<String>>>();
            reglas = new ArrayList<String>();
            //Separamos por líneas la gramatica
            String r[]= s.split("\r\n");

            for(int i = 0; i < r.length;i++) {
                //Separamos las reglas de sus variables
                String regla[] = r[i].split(" ::= ");

                //Separamos cada una de las variables
                String reglas_sig[] = regla[1].split(" \\| ");

                //Creamos la lista para las listas de reglas
                List<List<String>> list1 = new ArrayList<List<String>>();
                for (int j = 0; j < reglas_sig.length; j++) {
                    //Hacemos un split de las reglas por los espacios
                    String reglas_juntas[] = reglas_sig[j].split(" ");
                    list1.add(Arrays.stream(reglas_juntas).toList());
                }
                gramatica.put(regla[0], list1);
                reglas.add(regla[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String filename = casoPrueba + (AlgoritmoGenetico.instance.getOriginal() instanceof IndividuoGramatica1 ? "1" : "2");

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
}
