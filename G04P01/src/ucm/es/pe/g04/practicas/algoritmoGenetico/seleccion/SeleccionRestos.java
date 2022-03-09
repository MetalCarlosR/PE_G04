package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Arrays;
import java.util.Comparator;

public class SeleccionRestos extends Seleccion{
    public Seleccion getSeleccionExtra() {
        return seleccionExtra;
    }

    public void setSeleccionExtra(Seleccion seleccionExtra) {
        this.seleccionExtra = seleccionExtra;
    }

    Seleccion seleccionExtra;
    public SeleccionRestos(){
        seleccionExtra = new SeleccionRuleta();
    }
    @Override
    public Individuo[] seleccionar(Individuo[] poblacion) {
        int tamPoblacion = poblacion.length;
        Individuo[] selPoblacion = new Individuo[tamPoblacion];

        int numSel = 0;

        for (int i = 0; i < tamPoblacion; i++) {
            int n = (int) (poblacion[i].puntuacion * tamPoblacion);
            for (int j = 0; j < n; j++) {
                selPoblacion[numSel] = (Individuo) poblacion[i].clone();
                numSel ++;
            }
        }

        int resto = tamPoblacion - numSel;

        if(resto > 0){
            poblacion = seleccionExtra.seleccionar(poblacion);
            Arrays.sort(poblacion, Comparator.reverseOrder());
            for (int i = 0; i < resto; i++) {
                selPoblacion[tamPoblacion - resto + i] = (Individuo) poblacion[i].clone();
            }
        }

        return selPoblacion;
    }

    public String toString() {
        return "Restos";
    }
}
