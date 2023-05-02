package com.example.math4_web.Coordinates;

import java.util.ArrayList;
import java.util.List;

public class CoordinatesWrapper {
    private List<Coordinates> coordinatesList;

    public CoordinatesWrapper() {
        coordinatesList = new ArrayList<>();
    }

    public List<Coordinates> getCoordinatesList() {
        return coordinatesList;
    }

    public void setCoordinatesList(List<Coordinates> coordinatesList) {
        this.coordinatesList = coordinatesList;
    }
}