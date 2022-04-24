package ucm.es.pe.g04.practicas.algoritmoGenetico.individuos;

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

    public abstract String[] getTerminales();

    public abstract String[] getFunciones();

    public abstract int[] getElemsPorFuncion();
}
