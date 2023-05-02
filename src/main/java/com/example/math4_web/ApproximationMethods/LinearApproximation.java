package com.example.math4_web.ApproximationMethods;

import com.example.math4_web.ApproximationMethods.ApproximationMethod;
import com.example.math4_web.Coordinates.Coordinates;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class LinearApproximation extends ApproximationMethod {

    public LinearApproximation(){
        nameOfMethod = "ax + b";
    }
    @Override
    public Map<String, Double> calculate(List<Coordinates> list){
        double sxx = 0;
        double sx = 0;
        double sxy = 0;
        double sy = 0;

        for (Coordinates c: list){
            sxx += c.getX() * c.getX();
            sx += c.getX();
            sxy += c.getY() * c.getX();
            sy += c.getY();
        }

        var a = (sxy * list.size() - sx * sy) / (sxx * list.size() - sx * sx);
        var b = (sxx * sy - sx * sxy) / (sxx * list.size() - sx * sx);

        approximatingFunction = (x) -> a*x + b;
        var map = new TreeMap<String, Double>();
        map.put("a",a);
        map.put("b", b);
        return map;
    }

}
