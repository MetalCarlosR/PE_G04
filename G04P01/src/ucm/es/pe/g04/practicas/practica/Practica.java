package ucm.es.pe.g04.practicas.practica;

import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoAviones;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoAvionesData;
import ucm.es.pe.g04.practicas.gui.PanelPrincipal;
import ucm.es.pe.g04.practicas.gui.PanelPrincipalP1;

public class Practica {

    public static void main(String[] args) {
//        PanelPrincipal panel = new PanelPrincipalP1();
//        panel.setSize(600, 600);
//        panel.setVisible(true);
        IndividuoAvionesData.Init();
        IndividuoAviones i = new IndividuoAviones();
        i.init();
        i.getFitness();
    }
}
