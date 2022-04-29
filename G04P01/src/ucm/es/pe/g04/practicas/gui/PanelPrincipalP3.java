package ucm.es.pe.g04.practicas.gui;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelPrincipalP3 extends PanelPrincipal{
    public PanelPrincipalP3() {
        super("Practica 3");
    }

    @Override
    public ConfigPanel<AlgoritmoGenetico> creaPanelConfiguracion() {
        genetico.setCruce(new CruceArbol());
        genetico.setSeleccion(new SeleccionRuleta());
        genetico.setMutacion(new MutacionTerminal());
        genetico.setOriginal(new IndividuoArbol1());
        genetico.setMaximizar(true);
        ArbolesData.Init();
        genetico.preEvaluar = ArbolesData::CalcularMedia;

        Seleccion[] selecciones = new Seleccion[] {new SeleccionRuleta(), new SeleccionEstocasticaUniversal(), new SeleccionTruncamiento(), new SeleccionTorneoDet(), new SeleccionTorneoProb(), new SeleccionRestos(), new SeleccionRanking()};

        Mutacion[] mutaciones = new Mutacion[] { new MutacionTerminal(), new MutacionFuncional(), new MutacionPermutacion(), new MutacionHoist(), new MutacionContraccion(), new MutacionSubarbol(), new MutacionExpansion()};
        Cruce[] cruces = new Cruce[] {new CruceArbol()};
        Individuo[] individuos = new Individuo[] {new IndividuoArbol1()};
        String[] tipoCreacion = new String[]{"Completa", "Creciente", "RampedAndHalf"};

        Seleccion[] seleccionesExtra = new Seleccion[] {new SeleccionRuleta(), new SeleccionEstocasticaUniversal(), new SeleccionTruncamiento(), new SeleccionTorneoDet(), new SeleccionTorneoProb()};

        Boolean[] booleans = new Boolean[] {true, false};

        ConfigPanel<AlgoritmoGenetico> config = new ConfigPanel<AlgoritmoGenetico>();

        // se pueden añadir las opciones de forma independiente, o "de seguido"; el resultado es el mismo.
        config.addOption(new ConfigPanel.IntegerOption<AlgoritmoGenetico>(  // -- entero
                        "Tamaño de la poblacion", 					     // texto a usar como etiqueta del campo
                        "numero de individuos",       // texto a usar como 'tooltip' cuando pasas el puntero
                        "tamPoblacion",  						     // campo (espera que haya un getGrosor y un setGrosor)
                        1, 1000))							     // min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)
                .addOption(new ConfigPanel.IntegerOption<AlgoritmoGenetico>(  // -- entero
                        "Numero de generaciones", 					     // texto a usar como etiqueta del campo
                        "numero de generaciones",       // texto a usar como 'tooltip' cuando pasas el puntero
                        "maxGeneraciones",  						     // campo (espera que haya un getGrosor y un setGrosor)
                        1, 1000))							     // min y max (usa Integer.MIN_VALUE /MAX_VALUE para infinitos)
                .addOption(new ConfigPanel.DoubleOption<AlgoritmoGenetico>(   // -- doble, parecido a entero
                        "% cruce", 					 // etiqueta
                        "probabilidad de que cruce de cada individuo",           // tooltip
                        "probCruce",                     // campo
                        0, 100,							     // min y max, aplicando factor, si hay; vale usar Double.*_INFINITY)
                        100))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
                .addOption(new ConfigPanel.DoubleOption<AlgoritmoGenetico>(   // -- doble, parecido a entero
                        "% mutacion", 					 // etiqueta
                        "probabilidad de que haya mutacion",           // tooltip
                        "probMutacion",                     // campo
                        0, 100,							     // min y max, aplicando factor, si hay; vale usar Double.*_INFINITY)
                        100))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
                .addOption(new ConfigPanel.DoubleOption<AlgoritmoGenetico>(   // -- doble, parecido a entero
                        "Elitismo", 					 // etiqueta
                        "valor de error para la discretizacion del intervalo",           // tooltip
                        "elitismo",                     // campo
                        0, 1))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
                .addOption(new ConfigPanel.StrategyOption<AlgoritmoGenetico>(
                        "Individuo",
                        "tipo de individuo",
                        "original",
                        individuos))
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Individuo>(
                        "IndividuoArbol1",
                        "Config Arbol1",
                        "original",
                        IndividuoArbol1.class))
                .addInner(new ConfigPanel.ChoiceOption<Individuo>(
                        "Use IF",
                        "Usar IF",
                        "useIF",
                        booleans))
                .addInner(new ConfigPanel.IntegerOption<Individuo>(
                        "Profundidad",
                        "Profundidad del Arbol",
                        "profundidad",
                        2,10))
                .addInner(new ConfigPanel.ChoiceOption<Individuo>(
                        "TipoCreacion",
                        "Tipo de Creacion del Arbol",
                        "tipoCreacion",
                        tipoCreacion))
                .addInner(new ConfigPanel.DoubleOption<Individuo>(
                        "TarpeianProb",
                        "Probabilidad de eliminar un arbol mas grande que la media",
                        "tarpeianProb",
                        0,1))
                .endInner()
                .addOption(new ConfigPanel.ChoiceOption<AlgoritmoGenetico>(
                        "Maximizar",
                        "busca maximo o minimo",
                        "maximizar",
                        booleans))
                .addOption(new ConfigPanel.StrategyOption<AlgoritmoGenetico>(
                        "Seleccion",
                        "tipo de seleccion",
                        "seleccion",
                        selecciones))
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "torneo determinista",
                        "opciones del torneo",
                        "seleccion",
                        SeleccionTorneoDet.class))
                .addInner(new ConfigPanel.IntegerOption<Seleccion>(
                        "numero de participantes",
                        "cantidad de participantes por torneo",
                        "participantes",
                        0, Integer.MAX_VALUE))
                .endInner()
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "torneo probabilistico",
                        "opciones del torneo",
                        "seleccion",
                        SeleccionTorneoProb.class))
                .addInner(new ConfigPanel.IntegerOption<Seleccion>(
                        "numero de participantes",
                        "cantidad de participantes por torneo",
                        "participantes",
                        0, Integer.MAX_VALUE))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "% victoria minimo del peor",
                        "probablildad minima de que gane el peor",
                        "minThreshold",
                        0, 1.0))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "% victoria maximo del peor",
                        "probablildad maxima de que gane el peor",
                        "maxThreshold",
                        0, 1.0))
                .endInner()
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "truncamiento",
                        "opciones de truncamiento",
                        "seleccion",
                        SeleccionTruncamiento.class))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "porcentaje de truncamiento",
                        "porcentaje de individuos a elegir",
                        "_truncamiento",
                        0, 100,
                        100))
                .endInner()
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "restos",
                        "opciones de restos",
                        "seleccion",
                        SeleccionRestos.class))
                .addInner(new ConfigPanel.StrategyOption<Seleccion>(
                        "Tipo de seleccion adicional",
                        "tipo de seleccion para rellenar los huecos",
                        "seleccionExtra",
                        seleccionesExtra))
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "torneo determinista",
                        "opciones del torneo",
                        "seleccionExtra",
                        SeleccionTorneoDet.class))
                .addInner(new ConfigPanel.IntegerOption<Seleccion>(
                        "numero de participantes",
                        "cantidad de participantes por torneo",
                        "participantes",
                        0, Integer.MAX_VALUE))
                .endInner()
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "truncamiento",
                        "opciones de truncamiento",
                        "seleccionExtra",
                        SeleccionTruncamiento.class))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "porcentaje de truncamiento",
                        "porcentaje de individuos a elegir",
                        "_truncamiento",
                        0, 100,
                        100))
                .endInner()
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "torneo probabilistico",
                        "opciones del torneo",
                        "seleccionExtra",
                        SeleccionTorneoProb.class))
                .addInner(new ConfigPanel.IntegerOption<Seleccion>(
                        "numero de participantes",
                        "cantidad de participantes por torneo",
                        "participantes",
                        0, Integer.MAX_VALUE))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "% victoria minimo del peor",
                        "probablildad minima de que gane el peor",
                        "minThreshold",
                        0, 1.0))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "% victoria maximo del peor",
                        "probablildad maxima de que gane el peor",
                        "maxThreshold",
                        0, 1.0))
                .endInner()
                .endInner()
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "ranking",
                        "opciones de ranking",
                        "seleccion",
                        SeleccionRanking.class))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "beta",
                        "presion selectiva esperada",
                        "_beta",
                        1.0, 2.0))
                .addInner(new ConfigPanel.StrategyOption<Seleccion>(
                        "Tipo de seleccion adicional",
                        "tipo de seleccion para rellenar los huecos",
                        "seleccionExtra",
                        seleccionesExtra))
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "torneo determinista",
                        "opciones del torneo",
                        "seleccionExtra",
                        SeleccionTorneoDet.class))
                .addInner(new ConfigPanel.IntegerOption<Seleccion>(
                        "numero de participantes",
                        "cantidad de participantes por torneo",
                        "participantes",
                        0, Integer.MAX_VALUE))
                .endInner()
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "truncamiento",
                        "opciones de truncamiento",
                        "seleccionExtra",
                        SeleccionTruncamiento.class))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "porcentaje de truncamiento",
                        "porcentaje de individuos a elegir",
                        "_truncamiento",
                        0, 100,
                        100))
                .endInner()
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "torneo probabilistico",
                        "opciones del torneo",
                        "seleccionExtra",
                        SeleccionTorneoProb.class))
                .addInner(new ConfigPanel.IntegerOption<Seleccion>(
                        "numero de participantes",
                        "cantidad de participantes por torneo",
                        "participantes",
                        0, Integer.MAX_VALUE))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "% victoria minimo del peor",
                        "probablildad minima de que gane el peor",
                        "minThreshold",
                        0, 1.0))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "% victoria maximo del peor",
                        "probablildad maxima de que gane el peor",
                        "maxThreshold",
                        0, 1.0))
                .endInner()
                .endInner()
                .addOption(new ConfigPanel.StrategyOption<AlgoritmoGenetico>(
                        "Cruce",
                        "tipo de cruce",
                        "cruce",
                        cruces))
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Seleccion>(
                        "Intercambio Subarboles",
                        "opciones del cruce de arboles",
                        "cruce",
                        CruceArbol.class))
                .addInner(new ConfigPanel.DoubleOption<Seleccion>(
                        "Prob Seleccion interno",
                        "probablildad de selccionar un nodo interno",
                        "probInterno",
                        0, 1.0))
                .endInner()
                .addOption(new ConfigPanel.StrategyOption<AlgoritmoGenetico>(
                        "Mutacion",
                        "tipo de mutacion",
                        "mutacion",
                        mutaciones))
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Mutacion>(
                        "mutacion insercion",
                        "opciones de la mutacion",
                        "mutacion",
                        MutacionInsercion.class))
                .addInner(new ConfigPanel.IntegerOption<Mutacion>(
                        "numero de inserciones",
                        "cantidad de inserciones a realizar",
                        "_inserciones",
                        0,Integer.MAX_VALUE ))
                .endInner()
                .beginInner(new ConfigPanel.InnerOption<AlgoritmoGenetico, Mutacion>(
                        "mutacion heuristica",
                        "opciones de la mutacion",
                        "mutacion",
                        MutacionHeuristica.class))
                .addInner(new ConfigPanel.IntegerOption<Mutacion>(
                        "heuristica",
                        "cantidad de permutaciones",
                        "_heuristica",
                        0, Integer.MAX_VALUE))
                .endInner()
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
//                t.removeAll();
                result.setText(String.valueOf(genetico.getMejorAbsoluto().getFitness()));
//                t.init(mainPanel, ((IndividuoAviones)genetico.getMejorAbsoluto()).getMatriz());
            }
        });
        add(boton, BorderLayout.SOUTH);

        return config;
    }
}
