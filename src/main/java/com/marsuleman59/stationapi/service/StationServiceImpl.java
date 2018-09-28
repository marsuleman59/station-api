package com.marsuleman59.stationapi.service;

import com.marsuleman59.stationapi.dto.StationDto;
import com.marsuleman59.stationapi.entity.Station;
import com.marsuleman59.stationapi.repository.StationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StationServiceImpl implements StationService {

    @Autowired
    private StationRepository stationRepository;

    @Override
    public StationDto addStation(final StationDto stationDto) {
        Station station = convertToEntity(stationDto);
        return convertToDto(stationRepository.save(station));
    }

    @Override
    public void removeStation(final String stationId) {
        stationRepository.deleteById(stationId);
    }

    @Override
    public List<StationDto> getAllStations() {
        Iterable<Station> stations = stationRepository.findAll();
        final List<StationDto> stationDtoList = new ArrayList<>();
        stations.forEach(station -> stationDtoList.add(convertToDto(station)));
        return stationDtoList;
    }

    @Override
    public StationDto updateStation(String id, StationDto stationDto) {
        Station station = stationRepository.findById(id).get();
        station.setName(stationDto.getName());
        station.setHdEnabled(stationDto.getHdEnabled());
        station.setCallSign(stationDto.getCallSign());
        return convertToDto(stationRepository.save(station));
    }

    @Override
    public List<StationDto> getStationByNameOrID(final String stationId, final String stationName) {
        List<Station> stations = stationRepository.findByStationIdOrNameIgnoreCase(stationId, stationName);
        return getStationDtos(stations);
    }

    @Override
    public List<StationDto> getHDEnabledStations(boolean isHdEnabled) {
        List<Station> stations = stationRepository.findByHdEnabled(isHdEnabled);
        return getStationDtos(stations);
    }

    private List<StationDto> getStationDtos(List<Station> stations) {
        final List<StationDto> stationDtoList = new ArrayList<>();
        stations.forEach(station -> stationDtoList.add(convertToDto(station)));
        return stationDtoList;
    }

    private StationDto convertToDto(final Station station) {
        return StationDto.builder()
                .stationId(station.getStationId())
                .name(station.getName())
                .hdEnabled(station.getHdEnabled())
                .callSign(station.getCallSign())
                .build();
    }

    private Station convertToEntity(final StationDto stationDto) {
        return Station.builder()
                .stationId(stationDto.getStationId())
                .name(stationDto.getName())
                .hdEnabled(stationDto.getHdEnabled())
                .callSign(stationDto.getCallSign())
                .build();
    }
}
