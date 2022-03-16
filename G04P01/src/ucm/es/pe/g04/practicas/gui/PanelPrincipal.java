package ucm.es.pe.g04.practicas.gui;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PanelPrincipal extends JFrame {

    public PanelPrincipal(String practica){
        super(practica);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel(new GridLayout(3, 2, 4, 4));
        add(panelCentral, BorderLayout.EAST);


        AlgoritmoGenetico genetico = new AlgoritmoGenetico();

        final ConfigPanel<AlgoritmoGenetico> panel = creaPanelConfiguracion();
        panel.setTarget(genetico);
        panel.initialize();
        add(panel, BorderLayout.WEST);


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

    public abstract ConfigPanel<AlgoritmoGenetico> creaPanelConfiguracion();
}
