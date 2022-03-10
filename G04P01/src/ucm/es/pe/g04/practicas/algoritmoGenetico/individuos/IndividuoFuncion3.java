package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

public class IndividuoFuncion3  extends Individuo<Boolean>{
    //TODO: Que no sea copia de IndividuoFuncion1
    @Override
    public void init(double precision) {
        this.precision = precision;
        this.tamGenes = new int[2];
        this.min = new double[2];
        this.max = new double[2];
        this.min[0] = -512.000;
        this.min[1] = -512.000;
        this.max[0] = 512.000;
        this.max[1] = 512.000;
        this.tamGenes[0] = this.tamGen(min[0], max[0]);
        this.tamGenes[1] = this.tamGen(min[1], max[1]);
        int tamTotal = tamGenes[0] + tamGenes[1];
        this.cromosoma = new Boolean[tamTotal];
        for(int i = 0; i < tamTotal; i++) this.cromosoma[i] = this.rand.nextBoolean();
    }

    @Override
    public double getValor() {
        double x1 = this.getFenotipo(0), x2 = this.getFenotipo(1);
        return -(x2 + 47)*Math.sin(Math.sqrt(Math.abs(x2 + x1/2 + 47))) - x1 * Math.sin(Math.sqrt(Math.abs(x1 - (x2 + 47))));
    }

    @Override
    public double getFitness() {
        return getValor();
    }

    @Override
    public double getFenotipo(int n) {
        int ini = 0;
        String dec = "";
        for (int i = 0; i < n; i++) ini += this.tamGenes[i];
        for (int i = ini; i < ini + this.tamGenes[n]; i++) dec += cromosoma[i] ? '1' : '0';
        int val = Integer.parseInt(dec, 2);

        return min[n] + val * (max[n] - min[n]) / (Math.pow(2, this.tamGenes[n]) - 1);
    }

    @Override
    public void mutar(int i) {
        this.cromosoma[i] = !this.cromosoma[i];
    }

    @Override
    public String stringResult() {
        return "F: " + getFitness() + " x1: " + getFenotipo(0) + " x2: " + getFenotipo(1);
    }

    public String toString() {
        return "Funcion 3";
    }
}
