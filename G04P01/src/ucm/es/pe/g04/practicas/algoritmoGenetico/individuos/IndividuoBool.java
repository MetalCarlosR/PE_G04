package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

public abstract class IndividuoBool extends IndividuoFuncion<Boolean>{

    protected double precision = 0.001;

    public double getPrecision(){ return precision;}

    public void setPrecision(double precision){ this.precision = precision;}

    @Override
    public int tamGen(int n) {
        return (int) (Math.log10(((max[n] - min[n]) / precision) + 1) / Math.log10(2));
    }

    @Override
    public void mutar(int i) {
        this.cromosoma[i] = !this.cromosoma[i];
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
}
