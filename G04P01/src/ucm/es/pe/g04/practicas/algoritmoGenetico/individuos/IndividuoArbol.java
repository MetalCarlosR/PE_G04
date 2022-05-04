package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

import java.util.Arrays;
import java.util.Random;

public abstract class IndividuoArbol extends Individuo<Arbol> {
    protected static String terminales[];
    protected static String funciones[];
    protected static int elemsPorFuncion[];

    private boolean useIF = true;
    private int profundidad = 4;
    private String tipoCreacion = "Completa";

    private boolean bloating = true;
    public boolean isUseIF() {
        return useIF;
    }

    public void setUseIF(boolean useIF) {
        this.useIF = useIF;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public String getTipoCreacion() {
        return tipoCreacion;
    }

    public void setTipoCreacion(String tipoCreacion) {
        this.tipoCreacion = tipoCreacion;
    }

    public boolean getBloating() {
        return bloating;
    }

    public void setBloating(boolean bloating) {
        this.bloating = bloating;
    }

    @Override
    public void init() {
        this.cromosoma = new Arbol[1];
        cromosoma[0] = new Arbol(profundidad, useIF);
        switch (tipoCreacion) {
            case "Creciente":
                getArbol().inicializacionCreciente(0, this);
                break;
            case "Completa":
                getArbol().inicializacionCompleta(0, this);
                break;
            case "RampedAndHalf":
                profundidad = rand.nextInt(2, profundidad + 1);
                int ini = new Random().nextInt(2);
                if (ini == 0) getArbol().inicializacionCreciente(0, this);
                else getArbol().inicializacionCompleta(0, this);
                break;
            default:
                System.out.println("Error, tipo de creación no encontrado");
                getArbol().inicializacionCompleta(0, this);
                break;
        }
    }

    public Arbol getArbol() {
        return cromosoma[0];
    }

    public double calculaFitness() {

        double bloatingFactor = bloating && getArbol().getNumNodos() > ArbolesData.media && rand.nextDouble()  < 0.5 ? 0.5: 1;

        double d = 0;
        for (int i = 0; i < ArbolesData.casos.length; i++) {
            int[] aux = ArbolesData.casos[i];
            if (ejecutaArbol(getArbol(), aux) == aux[aux.length - 1])
                d++;
        }
        
        fitness = d * bloatingFactor;
        return fitness;
    }

    public int ejecutaArbol(Arbol A, int[] caso) {
        switch (A.getValor()) {
            case ("AND"):
                return ejecutaArbol(A.getHijos().get(0), caso) & ejecutaArbol(A.getHijos().get(1), caso);
            case ("OR"):
                return ejecutaArbol(A.getHijos().get(0), caso) | ejecutaArbol(A.getHijos().get(1), caso);
            case ("IF"):
                if (ejecutaArbol(A.getHijos().get(0), caso) == 1)
                    return ejecutaArbol(A.getHijos().get(1), caso);
                else
                    return ejecutaArbol(A.getHijos().get(2), caso);
            case ("NOT"):
                return ejecutaArbol(A.getHijos().get(0), caso) == 1 ? 0 : 1;
            default:
                return caso[Arrays.stream(getTerminales()).toList().indexOf(A.getValor())];
        }
    }

    public abstract String[] getTerminales();

    public abstract String[] getFunciones();

    public abstract int[] getElemsPorFuncion();

    @Override
    public String stringResult() {
        String s = "Fitness: " + getFitness() + "\n";

        s += getArbol().toString();

        return s;
    }

    @Override
    public Object clone() {
        IndividuoArbol newClone = (IndividuoArbol) super.clone();
        newClone.cromosoma[0] = (Arbol) this.cromosoma[0].clone();
        return newClone;
    }


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
        return 1;
    }
}
