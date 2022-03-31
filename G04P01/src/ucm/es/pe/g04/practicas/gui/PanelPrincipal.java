package ucm.es.pe.g04.practicas.gui;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PanelPrincipal extends JFrame {
    protected AlgoritmoGenetico genetico;

    public PanelPrincipal(String practica){
        super(practica);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel(new GridLayout(3, 2, 4, 4));
        add(panelCentral, BorderLayout.EAST);


        genetico = new AlgoritmoGenetico();

        final ConfigPanel<AlgoritmoGenetico> panel = creaPanelConfiguracion();
        panel.setTarget(genetico);
        panel.initialize();
        add(panel, BorderLayout.WEST);





    }

    public abstract ConfigPanel<AlgoritmoGenetico> creaPanelConfiguracion();
}
