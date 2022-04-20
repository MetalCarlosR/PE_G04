package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

public class Arbol<Tipo> {
    Arbol padre; Arbol[] hijos;
    Tipo tipo;
    int num_nodos; // número de nodos
    int profundidad; // profundidad del árbol
}

interface Tipo {
    int getLength();
    void op();
}

enum Tipo1 implements Tipo{
    A,B,C,D,E,F;

    @Override
    public int getLength() {
        return values().length;
    }

    @Override
    public void op() {
        switch (this) {
            case A -> {
            }
            case B -> {
            }
            case C -> {
            }
            case D -> {
            }
            case E -> {
            }
            case F -> {
            }
        }
    }
}
