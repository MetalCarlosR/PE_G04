package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class AvionesData {

    public static class Avion{
        // Tipo de avión ( 0-Pesado, 1-Grande, 2-Pequeño)
        int tipo;
        // Lista de tiempo estimado de llegada para n pistas
        double[] tel;

        public Avion(int tipo, int tamTel){
            this.tipo = tipo;
            tel = new double[tamTel];
        }

        public void setTel(int i , double tel){
            this.tel[i] = tel;
        }

        public void setTel(double[] tel){
            this.tel = tel.clone();
        }
    }

    public static AvionesData instace;

    public String fileName = "EjemploPractica";
    public int numPistas;
    public int numAviones;
    public Avion[] aviones;
    public double[] delay = new double[9];

    public static AvionesData Init() {
        instace = new AvionesData();
        return instace;
    }

    public void init(){
        try {
            BufferedReader file = new BufferedReader(new FileReader("input/P2/" + fileName + ".txt" ));
            numPistas = Integer.parseInt(file.readLine());
            numAviones = Integer.parseInt(file.readLine());
            aviones = new Avion[numAviones];
            String line;
            for (int i = 0; i < numAviones; i++) {
                line = file.readLine();
                String[] split = line.split(" ");
                Avion avion = new Avion(Integer.parseInt(split[0]), numPistas );

                for (int j = 0; j < 3; j++) {
                    avion.setTel(j, Double.parseDouble(split[1 + j]));
                }
                aviones[i] = avion;
            }

            line = file.readLine();
            String[] split = line.split(" ");

            for (int i = 0; i < 9; i++) {
                delay[i] = Double.parseDouble(split[i]);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
