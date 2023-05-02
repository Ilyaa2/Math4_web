package com.example.math4_web;

import com.example.math4_web.Caclulation.ApproximationAnalysis;
import com.example.math4_web.Coordinates.Coordinates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@SpringBootApplication
public class Math4WebApplication{

    public static void main(String[] args) {
        SpringApplication.run(Math4WebApplication.class, args);
        /*
        List<Coordinates> listOfCoordinates = new ArrayList<>(){{
            add(new Coordinates(1.1,2.73));
            add(new Coordinates(2.3,5.12));
            add(new Coordinates(3.7,7.74));
            add(new Coordinates(4.5,8.91));
            add(new Coordinates(5.4,10.59));
            add(new Coordinates(6.8,12.75));
            add(new Coordinates(7.5,13.43));
        }};

        ApproximationAnalysis analysis = new ApproximationAnalysis(listOfCoordinates);
        analysis.analyze();
        analysis.printResults();
        System.out.println(analysis.verdict());
         */
    }


}


