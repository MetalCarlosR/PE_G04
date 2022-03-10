package ucm.es.pe.g04.practicas.gui;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.Cruce;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceMonopunto;
import ucm.es.pe.g04.practicas.algoritmoGenetico.cruces.CruceUniforme;
import ucm.es.pe.g04.practicas.algoritmoGenetico.individuos.*;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.Mutacion;
import ucm.es.pe.g04.practicas.algoritmoGenetico.mutaciones.MutacionBasica;
import ucm.es.pe.g04.practicas.algoritmoGenetico.seleccion.*;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.ChoiceOption;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.ConfigListener;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.DoubleOption;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.InnerOption;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.IntegerOption;
import ucm.es.pe.g04.practicas.gui.ConfigPanel.StrategyOption;

public class PanelPrincipal extends JFrame {

    public PanelPrincipal(){
        super("Practica 1");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel(new GridLayout(3, 2, 4, 4));
        add(panelCentral, BorderLayout.EAST);


        AlgoritmoGenetico genetico = new AlgoritmoGenetico();

        final ConfigPanel<AlgoritmoGenetico> panel = creaPanelConfiguracion();
        panel.setTarget(genetico);
        panel.initialize();
        add(panel, BorderLayout.WEST);

        // crea una etiqueta que dice si todo es valido
        final String textoTodoValido = "Todos los campos OK";
        final String textoHayErrores = "Hay errores en algunos campos";
        final JLabel valido = new JLabel(textoHayErrores);
        // este evento se lanza cada vez que la validez cambia
        panel.addConfigListener(new ConfigListener() {
            @Override
            public void configChanged(boolean isConfigValid) {
                valido.setText(isConfigValid ? textoTodoValido: textoHayErrores);
            }
        });
        add(valido, BorderLayout.SOUTH);

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

    }

    public ConfigPanel<AlgoritmoGenetico> creaPanelConfiguracion() {
        Seleccion[] selecciones = new Seleccion[] {new SeleccionRuleta(), new SeleccionEstocasticaUniversal(), new SeleccionTruncamiento(), new SeleccionTorneoDet(), new SeleccionTorneoProb(), new SeleccionRestos()};
        Mutacion[] mutaciones = new Mutacion[] { new MutacionBasica()};
        Cruce[] cruces = new Cruce[] {new CruceMonopunto(), new CruceUniforme()};
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
                        "valor de error para la discretización del intervalo",           // tooltip
                        "precision",                     // campo
                        0, 1))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
                .addOption(new DoubleOption<AlgoritmoGenetico>(   // -- doble, parecido a entero
                        "Elitismo", 					 // etiqueta
                        "valor de error para la discretización del intervalo",           // tooltip
                        "elitismo",                     // campo
                        0, 1))								 // opcional: factor de multiplicacion != 1.0, para mostrar porcentajes
                .addOption(new StrategyOption<AlgoritmoGenetico>(
                        "Individuo",
                        "tipo de individuo",
                        "original",
                        individuos))
                    .beginInner(new InnerOption<AlgoritmoGenetico, Individuo>(
                            "Función 4",
                            "opciones de la función 4",
                            "original",
                            IndividuoFuncion4.class))
                    .addInner(new IntegerOption<Seleccion>(
                            "n",
                            "número de variables x",
                            "n",
                            0, Integer.MAX_VALUE))
                    .endInner()
                .beginInner(new InnerOption<AlgoritmoGenetico, Individuo>(
                        "Función 4",
                        "opciones de la función 4",
                        "original",
                        IndividuoFuncion4Real.class))
                .addInner(new IntegerOption<Seleccion>(
                        "n",
                        "número de variables x",
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
                        "torneo probabilístico",
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
                                "torneo probabilístico",
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
                .addOption(new StrategyOption<AlgoritmoGenetico>(
                        "Mutacion",
                        "tipo de mutacion",
                        "mutacion",
                        mutaciones))
                // y ahora ya cerramos el formulario
                .endOptions();

        return config;
    }
}
