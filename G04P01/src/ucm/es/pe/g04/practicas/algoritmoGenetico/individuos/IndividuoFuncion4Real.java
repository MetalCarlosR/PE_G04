package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

public class IndividuoFuncion4Real  extends Individuo<Double>{

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }

    int n = 2;

    //TODO: Que no sea copia de IndividuoFuncion1
    @Override
    public void init(double precision) {
        this.precision = precision;
        this.tamGenes = new int[2];
        this.min = new double[2];
        this.max = new double[2];
        this.min[0] = -3.000;
        this.min[1] = 4.100;
        this.max[0] = 12.100;
        this.max[1] = 5.800;
        this.tamGenes[0] = this.tamGen(min[0], max[0]);
        this.tamGenes[1] = this.tamGen(min[1], max[1]);
        int tamTotal = tamGenes[0] + tamGenes[1];
        this.cromosoma = new Double[tamTotal];
        for(int i = 0; i < tamTotal; i++) this.cromosoma[i] = this.rand.nextDouble();
    }

    @Override
    public double getValor() {
        double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
        return (21.5 + x1 * Math.sin(4 * Math.PI * x1) + x2 * Math.sin(20 * Math.PI * x2));
    }

    @Override
    public double getFitness() {
        return getValor();
    }

    @Override
    public double getFenotipo(int n) {
        return 0;
    }

    @Override
    public void mutar(int i) {

    }

    @Override
    public String stringResult() {
        return "F: " + getFitness() + " x1: " + getFenotipo(0) + " x2: " + getFenotipo(1);
    }

    public String toString() {
        return "Funcion 4 Real";
    }
}
