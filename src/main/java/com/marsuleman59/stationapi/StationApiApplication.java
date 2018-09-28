package com.marsuleman59.stationapi;

import com.marsuleman59.stationapi.dto.StationDto;
import com.marsuleman59.stationapi.entity.Station;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class StationApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(StationApiApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		final ModelMapper modelMapper = new ModelMapper();
		final PropertyMap<Station, StationDto> personMap = new PropertyMap<Station, StationDto>() {
			protected void configure() {
				map().setStationId(source.getStationId());
				map().setName(source.getName());
				map().setHdEnabled(source.getHdEnabled());
				map().setCallSign(source.getCallSign());
			}
		};
		modelMapper.addMappings(personMap);
		return modelMapper;
	}
}