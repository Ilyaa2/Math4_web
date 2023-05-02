package com.example.math4_web.ApproximationMethods;

import com.example.math4_web.Coordinates.Coordinates;

import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

public abstract class ApproximationMethod {
    protected UnaryOperator<Double> approximatingFunction;
    protected String nameOfMethod;

    public abstract Map<String, Double> calculate(List<Coordinates> list);

    public UnaryOperator<Double> getFunction(){
        return approximatingFunction;
    }

    public String getNameOfMethod() {
        return nameOfMethod;
    }
}
