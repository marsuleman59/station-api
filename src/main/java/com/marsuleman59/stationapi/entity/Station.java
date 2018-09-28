package com.marsuleman59.stationapi.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode
public class Station implements Serializable {

    @Id
    @Column(unique = true)
    private String stationId;

    private String name;

    private Boolean hdEnabled;

    private String callSign;

}
