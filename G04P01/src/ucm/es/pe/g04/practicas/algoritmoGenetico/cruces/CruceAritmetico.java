package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class CruceAritmetico extends Cruce<Double>{
    @Override
    protected void cruzar(Individuo<Double> individuo1, Individuo<Double> individuo2) {
        if(!(individuo1.cromosoma[0] instanceof Double) || !(individuo2.cromosoma[0] instanceof Double)){
            System.out.println("Cuidado, llamastes al cruce aritmético con cromosomas no Double");
            return;
        }

        var aux1 = individuo1.cromosoma.clone();
        var aux2 = individuo2.cromosoma.clone();


        for (int i = 0; i < individuo1.cromosoma.length ; i++) {
            if(r.nextDouble() > 0.5){
                double a1 = r.nextDouble();
                double a2 = r.nextDouble();
                individuo1.cromosoma[i] = a1 * aux1[i] + a2 * aux2[i];
                individuo2.cromosoma[i] = a1 * aux2[i] + a2 * aux1[i];
            }
        }

    }
}
