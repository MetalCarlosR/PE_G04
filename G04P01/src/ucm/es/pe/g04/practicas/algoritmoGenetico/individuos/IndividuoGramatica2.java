package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

public class IndividuoGramatica2  extends IndividuoGramatica{
    public static String terminales[] = { "A0", "A1", "A2", "D0", "D1", "D2", "D3", "D4", "D5", "D6", "D7" };

    @Override
    public String[] getTerminales() {
        return IndividuoGramatica2.terminales;
    }

    public String toString() {
        return "IndividuoGramatica2";
    }
}
