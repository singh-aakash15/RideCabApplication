package com.myproject.ridecabapp.repositories;

import com.myproject.ridecabapp.entities.Driver;
import org.locationtech.jts.geom.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

// ST_Distance(point1, point2)
// ST_DWithin(point1, 10000)
//these are PostGis related methods

// for managing drivers

// ST- special db where we provid coordinates

//there are some special queries for geospatial requirements
//ST_Distance(point1,point2)- calc. distance between two points
//ST-DWithin(point1,10000)- give all points which are within 10kms for ex

@Repository
public interface DriverRepository extends JpaRepository<Driver, Long> {
    @Query(value = "SELECT d.*, ST_Distance(d.current_location, :pickupLocation) AS distance " +
            "FROM driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 10000) " +
            "ORDER BY distance " +
            "LIMIT 10", nativeQuery = true)  //this geodb is smart enough that it has created indexes for us
        // e do not have to go to all data. // it will go to indexed table,find index page and go there direct.
        // log n time for n drivers due to indexing
        // it will only go to those drivers which fulfill thiss criteria based on some geospatial algo
        // it is a post gis feature
    List<Driver> findTenNearestDrivers(Point pickupLocation);

    //Our SQL db has knowledge of these Queries by itself due to PostGis extention
    @Query(value="SELECT d.*, " +
            "FROM driver d " +
            "WHERE d.available = true AND ST_DWithin(d.current_location, :pickupLocation, 15000) " +
            " ORDER BY d.rating DESC " +
            "LIMIT 10", nativeQuery = true )  //only 10 drivers
    List<Driver> findTenNearByTopRatedDrivers(Point pickupLocation);
}
