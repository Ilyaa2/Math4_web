package com.example.math4_web.Coordinates;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Coordinates {
    private double x;
    private double y;

    public Coordinates() {
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Coordinates(double x, double y){
        this.x = x;
        this.y = y;
    }
}
