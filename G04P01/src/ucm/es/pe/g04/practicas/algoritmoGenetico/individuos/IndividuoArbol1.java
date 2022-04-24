package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;


import java.util.Random;

public class IndividuoArbol1 extends Individuo<Arbol>{
    public static final String terminales[] = { "A0", "A1", "D0", "D1", "D2", "D3" };
    public static final String funciones[] = { "AND", "OR", "NOT", "IF" };
    public static final int elemsPorFuncion[] = { 2, 2, 1, 3 };

    boolean useIF = true;
    int profundidad = 4;
    int tipoCreacion = 0;

    @Override
    public void init() {
        this.cromosoma = new Arbol[1];
        cromosoma[0] = new Arbol(profundidad, useIF);
        switch(tipoCreacion){
            case 0:
                getArbol().inicializacionCreciente(0);
                break;
            case 1:
                getArbol().inicializacionCompleta(0);
                break;
            case 2:
                int ini = new Random().nextInt(2);
                if(ini == 0) getArbol().inicializacionCreciente(0);
                else getArbol().inicializacionCompleta(0);
                break;
        }
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

    public boolean ejecutaArbol(Arbol A){
        switch(A.getValor()){
            case("AND"):
                return ejecutaArbol(A.getHijos().get(0)) && ejecutaArbol(A.getHijos().get(1));
            case("OR"):
                return ejecutaArbol(A.getHijos().get(0)) || ejecutaArbol(A.getHijos().get(1));
            case("IF"):
                if (ejecutaArbol(A.getHijos().get(0)))
                    return ejecutaArbol(A.getHijos().get(1));
                else
                    return ejecutaArbol(A.getHijos().get(2));
            case("NOT"):
                return !ejecutaArbol(A.getHijos().get(0));
            case("D0"):
            case("D1"):
            case("D2"):
            case("D3"):
            case("A0"):
            case("A1"):
            default:
                return true;
        }
    }

    @Override
    public void mutar(int i) {

    }

    public Arbol getArbol(){
        return cromosoma[0];
    }

    @Override
    public int tamGen(int n) {
        return 0;
    }
}
