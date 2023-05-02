package com.example.math4_web.ApproximationMethods;

import com.example.math4_web.ApproximationMethods.ApproximationMethod;
import com.example.math4_web.Caclulation.GaussMethod;
import com.example.math4_web.Coordinates.Coordinates;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class QuadraticApproximation extends ApproximationMethod {

    public QuadraticApproximation(){
        nameOfMethod = "a2*x^2 + a1*x + a0";
    }
    @Override
    public Map<String, Double> calculate(List<Coordinates> list) {
        double sx = 0;
        double sx2 = 0;
        double sx3 = 0;
        double sx4 = 0;
        double sy = 0;
        double sxy = 0;
        double syx2 = 0;

        for (Coordinates c : list) {
            sx += c.getX();
            sx2 += c.getX() * c.getX();
            sx3 += c.getX() * c.getX() * c.getX();
            sx4 += c.getX() * c.getX() * c.getX() * c.getX();
            sy += c.getY();
            sxy += c.getY() * c.getX();
            syx2 += c.getX() * c.getX() * c.getY();
        }

        int n = 3;
        var A = new double[n][n];
        var B = new double[n];

        A[0][0] = list.size();
        A[0][1] = sx;
        A[0][2] = sx2;
        B[0] = sy;
        A[1][0] = sx;
        A[1][1] = sx2;
        A[1][2] = sx3;
        B[1] = sxy;
        A[2][0] = sx2;
        A[2][1] = sx3;
        A[2][2] = sx4;
        B[2] = syx2;
        double[] a = GaussMethod.solveSystem(n, A, B);
        approximatingFunction = (x) -> a[0] + a[1] * x + a[2] * x * x;
        Map<String, Double> map = new TreeMap<>() {{
            put("a0", a[0]);
            put("a1", a[1]);
            put("a2", a[2]);
        }};
        return map;
    }


}
