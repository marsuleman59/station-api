package com.marsuleman59.stationapi.dto;

import lombok.*;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
public class StationDto {

    private String stationId;

    private String name;

    private Boolean hdEnabled;

    private String callSign;
}
