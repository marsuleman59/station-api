package com.marsuleman59.stationapi.service;

import com.marsuleman59.stationapi.dto.StationDto;

import java.util.List;

public interface StationService {

    StationDto addStation(final StationDto stationDto);

    void removeStation(String stationId);

    List<StationDto> getAllStations();

    StationDto updateStation(String id, StationDto stationDto);

    List<StationDto> getStationByNameOrID(final String stationId, final String stationName);

    List<StationDto> getHDEnabledStations(boolean isHdEnabled);
}
