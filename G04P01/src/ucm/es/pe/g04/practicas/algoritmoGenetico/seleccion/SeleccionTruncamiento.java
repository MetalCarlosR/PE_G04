package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class SeleccionTruncamiento extends Seleccion{

    double _truncamiento;

    public SeleccionTruncamiento(double truncamiento){
        _truncamiento = truncamiento;
    }

    @Override
    public Individuo[] seleccionar(Individuo[] poblacion) {
        int tamPoblacion = poblacion.length;
        Individuo[] selPoblacion = new Individuo[tamPoblacion];
        Arrays.sort(poblacion, Collections.reverseOrder());
        int numSeleccion = (int) (tamPoblacion * _truncamiento);
        int rep = tamPoblacion / numSeleccion;
        int total = numSeleccion * rep;
        int resto = tamPoblacion - total;

        for (int i = 0; i < numSeleccion; i++) {
            for (int j = 0; j < rep; j++) {
                selPoblacion[rep * i + j] = (Individuo) poblacion[i].clone();
            }
        }

        for (int i = 0; i < resto; i++) {
            selPoblacion[total + i] = (Individuo)poblacion[i%numSeleccion].clone();
        }

        return selPoblacion;
    }
}
