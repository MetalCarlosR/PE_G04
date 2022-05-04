package ucm.es.pe.g04.practicas.gui;

import org.math.plot.*;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Graficas {
    List<Double> _mejorAbsoluto = new ArrayList<>();
    List<Double> _mejorGeneracion = new ArrayList<>();
    List<Double> _media = new ArrayList<>();
    List<Double> _generaciones = new ArrayList<>();

    List<Double> _extraData = new ArrayList<>();
    int _generacion = 1;

    //JFrame _frame;

    public Plot2DPanel getPlot() {
        return plot;
    }

    Plot2DPanel plot;

    public Graficas(JPanel frame) {
       // _frame = frame;

        plot = new Plot2DPanel();
        plot.addLegend("SOUTH");
        //_frame.setContentPane(plot);
        plot.setPreferredSize(new Dimension(600, 600));
        plot.setFixedBounds(1, 0, 64);

        frame.add(plot, BorderLayout.SOUTH);
    }

    public void siguienteGeneracion(double mejorAbsoluto, double mejorGeneracion, double media, double extraData){
        _mejorAbsoluto.add(mejorAbsoluto);
        _mejorGeneracion.add(mejorGeneracion);
        _media.add(media);
        _generaciones.add((double) _generacion++);
        if(extraData != Double.MIN_VALUE)
            _extraData.add(extraData);
    }


    public void generarGrafica(String extraDataName) {
        plot.removeAllPlots();

// define the legend position
// add a line plot to the PlotPanel
        plot.addLinePlot("MEJOR ABSOLUTO", _generaciones.stream().mapToDouble(d -> d).toArray(), _mejorAbsoluto.stream().mapToDouble(d -> d).toArray());
        plot.addLinePlot("MEJOR GENERACIÓN", _generaciones.stream().mapToDouble(d -> d).toArray(), _mejorGeneracion.stream().mapToDouble(d -> d).toArray());
        plot.addLinePlot("MEDIA", _generaciones.stream().mapToDouble(d -> d).toArray(), _media.stream().mapToDouble(d -> d).toArray());

        if(!_extraData.isEmpty())
            plot.addLinePlot(extraDataName, Color.black ,_generaciones.stream().mapToDouble(d -> d).toArray(), _extraData.stream().mapToDouble(d -> d).toArray());
        _generaciones.clear();
        _mejorAbsoluto.clear();
        _mejorGeneracion.clear();
        _media.clear();
        _extraData.clear();
        _generacion = 0;

// put the PlotPanel in a JFrame like a JPanel
    }
}
