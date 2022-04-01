package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;


import java.util.ArrayList;
import java.util.Arrays;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.AvionesData.Avion;

public class IndividuoAviones extends IndividuoNoRepetible<Integer>{

    @Override
    public void init() {
        tamGenes = new int[AvionesData.instace.numAviones];
//        cromosoma = new Integer[]{7, 8, 9, 10, 11, 6, 5, 4, 3, 2, 1, 0};
//        cromosoma = new Integer[]{7, 11, 10, 3, 2, 9, 4, 5, 6, 8, 0, 1};
//        cromosoma = new Integer[]{7, 11, 10, 3, 2, 9, 6, 5, 4, 8, 0, 1};


        cromosoma = new Integer[AvionesData.instace.numAviones];
        for (int i = 0; i < AvionesData.instace.numAviones; i++) {
            cromosoma[i] = i;
            tamGenes[i] = 1;
        }
        for (int i = cromosoma.length - 1; i > 0; i--)
        {
            int index = rand.nextInt(i + 1);
            if(index == i)
                continue;
            // Simple swap
            int a = cromosoma[index];
            cromosoma[index] = cromosoma[i];
            cromosoma[i] = a;
        }
    }

    @Override
    public double getValor() {
        return 0;
    }

    @Override
    public double getFitness() {
        double acc = 0;

        double[] lastTime = new double[AvionesData.instace.numPistas];
        int[] lastAvionPista = new int[AvionesData.instace.numPistas];
        Arrays.fill(lastTime, 0);
        Arrays.fill(lastAvionPista, -1);

        for (int i = 0; i < AvionesData.instace.numAviones; i++) {
            Avion a = AvionesData.instace.aviones[cromosoma[i]];
            int pista = -1;
            double tiempo = Double.MAX_VALUE;
            for (int j = 0; j < AvionesData.instace.numPistas; j++){
                //Calcula el tiempo del ultimo avion + el delay que pide

                double t = lastTime[j];
                if (lastAvionPista[j] != -1) t += AvionesData.instace.delay[a.tipo + lastAvionPista[j] * 3];
                //Lo compara con su propio tel y se lo queda si es el menor

                if (Math.max(t, a.tel[j]) < tiempo){
                    tiempo = Math.max(t, a.tel[j]);
                    pista = j;
                }
            }
            lastAvionPista[pista] = a.tipo;
            lastTime[pista] = tiempo;
            acc += Math.pow(tiempo - a.tel[pista], 2);
//            acc += Math.pow(tiempo - Array.min(a.tel), 2);
        }

        return acc;
    }

    public Object[][] getMatriz(){

        ArrayList<Object>[] ret = new ArrayList[AvionesData.instace.numPistas];

        for (int i = 0; i < AvionesData.instace.numPistas; i++) {
                ret[i] = new ArrayList<>();
        }


        double[] lastTime = new double[AvionesData.instace.numPistas];
        int[] lastAvionPista = new int[AvionesData.instace.numPistas];
        Arrays.fill(lastTime, 0);
        Arrays.fill(lastAvionPista, -1);

        for (int i = 0; i < AvionesData.instace.numAviones; i++) {
            Avion a = AvionesData.instace.aviones[cromosoma[i]];
            int pista = -1;
            double tiempo = Double.MAX_VALUE;
            for (int j = 0; j < AvionesData.instace.numPistas; j++){
                //Calcula el tiempo del ultimo avion + el delay que pide

                double t = lastTime[j];
                if (lastAvionPista[j] != -1) t += AvionesData.instace.delay[a.tipo + lastAvionPista[j] * 3];
                //Lo compara con su propio tel y se lo queda si es el menor

                if (Math.max(t, a.tel[j]) < tiempo){
                    tiempo = Math.max(t, a.tel[j]);
                    pista = j;
                }
            }
            lastAvionPista[pista] = a.tipo;
            lastTime[pista] = tiempo;
            ret[pista].add(cromosoma[i] + ": " + tiempo);
        }

        int maxSize = 0;
        for (int i = 0; i < AvionesData.instace.numPistas; i++) {
            if(ret[i].size() > maxSize)
                maxSize = ret[i].size();
        }

        for (ArrayList<Object> l:
             ret) {
            for (int i = 0; i < maxSize - l.size(); i++) {
                l.add(null);
            }
        }

        Object[][] realRet = new Object[AvionesData.instace.numPistas][];

        for (int i = 0; i < AvionesData.instace.numPistas; i++) {
            realRet[i] = ret[i].toArray();
        }

        return realRet;
    }

    @Override
    public double getFenotipo(int n) {
        return cromosoma[n];
    }

    public String toString() {
        return "Aviones";
    }

}
