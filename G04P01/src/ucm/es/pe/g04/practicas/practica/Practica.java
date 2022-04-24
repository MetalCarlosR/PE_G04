package ucm.es.pe.g04.practicas.practica;

import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceArbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.ArbolesData;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol1;

public class Practica {

    public static void main(String[] args) {
        ArbolesData.Init();
        ArbolesData.instance.init();
//        IndividuoArbol1 a = new IndividuoArbol1();
//        a.init();
//        a.getFitness();

        IndividuoArbol1 a1 = new IndividuoArbol1();
        a1.init();
        IndividuoArbol1 a2 = new IndividuoArbol1();
        a2.init();
        CruceArbol cruce = new CruceArbol();
        cruce.cruzar(a1,a2);
//        PanelPrincipal panel = new PanelPrincipalP2();
//        panel.setSize(1250, 800);
//        panel.setVisible(true);
    }
}
