package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class SeleccionEstocasticaUniversal extends Seleccion{


    @Override
    public Individuo[] seleccionar(Individuo[] poblacion) {
        int tamPoblacion = poblacion.length;
        Individuo sel_super[] = new Individuo[tamPoblacion];//seleccionados para sobrevivir
        double offset = 1d/tamPoblacion;
        double point = r.nextDouble() * offset;
        for(int i = 0; i < tamPoblacion; i++)
        {
            int j = 0;
            double auxPoint = point;
            while ((auxPoint > poblacion[j].puntuacion) && (j  < tamPoblacion)) {auxPoint -= poblacion[j].puntuacion; j++;}
            sel_super[i] = (Individuo) poblacion[j].clone();
            point += offset;
        }
        return sel_super;
    }

    public String toString() {
        return "Seleccion estocastica universal";
    }
}
