package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Arbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol1;

public class CruceArbol extends Cruce{

    public double getProbInterno() {
        return probInterno;
    }

    public void setProbInterno(double probInterno) {
        this.probInterno = probInterno;
    }

    private double probInterno = 0.9;

    @Override
    public void cruzar(Individuo individuo1, Individuo individuo2) {
        if (!(individuo1.cromosoma[0] instanceof Arbol) || !(individuo1.cromosoma[0] instanceof Arbol)) {
            System.out.println("Cuidado, necesito un Individuo<Arbol> para funcionar");
            return;
        }

        Arbol a1 = ((IndividuoArbol) individuo1).getArbol().getRandomHijo(probInterno, false);
        Arbol a2 = ((IndividuoArbol) individuo2).getArbol().getRandomHijo(probInterno, false);

        int index1 = a1.getIndex(), index2 = a2.getIndex();

        Arbol newA1 = (Arbol) a1.clone();
        Arbol newA2 = (Arbol) a2.clone();

        if(index1 != -1){
            a1.getPadre().insert(newA2,index1);
        }
        else {
            individuo1.cromosoma[0] = newA2;
            newA2.clearPadre();
        }
        if(index2 != -1){
            a2.getPadre().insert(newA1,index2);
        }
        else {
            individuo2.cromosoma[0] = newA1;
            newA1.clearPadre();
        }
    }

    public String toString() {
        return "Intercambio Subarboles";
    }
}
