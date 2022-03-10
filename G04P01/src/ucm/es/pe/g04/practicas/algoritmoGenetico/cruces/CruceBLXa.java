package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class CruceBLXa extends Cruce<Double> {
    @Override
    protected void cruzar(Individuo<Double> individuo1, Individuo<Double> individuo2) {
        if (!(individuo1.cromosoma[0] instanceof Double) || !(individuo2.cromosoma[0] instanceof Double)) {
            System.out.println("Cuidado, llamastes al cruce aritmético con cromosomas no Double");
            return;
        }


        for (int i = 0; i < individuo1.cromosoma.length; i++) {
            if (r.nextDouble() > 0.5) {
                double c1 = individuo1.cromosoma[i];
                double c2 = individuo2.cromosoma[i];
                double cMax = Math.max(c1, c2), cMin = Math.min(c1, c2);
                double I = cMax - cMin;
                double a = r.nextDouble();
                double rMin = cMin - I * a;
                double rMax = cMax + I * a;

                individuo1.cromosoma[i] = r.nextDouble(rMin, rMax);
                individuo2.cromosoma[i] = r.nextDouble(rMin, rMax);
            }
        }
    }
}
