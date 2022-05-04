package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public abstract class IndividuoGramatica extends Individuo<Double>{

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    public static String terminales[] = { "A0", "A1", "D0", "D1", "D2", "D3" };
    public abstract String[] getTerminales();


    int n = 8;
    int wraps = 0;

    public int getMaxWraps() {
        return maxWraps;
    }

    public void setMaxWraps(int maxWraps) {
        this.maxWraps = maxWraps;
    }

    int maxWraps = 2;
    int it = 0;

    @Override
    public void init() {
        do {
            it = 0; wraps = 0;
            this.tamGenes = new int[n];
            for (int i = 0; i < n; i++) {
                this.tamGenes[i] = 1;
            }
            int tamTotal = tamGenes[0] * n;
            this.cromosoma = new Double[tamTotal];
            for (int i = 0; i < tamTotal; i++) this.cromosoma[i] = Double.valueOf(this.rand.nextInt(256));
            expr(GramaticasData.casos[0]);
        } while (wraps > maxWraps);
        it = 0; wraps = 0;
    }

    @Override
    public double getValor() {
        return getFitness();
    }


    @Override
    public double getFenotipo(int n) {
        return cromosoma[n];
    }

    @Override
    public void mutar(int i) {
        cromosoma[i] = Double.valueOf(this.rand.nextInt(256));
    }

    @Override
    public int tamGen(int n) {
        return 1;
    }

    @Override
    public double calculaFitness() {
        double d = 0;
        it = 0; wraps = 0;

        for (int i = 0; i < GramaticasData.casos.length; i++) {
            int[] aux = GramaticasData.casos[i];
            if (expr(aux) == aux[aux.length - 1])
                d++;
            it = 0; wraps = 0;
        }
        fitness = d;
        return d;
    }

    private int expr(int[] caso) {
        if (it >= cromosoma.length){
            it = 0;
            this.wraps++;
        }
        if(this.wraps > this.maxWraps)
            return it;
        int instruc = cromosoma[it].intValue() % GramaticasData.gramatica.get("<expr>").size();
        if (instruc < 0) instruc *= -1;
        switch (instruc) {
            case 0:
                it++;
                return expr(caso) & expr(caso);
            case 1:
                it++;
                return expr(caso) | expr(caso);
            case 2:
                it++;
                return expr(caso) == 1 ? 0 : 1;
            case 3:
                it++;
                if (expr(caso) == 1){
                    it++;
                    return expr(caso);
                }
                else{
                    it+= 2;
                    return expr(caso);
                }
            default:
                it++;
                String s = GramaticasData.gramatica.get("<expr>").get(instruc).get(0);
                int index = Arrays.stream(getTerminales()).toList().indexOf(s);
                return caso[index];

        }

    }
    public String toString() {
        return "IndividuoGramatica";
    }

}
