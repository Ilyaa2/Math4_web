package com.example.math4_web.Caclulation;

import com.example.math4_web.Coordinates.Coordinates;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

@Setter
@Getter
public class Result{
    private List<Coordinates> coords;
    private List<Double> targets;
    private List<Double> deviations;
    private Map<String, Double> coefficients;
    private double measureOfDeviation;
    private double standardDeviation;
    private UnaryOperator<Double> approximatingFunction;
    private final String name;
    private String coefPirson ="";

    public Result(List<Coordinates> coords, String name, UnaryOperator<Double> approximatingFunction){
        this.coords = coords;
        this.name = name;
        this.targets = new ArrayList<>();
        this.deviations = new ArrayList<>();
        this.approximatingFunction = approximatingFunction;
    }

    public void calculateDeviations(int count){
        for (double dev: deviations){
            measureOfDeviation+= dev * dev;
        }
        standardDeviation = Math.sqrt(measureOfDeviation / count);
    }

    @Override
    public String toString() {
        return "Result{" +
                "measureOfDeviation=" + measureOfDeviation +
                ", coords=" + coords +
                ", coefficients=" + coefficients +
                ", targets=" + targets +
                ", deviations=" + deviations +
                ", name='" + name + '\'' +
                ", standardDeviation=" + standardDeviation +
                '}';
    }
}
