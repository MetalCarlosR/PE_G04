package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;


import java.util.Arrays;
import java.util.Random;

public class IndividuoArbol1 extends IndividuoArbol{
    public static String terminales[] = { "A0", "A1", "D0", "D1", "D2", "D3" };
    public static String funciones[] = { "AND", "OR", "NOT", "IF" };
    public static int elemsPorFuncion[] = { 2, 2, 1, 3 };

    boolean useIF = true;
    int profundidad = 4;
    int tipoCreacion = 0;

    @Override
    public double getValor() {
        return 0;
    }

    @Override
    public double getFitness() {
        double d = 0;
        for (int i = 0; i < Data.instance.casos.length; i++) {
            int[] aux = Data.instance.casos[i];
            if (ejecutaArbol(getArbol(), aux) != aux[aux.length - 1])
                d++;
        }
        return d;
    }

    @Override
    public double getFenotipo(int n) {
        return 0;
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
                return caso[Arrays.stream(terminales).toList().indexOf(A.getValor())];
        }
    }

    @Override
    public void mutar(int i) {

    }

    @Override
    public int tamGen(int n) {
        return 0;
    }

    @Override
    public String[] getTerminales() {
        return IndividuoArbol1.terminales;
    }

    @Override
    public String[] getFunciones() {
        return IndividuoArbol1.funciones;
    }

    @Override
    public int[] getElemsPorFuncion() {
        return IndividuoArbol1.elemsPorFuncion;
    }
}
