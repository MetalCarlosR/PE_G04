package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

import java.util.Arrays;
import java.util.Comparator;

public class SeleccionTorneoProb extends Seleccion{

    public int getParticipantes() {
        return participantes;
    }

    public void setParticipantes(int participantes) {
        this.participantes = participantes;
    }

    private int participantes = 2;

    public double getMaxThreshold() {
        return maxThreshold;
    }

    public void setMaxThreshold(double maxThreshold) {
        this.maxThreshold = maxThreshold;
    }

    public double getMinThreshold() {
        return minThreshold;
    }

    public void setMinThreshold(double minThreshold) {
        this.minThreshold = minThreshold;
    }

    private double maxThreshold = 1.0;
    private double minThreshold = 0.5;

    public  SeleccionTorneoProb() {}
    public  SeleccionTorneoProb(int participates){
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

            double threshold = r.nextDouble()%(maxThreshold - minThreshold) + minThreshold;
            double rand = r.nextDouble();
            selPoblacion[i] = rand > threshold ? (Individuo) selTorneo[0].clone() : (Individuo) selTorneo[participantes - 1].clone();
        }

        return selPoblacion;
    }

    public String toString() {
        return "Torneo probabilistico";
    }
}
