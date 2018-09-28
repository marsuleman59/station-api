package com.marsuleman59.stationapi.repository;

import com.marsuleman59.stationapi.entity.Station;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StationRepository extends CrudRepository<Station, String> {

    List<Station> findByStationIdOrNameIgnoreCase(final String stationId, final String stationName);

    List<Station> findByHdEnabled(final boolean isHdEnabled);
}
