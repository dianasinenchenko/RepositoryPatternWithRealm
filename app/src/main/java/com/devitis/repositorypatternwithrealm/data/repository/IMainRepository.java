package com.devitis.repositorypatternwithrealm.data.repository;

import android.support.annotation.NonNull;

import com.devitis.repositorypatternwithrealm.data.model.Marker;

import java.util.List;

/**
 * Created by Diana on 02.04.2019.
 */

public interface IMainRepository {

    List<Marker> getMarkers();

    Marker getMarkerById(int id);

    List<Marker> getMarkerByName(@NonNull String name);

    void createMarker(@NonNull Marker marker);

    void updateMarker(@NonNull Marker marker);

    void deleteMarkerById(int markerId);


}
