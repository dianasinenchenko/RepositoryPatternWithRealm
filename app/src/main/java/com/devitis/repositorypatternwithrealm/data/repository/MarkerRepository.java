package com.devitis.repositorypatternwithrealm.data.repository;

import android.support.annotation.NonNull;

import com.devitis.repositorypatternwithrealm.data.model.Marker;

import java.util.List;

/**
 * Created by Diana on 02.04.2019.
 */

public class MarkerRepository implements IMainRepository {


    @Override
    public List<Marker> getMarkers() {
        return null;
    }

    @Override
    public Marker getMarkerById(int id) {
        return null;
    }

    @Override
    public List<Marker> getMarkerByName(@NonNull Marker name) {
        return null;
    }

    @Override
    public List<Marker> getMarkerByEndDate(@NonNull Marker endDate) {
        return null;
    }

    @Override
    public List<Marker> getMarkerByLocation(@NonNull Marker location) {
        return null;
    }

    @Override
    public void createMarker(@NonNull Marker marker) {

    }

    @Override
    public void updateMarker(@NonNull Marker marker) {

    }

    @Override
    public void deleteMarkerById(int markerId) {

    }
}
