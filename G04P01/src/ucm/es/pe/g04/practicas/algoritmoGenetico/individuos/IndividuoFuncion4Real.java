package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

public class IndividuoFuncion4Real  extends IndividuoFuncion<Double>{

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    int n = 2;

    @Override
    public void init() {
        this.tamGenes = new int[n];
        this.min = new double[n];
        this.max = new double[n];
        for (int i = 0; i < n; i++){
            this.min[i] = 0;
            this.max[i] = Math.PI;
            this.tamGenes[i] = this.tamGen(i);
        }
        int tamTotal = tamGenes[0]*n;
        this.cromosoma = new Double[tamTotal];
        for(int i = 0; i < tamTotal; i++) this.cromosoma[i] = this.rand.nextDouble() * this.max[i];
    }

    @Override
    public double getValor() {
        double valor = 0;
        for (int i = 1; i <= n; i++) {
            double x = this.getFenotipo(i-1);
            valor += Math.sin(x) * Math.pow(Math.sin((i+1)*Math.pow(x, 2)/Math.PI), 20);
        }
        valor = -valor;
        return valor;
    }


    @Override
    public double getFenotipo(int n) {
        return cromosoma[n];
    }

    @Override
    public void mutar(int i) {
        cromosoma[i] = this.rand.nextDouble() * this.max[i];
    }

    @Override
    public int tamGen(int n) {
        return 1;
    }

    public String toString() {
        return "Funcion 4 Real";
    }
}
