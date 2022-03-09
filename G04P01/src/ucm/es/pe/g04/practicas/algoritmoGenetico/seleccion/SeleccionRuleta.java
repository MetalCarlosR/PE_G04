package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class SeleccionRuleta extends Seleccion {

    @Override
    public Individuo[] seleccionar(Individuo[] poblacion) {
        int tamPoblacion = poblacion.length;
        Individuo selPoblacion[] = new Individuo[tamPoblacion];//seleccionados para sobrevivir
        double prob; // probabilidad de seleccion
        for(int i = 0; i < tamPoblacion; i++)
        {
            prob = r.nextDouble();
            int j = 0;
            while ((prob > poblacion[j].puntuacion) && (j  < tamPoblacion - 1)) { prob -= poblacion[j].puntuacion; j++;}
            selPoblacion[i] = (Individuo) poblacion[j].clone();
        }
        return selPoblacion;
    }

    public String toString() {
        return "Ruleta";
    }
}
