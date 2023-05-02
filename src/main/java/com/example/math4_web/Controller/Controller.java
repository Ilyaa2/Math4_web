package com.example.math4_web.Controller;

import com.example.math4_web.ApproximationMethods.ApproximationMethod;
import com.example.math4_web.Caclulation.ApproximationAnalysis;
import com.example.math4_web.ChartGenerator;
import com.example.math4_web.Coordinates.Coordinates;
import com.example.math4_web.Coordinates.CoordinatesWrapper;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.UnaryOperator;

@org.springframework.stereotype.Controller
@RequestMapping("/")
public class Controller {
    private ChartGenerator chartGenerator;
    private volatile byte[] chartData;

    @Autowired
    public Controller(ChartGenerator chartGenerator) {
        this.chartGenerator = chartGenerator;
    }

    @GetMapping(value = "/Charts.png", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> newCharts() throws IOException {
        return ResponseEntity.ok().body(chartData);
    }

    @GetMapping("/")
    public String selectRows(Model model) {
        return "select";
    }

    @GetMapping("/coordinates")
    public String getCoordinatesForm(@RequestParam(name = "rowCount", defaultValue = "8") int rowCount, Model model) {
        CoordinatesWrapper coordinatesWrapper = new CoordinatesWrapper();
        for (int i = 0; i < rowCount; i++) {
            coordinatesWrapper.getCoordinatesList().add(new Coordinates());
        }
        model.addAttribute("coordinatesWrapper", coordinatesWrapper);
        return "coordinates";
    }

    @SneakyThrows
    @PostMapping("/coordinates")
    public String processCoordinates(@ModelAttribute CoordinatesWrapper coordinatesWrapper, Model model) {
        var listOfCoordinates = coordinatesWrapper.getCoordinatesList();
        List<UnaryOperator<Double>> functions = new ArrayList<>();
        ApproximationAnalysis analysis = new ApproximationAnalysis(listOfCoordinates);
        analysis.analyze();

        for (ApproximationMethod m: analysis.getApproximationMethods()){
            functions.add(m.getFunction());
        }
        chartData = chartGenerator.generateCharts(listOfCoordinates, functions);
        model.addAttribute("results", analysis.getResults());
        model.addAttribute("bestResult", analysis.verdict());
        return "result";
    }

}
