package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

public class IndividuoGramatica1 extends IndividuoGramatica{
    public static String terminales[] = { "A0", "A1", "D0", "D1", "D2", "D3" };

    @Override
    public String[] getTerminales() {
        return IndividuoGramatica1.terminales;
    }

    public String toString() {
        return "IndividuoGramatica1";
    }
}
