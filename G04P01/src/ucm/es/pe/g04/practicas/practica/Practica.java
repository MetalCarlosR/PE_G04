package ucm.es.pe.g04.practicas.practica;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceArbol;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.ArbolesData;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.IndividuoArbol1;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionInsercion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionTerminal;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.SeleccionRuleta;

public class Practica {

    public static void main(String[] args) {
        ArbolesData.Init();
        ArbolesData.instance.init();
        AlgoritmoGenetico genetico = new AlgoritmoGenetico();
        genetico.setCruce(new CruceArbol());
        genetico.setSeleccion(new SeleccionRuleta());
        genetico.setMutacion(new MutacionTerminal());
        genetico.setOriginal(new IndividuoArbol1());
        genetico.run();
//        IndividuoArbol1 a = new IndividuoArbol1();
//        a.init();
//        a.getFitness();

//        IndividuoArbol1 a1 = new IndividuoArbol1();
//        a1.init();
//        IndividuoArbol1 a2 = new IndividuoArbol1();
//        a2.init();
//        System.out.println("I1: " + a1.getFitness());
//        System.out.println("I2: " + a2.getFitness());
//        System.out.println("---------------");
//        CruceArbol cruce = new CruceArbol();
//        cruce.cruzar(a1,a2);
//        System.out.println("I1: " + a1.getFitness());
//        System.out.println("I2: " + a2.getFitness());
//        PanelPrincipal panel = new PanelPrincipalP2();
//        panel.setSize(1250, 800);
//        panel.setVisible(true);
    }
}
