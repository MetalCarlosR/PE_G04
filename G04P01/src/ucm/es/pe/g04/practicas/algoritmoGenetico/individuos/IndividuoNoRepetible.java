package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

public abstract class IndividuoNoRepetible<T> extends Individuo<T>{

    // Mutacion por Intercambio
    @Override
    public void mutar(int i) {
        int p1 =  rand.nextInt(cromosoma.length);
        int p2 =  rand.nextInt(cromosoma.length);

        T aux = cromosoma[p1];
        cromosoma[p1] = cromosoma[p2];
        cromosoma[p2] = aux;
    }

    @Override
    public int tamGen(int n) {
        return 1;
    }
}
