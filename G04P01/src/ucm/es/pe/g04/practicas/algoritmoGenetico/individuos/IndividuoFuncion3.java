package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

public class IndividuoFuncion3  extends IndividuoBool{
    @Override
    public void init() {
        this.tamGenes = new int[2];
        this.min = new double[2];
        this.max = new double[2];
        this.min[0] = -512.000;
        this.min[1] = -512.000;
        this.max[0] = 512.000;
        this.max[1] = 512.000;
        this.tamGenes[0] = this.tamGen(0);
        this.tamGenes[1] = this.tamGen(1);
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

    public String toString() {
        return "Funcion 3";
    }
}
