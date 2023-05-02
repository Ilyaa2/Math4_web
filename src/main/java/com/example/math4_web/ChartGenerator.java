package com.example.math4_web;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.function.UnaryOperator;

import javax.imageio.ImageIO;

import com.example.math4_web.Coordinates.Coordinates;
import org.jfree.chart.*;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.ui.RectangleEdge;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;


@Component
@Scope("prototype")
public class ChartGenerator {

    public byte[] generateCharts(List<Coordinates> coords, List<UnaryOperator<Double>> functions) throws IOException{
        XYSeries series1 = new XYSeries("ax + b");
        XYSeries series2 = new XYSeries("a2*x^2 + a1*x + a0");
        XYSeries series3 = new XYSeries("a3*x^3 + a2*x^2 + a1*x + a0");
        XYSeries series4 = new XYSeries("ax^b");
        XYSeries series5 = new XYSeries("ae^(bx)");
        XYSeries series6 = new XYSeries("aln(x) + b");

        double min = coords.get(0).getX();
        double max = coords.get(0).getX();
        for (Coordinates c: coords){
            if (min > c.getX()) min = c.getX();
            if (max < c.getX()) max = c.getX();
        }
        // Здесь добавьте ваши функции
        for (double x = min - 0.5; x <= max + 0.5; x += 0.1) {
            series1.add(x, functions.get(0).apply(x));
            series2.add(x, functions.get(1).apply(x));
            series3.add(x, functions.get(2).apply(x));
            series4.add(x, functions.get(3).apply(x));
            series5.add(x, functions.get(4).apply(x));
            series6.add(x, functions.get(5).apply(x));
        }

        XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(series1);
        dataset.addSeries(series2);
        dataset.addSeries(series3);
        dataset.addSeries(series4);
        dataset.addSeries(series5);
        dataset.addSeries(series6);

        JFreeChart chart = ChartFactory.createXYLineChart(
                "Functions",
                "X-Axis",
                "Y-Axis",
                dataset,
                PlotOrientation.VERTICAL,
                true,
                false,
                false
        );

        chart.getXYPlot().getRenderer().setSeriesPaint(0, Color.RED);
        chart.getXYPlot().getRenderer().setSeriesPaint(1, Color.GREEN);
        chart.getXYPlot().getRenderer().setSeriesPaint(2, Color.BLUE);
        chart.getXYPlot().getRenderer().setSeriesPaint(3, Color.cyan);
        chart.getXYPlot().getRenderer().setSeriesPaint(4, Color.MAGENTA);
        chart.getXYPlot().getRenderer().setSeriesPaint(5, Color.ORANGE);

        ChartRenderingInfo info = new ChartRenderingInfo();
        BufferedImage image = chart.createBufferedImage(1440, 900, info);

        // Добавление точек на график
        Graphics2D g2 = image.createGraphics();
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        Rectangle2D dataArea = info.getPlotInfo().getDataArea();
        //double[] points = {0, 0, 1, 2, -2, -2};
        for (Coordinates c: coords) {
            int x = (int) chart.getXYPlot().getDomainAxis().valueToJava2D(c.getX(), dataArea, RectangleEdge.BOTTOM);
            int y = (int) chart.getXYPlot().getRangeAxis().valueToJava2D(c.getY(), dataArea, RectangleEdge.LEFT);
            g2.fillOval(x - 4, y - 4, 8, 8);
        }
        g2.dispose();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", outputStream);
        return outputStream.toByteArray();
    }
}