package ucm.es.pe.g04.practicas.gui;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.Mutacion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionBasica;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionInsercion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.*;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.ChoiceOption;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.ConfigListener;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.DoubleOption;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.InnerOption;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.IntegerOption;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.StrategyOption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class PanelPrincipalP1 extends PanelPrincipal{

    public PanelPrincipalP1() {
        super("Practica 1");
    }

    @Override
    public ConfigPanel<AlgoritmoGenetico> creaPanelConfiguracion() {
        genetico.setCruce(new CruceMonopunto());
        genetico.setSeleccion(new SeleccionRuleta());
        genetico.setMutacion(new MutacionBasica());
        genetico.setOriginal(new IndividuoFuncion1());


        Seleccion[] selecciones = new Seleccion[] {new SeleccionRuleta(), new SeleccionEstocasticaUniversal(), new SeleccionTruncamiento(), new SeleccionTorneoDet(), new SeleccionTorneoProb(), new SeleccionRestos()};
        Mutacion[] mutaciones = new Mutacion[] { new MutacionBasica()};
        Cruce[] cruces = new Cruce[] {new CruceMonopunto(), new CruceUniforme(), new CruceAritmetico(), new CruceBLXa()};
        Individuo[] individuos = new Individuo[] {new IndividuoFuncion1(), new IndividuoFuncion2(), new IndividuoFuncion3(), new IndividuoFuncion4(), new IndividuoFuncion4Real()};

        Seleccion[] seleccionesExtra = new Seleccion[] {new SeleccionRuleta(), new SeleccionEstocasticaUniversal(), new SeleccionTruncamiento(), new SeleccionTorneoDet(), new SeleccionTorneoProb()};

        Boolean[] maximizar = new Boolean[] {true, false};
        ConfigPanel<AlgoritmoGenetico> config = new ConfigPanel<AlgoritmoGenetico>();

        // se pueden añadir las opciones de forma independiente, o "de seguido"; el resultado es el mismo.
        config.addOption(new IntegerOption<AlgoritmoGenetico>(  // -- entero
                        "Tamaño de la poblacion", 					     // texto a usar como etiqueta del campo
                        "numero de individuos",       // texto a usar como 'tooltip' cuando pasas el puntero
                        "tamPoblacion",  						     // campo (espera que haya un getGrosor y un setGrosor)
                        1, 1000))							     // min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)
                .addOption(new IntegerOption<AlgoritmoGenetico>(  // -- entero
                        "Numero de generaciones", 					     // texto a usar como etiqueta del campo
                        "numero de generaciones",       // texto a usar como 'tooltip' cuando pasas el puntero
                        "maxGeneraciones",  						     // campo (espera que haya un getGrosor y un setGrosor)
                        1, 1000))							     // min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)
                .addOption(new DoubleOption<AlgoritmoGenetico>(   // -- doble, parecido a entero
                        "% cruce", 					 // etiqueta
                        "probabilidad de que cruce de cada individuo",           // tooltip
                        "probCruce",                     // campo
                        0, 100,							     // min y max, aplicando factor, si hay; vale usar Double.*_INFINITY)
                        100))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
                .addOption(new DoubleOption<AlgoritmoGenetico>(   // -- doble, parecido a entero
                        "% mutacion", 					 // etiqueta
                        "probabilidad de que haya mutacion",           // tooltip
                        "probMutacion",                     // campo
                        0, 100,							     // min y max, aplicando factor, si hay; vale usar Double.*_INFINITY)
                        100))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
                .addOption(new DoubleOption<AlgoritmoGenetico>(   // -- doble, parecido a entero
                        "Precision", 					 // etiqueta
                        "valor de error para la discretizacion del intervalo",           // tooltip
                        "precision",                     // campo
                        0, 1))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
                .addOption(new DoubleOption<AlgoritmoGenetico>(   // -- doble, parecido a entero
                        "Elitismo", 					 // etiqueta
                        "valor de error para la discretizacion del intervalo",           // tooltip
                        "elitismo",                     // campo
                        0, 1))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
                .addOption(new StrategyOption<AlgoritmoGenetico>(
                        "Individuo",
                        "tipo de individuo",
                        "original",
                        individuos))
                .beginInner(new InnerOption<AlgoritmoGenetico, Individuo>(
                        "Funcion 4",
                        "opciones de la funcion 4",
                        "original",
                        IndividuoFuncion4.class))
                .addInner(new IntegerOption<Individuo>(
                        "n",
                        "numero de variables x",
                        "n",
                        0, Integer.MAX_VALUE))
                .endInner()
                .beginInner(new InnerOption<AlgoritmoGenetico, Individuo>(
                        "Funcion 4",
                        "opciones de la funcion 4",
                        "original",
                        IndividuoFuncion4Real.class))
                .addInner(new IntegerOption<Individuo>(
                        "n",
                        "numero de variables x",
                        "n",
                        0, Integer.MAX_VALUE))
                .endInner()
                .addOption(new ChoiceOption<AlgoritmoGenetico>(
                        "Maximizar",
                        "busca maximo o minimo",
                        "maximizar",
                        maximizar))
                .addOption(new StrategyOption<AlgoritmoGenetico>(
                        "Seleccion",
                        "tipo de seleccion",
                        "seleccion",
                        selecciones))
                .beginInner(new InnerOption<AlgoritmoGenetico, Seleccion>(
                        "torneo determinista",
                        "opciones del torneo",
                        "seleccion",
                        SeleccionTorneoDet.class))
                .addInner(new IntegerOption<Seleccion>(
                        "numero de participantes",
                        "cantidad de participantes por torneo",
                        "participantes",
                        0, Integer.MAX_VALUE))
                .endInner()
                .beginInner(new InnerOption<AlgoritmoGenetico, Seleccion>(
                        "torneo probabilistico",
                        "opciones del torneo",
                        "seleccion",
                        SeleccionTorneoProb.class))
                .addInner(new IntegerOption<Seleccion>(
                        "numero de participantes",
                        "cantidad de participantes por torneo",
                        "participantes",
                        0, Integer.MAX_VALUE))
                .addInner(new DoubleOption<Seleccion>(
                        "% victoria minimo del peor",
                        "probablildad minima de que gane el peor",
                        "minThreshold",
                        0, 1.0))
                .addInner(new DoubleOption<Seleccion>(
                        "% victoria maximo del peor",
                        "probablildad maxima de que gane el peor",
                        "maxThreshold",
                        0, 1.0))
                .endInner()
                .beginInner(new InnerOption<AlgoritmoGenetico, Seleccion>(
                        "truncamiento",
                        "opciones de truncamiento",
                        "seleccion",
                        SeleccionTruncamiento.class))
                .addInner(new DoubleOption<Seleccion>(
                        "porcentaje de truncamiento",
                        "porcentaje de individuos a elegir",
                        "_truncamiento",
                        0, 100,
                        100))
                .endInner()
                .beginInner(new InnerOption<AlgoritmoGenetico, Seleccion>(
                        "restos",
                        "opciones de restos",
                        "seleccion",
                        SeleccionRestos.class))
                .addInner(new StrategyOption<Seleccion>(
                        "Tipo de seleccion adicional",
                        "tipo de seleccion para rellenar los huecos",
                        "seleccionExtra",
                        seleccionesExtra))
                .beginInner(new InnerOption<AlgoritmoGenetico, Seleccion>(
                        "torneo determinista",
                        "opciones del torneo",
                        "seleccionExtra",
                        SeleccionTorneoDet.class))
                .addInner(new IntegerOption<Seleccion>(
                        "numero de participantes",
                        "cantidad de participantes por torneo",
                        "participantes",
                        0, Integer.MAX_VALUE))
                .endInner()
                .beginInner(new InnerOption<AlgoritmoGenetico, Seleccion>(
                        "truncamiento",
                        "opciones de truncamiento",
                        "seleccionExtra",
                        SeleccionTruncamiento.class))
                .addInner(new DoubleOption<Seleccion>(
                        "porcentaje de truncamiento",
                        "porcentaje de individuos a elegir",
                        "_truncamiento",
                        0, 100,
                        100))
                .endInner()
                .beginInner(new InnerOption<AlgoritmoGenetico, Seleccion>(
                        "torneo probabilistico",
                        "opciones del torneo",
                        "seleccionExtra",
                        SeleccionTorneoProb.class))
                .addInner(new IntegerOption<Seleccion>(
                        "numero de participantes",
                        "cantidad de participantes por torneo",
                        "participantes",
                        0, Integer.MAX_VALUE))
                .addInner(new DoubleOption<Seleccion>(
                        "% victoria minimo del peor",
                        "probablildad minima de que gane el peor",
                        "minThreshold",
                        0, 1.0))
                .addInner(new DoubleOption<Seleccion>(
                        "% victoria maximo del peor",
                        "probablildad maxima de que gane el peor",
                        "maxThreshold",
                        0, 1.0))
                .endInner()
                .endInner()
                .addOption(new StrategyOption<AlgoritmoGenetico>(
                        "Cruce",
                        "tipo de cruce",
                        "cruce",
                        cruces))
                .beginInner(new InnerOption<AlgoritmoGenetico, Cruce>(
                        "Cruce aritmetico",
                        "opciones del cruce aritmetico",
                        "cruce",
                        CruceAritmetico.class))
                .addInner(new DoubleOption<Cruce>(
                        "Alpha",
                        "alpha a usar en el cruce",
                        "alpha",
                        0, 1.0))
                .endInner()
                .addOption(new StrategyOption<AlgoritmoGenetico>(
                        "Mutacion",
                        "tipo de mutacion",
                        "mutacion",
                        mutaciones))
                // y ahora ya cerramos el formulario
                .endOptions();

        // usado por todos los botones
        JButton boton;
        // crea botones para mostrar el estado de las figuras por consola
        boton = new JButton("Ejecutar");
        boton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Lanza el algoritmo genetico");
                genetico.run();
            }
        });
        add(boton, BorderLayout.SOUTH);

        return config;
    }
}
