package com.myproject.ridecabapp.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

// contain some coordinates, type by which we are going to give coordinates is Point.
//It means we are using geospatial point

@Data
@NoArgsConstructor
public class PointDto {

    private double[] coordinates;
    private String type = "Point";

    public PointDto(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
