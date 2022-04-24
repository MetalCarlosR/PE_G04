package ucm.es.pe.g04.practicas.practica;

import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceOX;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceOX_OP;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Arbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.Data;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol1;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoAviones;
import ucm.es.pe.g04.practicas.gui.PanelPrincipal;
import ucm.es.pe.g04.practicas.gui.PanelPrincipalP2;

public class Practica {

    public static void main(String[] args) {
        Data.Init();
        Data.instance.init();
        IndividuoArbol1 a = new IndividuoArbol1();
        a.init();
        a.getFitness();
//        PanelPrincipal panel = new PanelPrincipalP2();
//        panel.setSize(1250, 800);
//        panel.setVisible(true);
    }
}
