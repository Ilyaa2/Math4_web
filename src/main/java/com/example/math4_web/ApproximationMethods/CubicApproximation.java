package com.example.math4_web.ApproximationMethods;

import com.example.math4_web.ApproximationMethods.ApproximationMethod;
import com.example.math4_web.Caclulation.GaussMethod;
import com.example.math4_web.Coordinates.Coordinates;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CubicApproximation extends ApproximationMethod {
    public CubicApproximation(){
        nameOfMethod = "a3*x^3 + a2*x^2 + a1*x + a0";
    }

    @Override
    public Map<String, Double> calculate(List<Coordinates> list) {
        double[] x = new double[7];
        double[] xy = new double[4];
        x[0] = list.size();
        for (Coordinates c : list) {
            for (int i = 1; i < 7; i++) {
                x[i] += Math.pow(c.getX(), i);
            }
            for (int i = 0; i < 4; i++) {
                xy[i] += c.getY() * Math.pow(c.getX(), i);
            }
        }

        int n = 4;
        var A = new double[n][n];
        var B = new double[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = x[i + j];
            }
            B[i] = xy[i];
        }

        double[] a = GaussMethod.solveSystem(n, A, B);
        approximatingFunction = (X) -> a[0] + a[1] * X + a[2] * X * X + a[3] * X * X * X;
        Map<String, Double> map = new TreeMap<>() {{
            put("a0", a[0]);
            put("a1", a[1]);
            put("a2", a[2]);
            put("a3", a[3]);
        }};
        return map;
    }
}
