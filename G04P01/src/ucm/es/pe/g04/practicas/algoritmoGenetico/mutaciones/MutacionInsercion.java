package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class MutacionInsercion extends Mutacion{
    public int get_inserciones() {
        return _inserciones;
    }

    public void set_inserciones(int _inserciones) {
        this._inserciones = _inserciones;
    }

    private int _inserciones = 2;
    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {
        for (Individuo i : poblacion) {
            if(r.nextDouble() < probMutacion)
            {
                for (int j = 0; j < _inserciones; j++){
                    int individuo = r.nextInt(i.cromosoma.length), lugar = r.nextInt(i.cromosoma.length);
                    int dir = individuo > lugar ? -1 : 1;
                    for (; individuo != lugar; individuo += dir) {
                        Object aux = i.cromosoma[individuo];
                        i.cromosoma[individuo] = i.cromosoma[individuo + dir];
                        i.cromosoma[individuo + dir] = aux;
                    }
                }
            }
        }
    }

    public String toString() {
        return "Insercion";
    }
}
