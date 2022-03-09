package ucm.es.pe.g04.practicas.gui;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


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

        Graficas graficas = new Graficas(this);

        AlgoritmoGenetico genetico = new AlgoritmoGenetico(graficas);

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
        String[] seleccion = new String[] {"Ruleta", "Estocastica_Universal", "Truncamiento", "Torneo", "Torneo_Probabilistico", "Restos"};
        String[] cruce = new String[] {"Monopunto", "Uniforme"};
        String[] mutacion = new String[] {"Basica"};

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
                        "probabilidad de que dos individuos se crucen",           // tooltip
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
                .addOption(new ChoiceOption<AlgoritmoGenetico>(	 // -- eleccion de objeto no-configurable
                        "seleccion",							 // etiqueta
                        "tipo de seleccion", 					 // tooltip
                        "seleccionFact",   							 // campo (debe haber un getColor y un setColor)
                        seleccion))                            // elecciones posibles
                .addOption(new ChoiceOption<AlgoritmoGenetico>(	 // -- eleccion de objeto no-configurable
                        "cruce",							 // etiqueta
                        "tipo de cruce", 					 // tooltip
                        "cruceFact",   							 // campo (debe haber un getColor y un setColor)
                        cruce))                            // elecciones posibles
                .addOption(new ChoiceOption<AlgoritmoGenetico>(	 // -- eleccion de objeto no-configurable
                        "mutacion",							 // etiqueta
                        "tipo de mutacion", 					 // tooltip
                        "mutacionFact",   							 // campo (debe haber un getColor y un setColor)
                        mutacion))                            // elecciones posibles
                // y ahora ya cerramos el formulario
                .endOptions();

        return config;
    }
}
