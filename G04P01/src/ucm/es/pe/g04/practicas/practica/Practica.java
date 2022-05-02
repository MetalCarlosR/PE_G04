package ucm.es.pe.g04.practicas.practica;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceArbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.ArbolesData;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Individuo;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol1;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionFuncional;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionInsercion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionPermutacion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionTerminal;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.SeleccionRuleta;
import ucm.es.pe.g04.practicas.gui.PanelPrincipal;
import ucm.es.pe.g04.practicas.gui.PanelPrincipalP3;

public class Practica {

    public static void main(String[] args) {
        PanelPrincipal panel = new PanelPrincipalP3();
        panel.setSize(1250, 800);
        panel.setVisible(true);
//        ArbolesData.Init();
//        IndividuoArbol1 a = new IndividuoArbol1();
//        a.init();
//        double aux = -1;
//        int i = 0;
//        double fintess = 0;
//        while (fintess == 0) {
//            a.init();
//            fintess = a.getFitness();
//        }
//        IndividuoArbol1 a2 = (IndividuoArbol1) a.clone();
//        while (aux != 0){
//            aux = a.getFitness();
//            i++;
//        }
//        a.getFitness();
    }
}
