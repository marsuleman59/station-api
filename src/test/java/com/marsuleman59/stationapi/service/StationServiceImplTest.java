package com.marsuleman59.stationapi.service;

import com.marsuleman59.stationapi.dto.StationDto;
import com.marsuleman59.stationapi.entity.Station;
import com.marsuleman59.stationapi.exceptions.StationNotFoundException;
import com.marsuleman59.stationapi.repository.StationRepository;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class StationServiceImplTest {

    @Mock
    private StationRepository stationRepository;

    @InjectMocks
    private StationService stationService = new StationServiceImpl();

    @Before
    public void setUp() {

    }

    StationDto stationDto = StationDto.builder().stationId("s111").name("station1").hdEnabled(true).callSign("test").build();
    Station station = Station.builder().stationId("s111").name("station1").hdEnabled(true).callSign("test").build();

    @Test
    public void addStation() {
        Mockito.when(stationRepository.save(Mockito.any(Station.class))).thenReturn(station);
        StationDto response = stationService.addStation(stationDto);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStationId(), is("s111"));
    }

    @Test
    public void removeStation() {
        Mockito.when(stationRepository.findById(Mockito.anyString())).thenReturn(Optional.of(station));
        stationService.removeStation("station 1");
    }
    @Test(expected = StationNotFoundException.class)
    public void removeStationException() {
        Mockito.when(stationRepository.findById(Mockito.anyString())).thenThrow(StationNotFoundException.class);
        stationService.removeStation("station 1");
    }

    @Test
    public void getAllStations() {
        List<Station> stationList = new ArrayList<>();
        stationList.add(station);
        Mockito.when(stationRepository.findAll()).thenReturn(stationList);
        List<StationDto> stations = stationService.getAllStations();
        assertThat(stations,is(notNullValue()));
        assertThat(stations.size(),is(stationList.size()));
    }

    @Test
    public void updateStation() {
        Mockito.when(stationRepository.findById(Mockito.anyString())).thenReturn(Optional.of(station));
        Mockito.when(stationRepository.save(station)).thenReturn(station);
        StationDto response = stationService.updateStation("station 1",stationDto);
        assertThat(response, is(notNullValue()));
        assertThat(response.getStationId(), is("s111"));
    }

    @Test
    public void getStationByNameOrID() {
        List<Station> stationList = new ArrayList<>();
        stationList.add(station);
        Mockito.when(stationRepository.findByStationIdOrNameIgnoreCase("station 1","123")).thenReturn(stationList);
        List<StationDto> response = stationService.getStationByNameOrID("station 1","123");
        assertThat(response,is(notNullValue()));
        assertThat(response.size(),is(stationList.size()));
    }

    @Test
    public void getHDEnabledStations() {
        List<Station> stationList = new ArrayList<>();
        stationList.add(station);
        Mockito.when(stationRepository.findByHdEnabled(true)).thenReturn(stationList);
        List<StationDto> response = stationService.getHDEnabledStations(true);
        assertThat(response,is(notNullValue()));
        assertThat(response.size(),is(stationList.size()));

    }
}