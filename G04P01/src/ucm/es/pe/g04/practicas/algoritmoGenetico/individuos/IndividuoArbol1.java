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
