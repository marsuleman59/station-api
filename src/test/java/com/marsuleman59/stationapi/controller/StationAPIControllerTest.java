package com.marsuleman59.stationapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.marsuleman59.stationapi.dto.StationDto;
import com.marsuleman59.stationapi.service.StationService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class StationAPIControllerTest {

    private MockMvc mockMvc;

    @Mock
    private StationService stationService;

    @InjectMocks
    private StationAPIController stationAPIController;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(stationAPIController)
                .build();
        JacksonTester.initFields(this, new ObjectMapper());
    }


    StationDto stationDto = StationDto.builder().stationId("s111").name("station1").hdEnabled(true).callSign("test").build();
    @Test
    public void addStation() throws Exception {
        when(stationService.addStation(Mockito.any(StationDto.class))).thenReturn(stationDto);

        String request = "{\"stationId\" : \"s111\",\"name\": \"station1\",\"hdEnabled\": true,\"callSign\": \"test\"}";

        this.mockMvc.perform(post("/station/")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(jsonPath("$", is(notNullValue())))
                .andExpect(jsonPath("$.stationId", is("s111")))
                .andExpect(jsonPath("$.name", is("station1")))
                .andExpect(jsonPath("$.hdEnabled", is(true)))
                .andExpect(jsonPath("$.callSign", is("test")));
    }

    @Test
    public void removeStationByStationId() throws Exception {
        Mockito.doNothing().when(stationService).removeStation("1234");
        this.mockMvc.perform(delete("/station/1234")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    public void getAllStations() throws Exception {
        when(stationService.getAllStations()).thenReturn(Collections.singletonList(stationDto));

        this.mockMvc.perform(get("/station/")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", is(notNullValue())))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].stationId", is("s111")))
                .andExpect(jsonPath("$[0].name", is("station1")))
                .andExpect(jsonPath("$[0].hdEnabled", is(true)))
                .andExpect(jsonPath("$[0].callSign", is("test")));
    }

    @Test
    public void updateStation() throws Exception {
        when(stationService.updateStation(Mockito.anyString(), Mockito.any(StationDto.class))).thenReturn(stationDto);

        String request = "{\"stationId\" : \"s111\",\"name\": \"station1\",\"hdEnabled\": true,\"callSign\": \"test\"}";

        this.mockMvc.perform(put("/station/1234")
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(request))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", is(notNullValue())))
                .andExpect(jsonPath("$.stationId", is("s111")))
                .andExpect(jsonPath("$.name", is("station1")))
                .andExpect(jsonPath("$.hdEnabled", is(true)))
                .andExpect(jsonPath("$.callSign", is("test")));
    }

    @Test
    public void getStationByNameOrID() throws Exception {
        when(stationService.getStationByNameOrID("s111", "station1")).thenReturn(Collections.singletonList(stationDto));

        String request = "{\"stationId\" : \"s111\",\"name\": \"station1\",\"hdEnabled\": true,\"callSign\": \"test\"}";

        this.mockMvc.perform(get("/station/searchByIdOrName?stationId=s111&stationName=station1")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", is(notNullValue())))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].stationId", is("s111")))
                .andExpect(jsonPath("$[0].name", is("station1")))
                .andExpect(jsonPath("$[0].hdEnabled", is(true)))
                .andExpect(jsonPath("$[0].callSign", is("test")));
    }

    @Test
    public void getStationByHdEnabledStation() throws Exception {
        when(stationService.getHDEnabledStations(true)).thenReturn(Collections.singletonList(stationDto));

        String request = "{\"stationId\" : \"s111\",\"name\": \"station1\",\"hdEnabled\": true,\"callSign\": \"test\"}";

        this.mockMvc.perform(get("/station/searchByHdEnabledStation?hdEnabled=true")
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(jsonPath("$", is(notNullValue())))
                .andExpect(jsonPath("$.length()", is(1)))
                .andExpect(jsonPath("$[0].stationId", is("s111")))
                .andExpect(jsonPath("$[0].name", is("station1")))
                .andExpect(jsonPath("$[0].hdEnabled", is(true)))
                .andExpect(jsonPath("$[0].callSign", is("test")));
    }
}