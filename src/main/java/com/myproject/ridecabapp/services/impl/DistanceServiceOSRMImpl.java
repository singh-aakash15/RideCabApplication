package com.myproject.ridecabapp.services.impl;

import com.myproject.ridecabapp.services.DistanceService;
import lombok.Data;
import org.locationtech.jts.geom.Point;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.List;

@Service
public class DistanceServiceOSRMImpl implements DistanceService {
    @Override
    public double calculateDistance(Point src, Point dest) {
        // call third party api called osrm to fetch distance

        final String OSRM_API_BASE_URL="https://router.project-osrm.org/route/v1/driving/";

        try{
            String uri= src.getX()+"," +src.getY()+"," + dest.getX()+ "," + dest.getY();
            OSRMResponseDto responseDto= RestClient.builder().  //RestClient used to make api calls to 3rd party
                    baseUrl(OSRM_API_BASE_URL)
                    .build()
                    .get()
                    .uri(uri)
                    .retrieve() //to get all data //jackson will convert json data to OSRM data type
                    .body(OSRMResponseDto.class);  //RestClient will get data and map it to custom OSRMResponsedto
            return responseDto.getRoutes().get(0).getDistance()/1000.0; //get all routes-> get first route-> get distance from the first route
        }
        catch(Exception e){
            throw new RuntimeException("Error getting data from OSRM"+e.getMessage());
        }

    }
}

@Data
class OSRMResponseDto{  //customdto in which we can map
    private List<OSRMRoute> routes;
}
@Data
class OSRMRoute{
    private double distance;
}