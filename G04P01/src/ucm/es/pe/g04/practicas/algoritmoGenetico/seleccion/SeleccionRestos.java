package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.Factorias.FactoriaSeleccion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Arrays;
import java.util.Comparator;

public class SeleccionRestos extends Seleccion{
    @Override
    public Individuo[] seleccionar(Individuo[] poblacion) {
        int tamPoblacion = poblacion.length;
        Individuo[] selPoblacion = new Individuo[tamPoblacion];

        int numSel = 0;

        for (int i = 0; i < tamPoblacion; i++) {
            int n = (int) (poblacion[i].puntuacion * tamPoblacion);
            for (int j = 0; j < n; j++) {
                selPoblacion[n + i] = (Individuo) poblacion[i].clone();
            }
            numSel += n;
        }

        int resto = tamPoblacion - numSel;

        if(resto > 0){
            poblacion = FactoriaSeleccion.getAlgoritmoSeleccion("Ruleta",0,0).seleccionar(poblacion);
            Arrays.sort(poblacion, Comparator.reverseOrder());
            for (int i = 0; i < resto; i++) {
                selPoblacion[tamPoblacion - resto + i] = (Individuo) poblacion[i].clone();
            }
        }

        return selPoblacion;
    }
}
