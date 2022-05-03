package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

public class IndividuoArbol2 extends IndividuoArbol{

    public static String terminales[] = { "A0", "A1","A2", "D0", "D1", "D2", "D3","D4","D5","D6","D7" };
    public static String funciones[] = { "AND", "OR", "NOT", "IF" };
    public static int elemsPorFuncion[] = { 2, 2, 1, 3 };

    @Override
    public String[] getTerminales() {
        return IndividuoArbol2.terminales;
    }

    @Override
    public String[] getFunciones() {
        return IndividuoArbol2.funciones;
    }

    @Override
    public int[] getElemsPorFuncion() {
        return IndividuoArbol2.elemsPorFuncion;
    }


    public String toString() {
        return "IndividuoArbol2";
    }
}
