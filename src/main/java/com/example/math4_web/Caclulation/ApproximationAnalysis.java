package com.example.math4_web.Caclulation;

import com.example.math4_web.ApproximationMethods.*;
import com.example.math4_web.Coordinates.Coordinates;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ApproximationAnalysis {

    private final List<Coordinates> listOfCoordinates;
    private List<ApproximationMethod> approximationMethods;
    private List<Result> results;


    public List<ApproximationMethod> getApproximationMethods() {
        return approximationMethods;
    }

    public ApproximationAnalysis(List<Coordinates> list) {
        //immutable list
        this.listOfCoordinates = Collections.unmodifiableList(list);

        approximationMethods = new ArrayList<>() {{
            add(new LinearApproximation());
            add(new QuadraticApproximation());
            add(new CubicApproximation());
            add(new PowerApproximation());
            add(new ExponentialApproximation());
            add(new LogarithmicApproximation());
        }};
        results = new ArrayList<>();
    }

    public void analyze() {
        for (ApproximationMethod method : approximationMethods) {
            var map = method.calculate(listOfCoordinates);
            Result result = new Result(listOfCoordinates, method.getNameOfMethod(), method.getFunction());
            result.setCoefficients(map);
            if ("ax + b".equals(result.getName())){
                result.setCoefPirson(String.valueOf(calcCoefPirson()));
            }
            for (var c : listOfCoordinates) {
                result.getTargets().add(method.getFunction().apply(c.getX()));
                result.getDeviations().add(method.getFunction().apply(c.getX()) - c.getY());
            }
            result.calculateDeviations(listOfCoordinates.size());
            results.add(result);
        }
    }

    private double calcCoefPirson() {
        double meanX = 0;
        double meanY = 0;
        for (var c : listOfCoordinates) {
            meanX += c.getX();
            meanY += c.getY();
        }
        meanX = meanX / listOfCoordinates.size();
        meanY = meanY / listOfCoordinates.size();

        double first = 0;
        double second = 0;
        double third = 0;

        for (var c : listOfCoordinates) {
            first += (c.getX() - meanX) * (c.getY() - meanY);
            second += (c.getX() - meanX) * (c.getX() - meanX);
            third += (c.getY() - meanY) * (c.getY() - meanY);
        }

        return first / Math.sqrt(second * third);
    }

    public Result verdict() {
        Result bestResult = results.get(0);
        for (Result r : results) {
            if (bestResult.getStandardDeviation() > r.getStandardDeviation()) {
                bestResult = r;
            }
        }
        return bestResult;
    }

    public void printResults() {
        for (Result r : results) {
            System.out.println(r.toString());
        }
    }

    public List<Result> getResults() {
        return results;
    }
}
