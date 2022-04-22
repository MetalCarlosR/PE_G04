package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;


public class IndividuoArbol1 extends Individuo<Arbol>{
    public static final String terminales[] = { "A0", "A1", "D0", "D1", "D2", "D3" };
    public static final String funciones[] = { "AND", "OR", "NOT", "IF" };
    public static final int elemsPorFuncion[] = { 2, 2, 1, 3 };

    @Override
    public void init() {
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
        return 0;
    }

    @Override
    public void mutar(int i) {

    }

    @Override
    public int tamGen(int n) {
        return 0;
    }
}
