package com.example.math4_web.ApproximationMethods;

import com.example.math4_web.ApproximationMethods.ApproximationMethod;
import com.example.math4_web.Coordinates.Coordinates;

import java.util.*;


public class ExponentialApproximation extends ApproximationMethod {
    public ExponentialApproximation(){
        nameOfMethod = "ae^(bx)";
    }

    private List<Coordinates> preprocessing(List<Coordinates> list) {
        List<Coordinates> newList = new ArrayList<>(list.size());
        for (Coordinates coordinates : list) {
            Coordinates newCoord = new Coordinates(coordinates.getX(), Math.log(coordinates.getY()));
            newList.add(newCoord);
        }
        return newList;
    }

    @Override
    public Map<String, Double> calculate(List<Coordinates> list) {
        var myList = preprocessing(list);
        double sxx = 0;
        double sx = 0;
        double sxy = 0;
        double sy = 0;

        for (Coordinates c : myList) {
            sxx += c.getX() * c.getX();
            sx += c.getX();
            sxy += c.getY() * c.getX();
            sy += c.getY();
        }

        var a = (sxy * list.size() - sx * sy) / (sxx * list.size() - sx * sx);
        var b = (sxx * sy - sx * sxy) / (sxx * list.size() - sx * sx);

        Map<String, Double> map = new TreeMap<>();
        map.put("a", Math.exp(b));
        map.put("b", a);
        approximatingFunction = (x) -> map.get("a") * Math.exp(map.get("b") * x);
        return map;
    }
}
