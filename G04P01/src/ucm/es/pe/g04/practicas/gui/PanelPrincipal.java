package ucm.es.pe.g04.practicas.gui;

import ucm.es.pe.g04.practicas.algoritmoGenetico.AlgoritmoGenetico;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class PanelPrincipal extends JFrame {
    protected AlgoritmoGenetico genetico;
    protected ConfigPanel<AlgoritmoGenetico> panel;
    protected Tabla t;
    protected JPanel mainPanel;

    public PanelPrincipal(String practica){
        super(practica);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        JPanel panelCentral = new JPanel(new GridLayout(3, 2, 4, 4));
        add(panelCentral, BorderLayout.EAST);

        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        t = new Tabla();
        Graficas g = new Graficas(mainPanel);
        genetico = AlgoritmoGenetico.Init(g);

        panel = creaPanelConfiguracion();
        panel.setTarget(genetico);
        panel.initialize();
        add(panel, BorderLayout.WEST);
        add(mainPanel, BorderLayout.EAST);
    }

    public abstract ConfigPanel<AlgoritmoGenetico> creaPanelConfiguracion();
}
