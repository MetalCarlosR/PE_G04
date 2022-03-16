package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;


import java.io.*;

public class IndividuoAviones extends Individuo<Integer>{

    public class Avion{
        // Tipo de avión ( 1-Pesado, 2-Grande, 3-Pequeño)
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


    String fileName = "EjemploPractica";
    int numPistas;
    int numAviones;
    Avion[] aviones;



    @Override
    public void init() {
        try {
            BufferedReader file = new BufferedReader(new FileReader("input/P2/" + this.fileName + ".txt" ));
            numPistas = Integer.parseInt(file.readLine());
            numAviones = Integer.parseInt(file.readLine());
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

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        aviones = new Avion[numAviones];

    }

    @Override
    public double getValor() {
        return 0;
    }

    @Override
    public double getFitness() {
        return 0;
    }

    @Override
    public double getFenotipo(int n) {
        return cromosoma[n];
    }

    @Override
    public void mutar(int i) {

    }

    @Override
    public int tamGen(int n) {
        return 1;
    }

}
