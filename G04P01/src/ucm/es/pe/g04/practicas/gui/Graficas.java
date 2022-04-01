package ucm.es.pe.g04.practicas.gui;

import org.math.plot.*;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Graficas {
    List<Double> _mejorAbsoluto = new ArrayList<>();
    List<Double> _mejorGeneracion = new ArrayList<>();
    List<Double> _media = new ArrayList<>();
    List<Double> _generaciones = new ArrayList<>();
    int _generacion = 1;

    JFrame _frame;
    Plot2DPanel plot;

    public Graficas(JFrame frame) {
        _frame = frame;

        plot = new Plot2DPanel();
        plot.addLegend("SOUTH");
        _frame.setContentPane(plot);
    }

    public void siguienteGeneracion(double mejorAbsoluto, double mejorGeneracion, double media){
        _mejorAbsoluto.add(mejorAbsoluto);
        _mejorGeneracion.add(mejorGeneracion);
        _media.add(media);
        _generaciones.add((double) _generacion++);
    }


    public void generarGrafica() {
        plot.removeAllPlots();
// define the legend position
// add a line plot to the PlotPanel
        plot.addLinePlot("MEJOR ABSOLUTO", _generaciones.stream().mapToDouble(d -> d).toArray(), _mejorAbsoluto.stream().mapToDouble(d -> d).toArray());
        plot.addLinePlot("MEJOR GENERACIÓN", _generaciones.stream().mapToDouble(d -> d).toArray(), _mejorGeneracion.stream().mapToDouble(d -> d).toArray());
        plot.addLinePlot("MEDIA", _generaciones.stream().mapToDouble(d -> d).toArray(), _media.stream().mapToDouble(d -> d).toArray());
        _generaciones.clear();
        _mejorAbsoluto.clear();
        _mejorGeneracion.clear();
        _media.clear();
// put the PlotPanel in a JFrame like a JPanel
    }
}
