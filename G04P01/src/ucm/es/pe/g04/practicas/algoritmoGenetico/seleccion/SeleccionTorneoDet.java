package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Arrays;
import java.util.Comparator;

public class SeleccionTorneoDet extends Seleccion{

    public int getParticipantes() {
        return participantes;
    }

    public void setParticipantes(int participantes) {
        this.participantes = participantes;
    }

    private int participantes = 2;

    public SeleccionTorneoDet() {}
    public  SeleccionTorneoDet(int participates){
        this.participantes = participates;
    }
    
    @Override
    public Individuo[] seleccionar(Individuo[] poblacion) {

        int tamPoblacion = poblacion.length;
        Individuo[] selPoblacion = new Individuo[tamPoblacion];

        Individuo[] selTorneo = new Individuo[participantes];
        for (int i = 0; i < tamPoblacion; i++) {
            for (int j = 0; j < participantes; j++) {
                int sel = r.nextInt(tamPoblacion);
                selTorneo[j] = poblacion[sel];
            }
            Arrays.sort(selTorneo, Comparator.reverseOrder());

            selPoblacion[i] = (Individuo) selTorneo[0].clone();
        }

        return selPoblacion;
    }

    public String toString() {
        return "Torneo determinista";
    }
}
