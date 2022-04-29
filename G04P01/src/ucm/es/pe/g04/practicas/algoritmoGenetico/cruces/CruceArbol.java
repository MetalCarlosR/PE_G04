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

        Arbol a1 = ((IndividuoArbol) individuo1).getArbol().getRandomHijo(probInterno);
        Arbol a2 = ((IndividuoArbol) individuo2).getArbol().getRandomHijo(probInterno);

        Arbol padre1 = a1.getPadre();
        Arbol padre2 = a2.getPadre();
        int index1 = -1, index2 = -1;

        if(padre1 != null)
            index1 = padre1.getHijos().indexOf(a1);
        if(padre2 != null)
            index2 = padre2.getHijos().indexOf(a2);

        if(index1 != -1){
            padre1.insert((Arbol) a2.clone(),index1);
        }
        else {
            individuo1.cromosoma[0] = a2.clone();
        }
        if(index2 != -1){
            padre2.insert((Arbol) a1.clone(),index2);
        }
        else {
            individuo2.cromosoma[0] = a1.clone();
        }
    }

    public String toString() {
        return "Intercambio Subarboles";
    }
}
