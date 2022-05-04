package ucm.es.pe.g04.practicas.practica;

import ucm.es.pe.g04.practicas.gui.PanelPrincipal;
import ucm.es.pe.g04.practicas.gui.PanelPrincipalArbol;

public class Practica {

    public static void main(String[] args) {
        PanelPrincipal panel = new PanelPrincipalArbol();
        panel.setSize(1250, 800);
        panel.setVisible(true);
    }
}
