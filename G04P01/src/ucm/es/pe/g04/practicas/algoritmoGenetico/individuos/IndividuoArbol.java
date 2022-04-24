package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.util.Arrays;
import java.util.Random;

public abstract class IndividuoArbol extends Individuo<Arbol>{
    protected static String terminales[];
    protected static String funciones[];
    protected static int elemsPorFuncion[];

    boolean useIF = true;
    int profundidad = 4;
    int tipoCreacion = 0;

    @Override
    public void init() {
        this.cromosoma = new Arbol[1];
        cromosoma[0] = new Arbol(profundidad, useIF);
        switch(tipoCreacion){
            case 0:
                getArbol().inicializacionCreciente(0,this);
                break;
            case 1:
                getArbol().inicializacionCompleta(0,this);
                break;
            case 2:
                int ini = new Random().nextInt(2);
                if(ini == 0) getArbol().inicializacionCreciente(0,this);
                else getArbol().inicializacionCompleta(0,this);
                break;
        }
    }

    public Arbol getArbol(){
        return cromosoma[0];
    }

    @Override
    public double getFitness() {
        double d = 0;
        for (int i = 0; i < ArbolesData.instance.casos.length; i++) {
            int[] aux = ArbolesData.instance.casos[i];
            if (ejecutaArbol(getArbol(), aux) == aux[aux.length - 1])
                d++;
        }
        return d;
    }

    public int ejecutaArbol(Arbol A, int[] caso){
        switch(A.getValor()){
            case("AND"):
                return ejecutaArbol(A.getHijos().get(0), caso) & ejecutaArbol(A.getHijos().get(1), caso);
            case("OR"):
                return ejecutaArbol(A.getHijos().get(0), caso) | ejecutaArbol(A.getHijos().get(1), caso);
            case("IF"):
                if (ejecutaArbol(A.getHijos().get(0), caso) == 1)
                    return ejecutaArbol(A.getHijos().get(1), caso);
                else
                    return ejecutaArbol(A.getHijos().get(2), caso);
            case("NOT"):
                return ejecutaArbol(A.getHijos().get(0), caso) == 1 ? 0 : 1;
//            case("D0"):
//            case("D1"):
//            case("D2"):
//            case("D3"):
//            case("A0"):
//            case("A1"):
            default:
                return caso[Arrays.stream(getTerminales()).toList().indexOf(A.getValor())];
        }
    }

    public abstract String[] getTerminales();

    public abstract String[] getFunciones();

    public abstract int[] getElemsPorFuncion();
}
