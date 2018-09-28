package com.marsuleman59.stationapi.controller;

import com.marsuleman59.stationapi.dto.StationDto;
import com.marsuleman59.stationapi.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.Media;
import javax.validation.Valid;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/station")
public class StationAPIController {

    @Autowired
    private StationService stationService;

    @PostMapping()
    public ResponseEntity<StationDto> addStation(@RequestBody final StationDto stationDto) {

        return new ResponseEntity<StationDto>(stationService.addStation(stationDto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{stationId}")
    public ResponseEntity<StationDto> removeStationByStationId(@PathVariable final String stationId) {
        stationService.removeStation(stationId);
        return new ResponseEntity<StationDto>(HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<StationDto>> getAllStations() {
        return new ResponseEntity<List<StationDto>>(stationService.getAllStations(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StationDto> updateStation(@PathVariable final String id, @RequestBody final StationDto stationDto) {
        return new ResponseEntity<StationDto>(stationService.updateStation(id, stationDto), HttpStatus.OK);
    }

    @GetMapping("/searchByIdOrName")
    public ResponseEntity<List<StationDto>> getStationByNameOrID(@RequestParam(value= "stationId", required = false) final String stationId, @RequestParam(value= "stationName", required = false) final String stationName) {
        return new ResponseEntity<List<StationDto>>(stationService.getStationByNameOrID(stationId, stationName), HttpStatus.OK);
    }

    @GetMapping("/searchByHdEnabledStation")
    public ResponseEntity<List<StationDto>> getStationByHdEnabledStation(@RequestParam("hdEnabled") final Boolean isHdEnabled) {
        return new ResponseEntity<List<StationDto>>(stationService.getHDEnabledStations(isHdEnabled), HttpStatus.OK);
    }
}
