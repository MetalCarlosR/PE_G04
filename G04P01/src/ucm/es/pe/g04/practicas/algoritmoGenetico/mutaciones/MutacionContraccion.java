package ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Arbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol;

import java.util.Random;

public class MutacionContraccion extends  Mutacion{
    @Override
    public void mutar(Individuo[] poblacion, double probMutacion) {
        if (!(poblacion[0].cromosoma[0] instanceof Arbol)) {
            System.out.println("Cuidado, necesito un Individuo<Arbol> para funcionar");
            return;
        }
        Random rand = new Random();
        for (int i = 0; i < poblacion.length; i++) {
            if(r.nextDouble() < probMutacion)
            {
                IndividuoArbol a = (IndividuoArbol) poblacion[i];
                Arbol arbol = a.getArbol().getRandomFuncion();
                if (arbol == null)
                    continue;
                Arbol padre = arbol.getPadre();
                Arbol terminal = new Arbol();
                int term = rand.nextInt(a.getTerminales().length);
                terminal.setValor(a.getTerminales()[term]);
                terminal.setEsHoja(true);

                if(padre != null)
                    padre.insert(terminal, padre.getHijos().indexOf(arbol));
                else
                    a.cromosoma[0] = terminal;
            }
        }
    }

    @Override
    public String toString() {
        return "Contraccion";
    }
}
