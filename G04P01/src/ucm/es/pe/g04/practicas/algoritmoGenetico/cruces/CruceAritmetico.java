package ucm.es.pe.g04.practicas.algoritmoGenetico.cruces;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class CruceAritmetico extends Cruce{

    public double getAlpha() {
        return alpha;
    }

    public void setAlpha(double alpha) {
        this.alpha = alpha;
    }

    private double alpha = 0.6;
    @Override
    protected void cruzar(Individuo individuo1, Individuo individuo2) {
        if(!(individuo1.cromosoma[0] instanceof Double) || !(individuo2.cromosoma[0] instanceof Double)){
            System.out.println("Cuidado, llamastes al cruce aritmético con cromosomas no Double");
            return;
        }

        var aux1 = (Double[])individuo1.cromosoma.clone();
        var aux2 = (Double[])individuo2.cromosoma.clone();


//        for (int i = 0; i < individuo1.cromosoma.length ; i++) {
//            if(r.nextDouble() > 0.5){
//                double a1 = r.nextDouble();
//                double a2 = r.nextDouble();
//                individuo1.cromosoma[i] = a1 * aux1[i] + a2 * aux2[i];
//                individuo2.cromosoma[i] = a1 * aux2[i] + a2 * aux1[i];
//            }
//        }
        for (int i = 0; i < aux1.length; i++) {
            individuo1.cromosoma[i] = alpha * aux1[i] + (1.0 - alpha) * aux2[i];
            individuo2.cromosoma[i] = alpha * aux2[i] + (1.0 - alpha) * aux1[i];
        }

    }

    public String toString() {
        return "Aritmético";
    }
}
