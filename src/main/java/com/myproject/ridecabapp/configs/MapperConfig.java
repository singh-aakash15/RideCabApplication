package com.myproject.ridecabapp.configs;

import com.myproject.ridecabapp.dto.PointDto;
import com.myproject.ridecabapp.utils.GeometryUtil;
import org.locationtech.jts.geom.Point;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// using to convert pointdto to point and vice versa..
@Configuration
public class MapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();  //creating A mapper object

        mapper.typeMap(PointDto.class, Point.class).setConverter(context -> { //converter to convert from one type to another
            PointDto pointDto = context.getSource();
            return GeometryUtil.createPoint(pointDto);
        });

        mapper.typeMap(Point.class, PointDto.class).setConverter(context -> {
            Point point = context.getSource();
            double coordinates[] = {
                    point.getX(),  //longitude
                    point.getY() //latitude
            };
            return new PointDto(coordinates);
        });


        return mapper;
    }
}
