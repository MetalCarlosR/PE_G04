package ucm.es.pe.g04.practicas.practica;

import ucm.es.pe.g04.practicas.gui.PanelPrincipal;
import ucm.es.pe.g04.practicas.gui.PanelPrincipalArbol;
import ucm.es.pe.g04.practicas.gui.PanelPrincipalGramaticas;
import ucm.es.pe.g04.practicas.gui.PanelPrincipalP1;

public class Practica {

    public static void main(String[] args) {
        //Panel Gramáticas
        //PanelPrincipal panel = new PanelPrincipalGramaticas();
        //Panel Arbol
        PanelPrincipal panel = new PanelPrincipalArbol();
        panel.setSize(1250, 800);
        panel.setVisible(true);
    }
}
