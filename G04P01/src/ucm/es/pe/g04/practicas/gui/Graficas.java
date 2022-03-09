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

    public Graficas() {
        _frame = new JFrame("a plot panel");
        _frame.setSize(600, 600);
        _frame.setVisible(true);
        _frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    }

    public void generarGrafica(double mejorAbsoluto, double mejorGeneracion, double media) {
        _mejorAbsoluto.add(mejorAbsoluto);
        _mejorGeneracion.add(mejorGeneracion);
        _media.add(media);
        _generaciones.add((double) _generacion++);


        Plot2DPanel plot = new Plot2DPanel();
// define the legend position
        plot.addLegend("SOUTH");
// add a line plot to the PlotPanel
        plot.addLinePlot("MEJOR ABSOLUTO", _generaciones.stream().mapToDouble(d -> d).toArray(), _mejorAbsoluto.stream().mapToDouble(d -> d).toArray());
        plot.addLinePlot("MEJOR GENERACIÓN", _generaciones.stream().mapToDouble(d -> d).toArray(), _mejorGeneracion.stream().mapToDouble(d -> d).toArray());
        plot.addLinePlot("MEDIA", _generaciones.stream().mapToDouble(d -> d).toArray(), _media.stream().mapToDouble(d -> d).toArray());
// put the PlotPanel in a JFrame like a JPanel
        _frame.setContentPane(plot);
    }


}
