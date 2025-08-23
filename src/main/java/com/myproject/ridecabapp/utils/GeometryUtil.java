package com.myproject.ridecabapp.utils;

import com.myproject.ridecabapp.dto.PointDto;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.locationtech.jts.geom.PrecisionModel;

//utility method so that other people can also use this
// converting PointDto to Point
// Geometry Factory is used to tell that this is a point in earth
public class GeometryUtil {

    public static Point createPoint(PointDto pointDto) {
        GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate coordinate = new Coordinate(pointDto.getCoordinates()[0],
                pointDto.getCoordinates()[1]
                );  //convert PointDto to Point
        return geometryFactory.createPoint(coordinate);
    }
}
