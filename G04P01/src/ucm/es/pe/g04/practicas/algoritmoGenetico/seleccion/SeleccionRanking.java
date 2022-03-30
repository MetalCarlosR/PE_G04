package ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;

public class SeleccionRanking extends Seleccion{
    public Seleccion getSeleccionExtra() {
        return seleccionExtra;
    }

    public void setSeleccionExtra(Seleccion seleccionExtra) {
        this.seleccionExtra = seleccionExtra;
    }

    Seleccion seleccionExtra;
    static private final double _beta = 1.5;
    public SeleccionRanking(){ seleccionExtra = new SeleccionRuleta();}
    @Override
    public Individuo[] seleccionar(Individuo[] poblacion) {
        rankingPuntuacion(poblacion);
        poblacion = seleccionExtra.seleccionar(poblacion);
        return poblacion;
    }

    private void rankingPuntuacion(Individuo[] poblacion){
        for (int i = 0; i < poblacion.length; i++) {
            double probOfIth = (double)i/(poblacion.length-1);
            probOfIth *= 2*(_beta-1);
            probOfIth = _beta - probOfIth;
            probOfIth /= poblacion.length;
            poblacion[i].puntuacion = probOfIth;
        }
    }
}
