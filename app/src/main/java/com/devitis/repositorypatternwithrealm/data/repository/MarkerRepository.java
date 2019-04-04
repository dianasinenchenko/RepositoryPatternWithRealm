package com.devitis.repositorypatternwithrealm.data.repository;

import android.media.MediaActionSound;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.devitis.repositorypatternwithrealm.data.converter.RealmModelMarkerConverter;
import com.devitis.repositorypatternwithrealm.data.model.Marker;
import com.devitis.repositorypatternwithrealm.data.realm.RealmMarker;
import com.devitis.repositorypatternwithrealm.data.sourse.IMarkerDataSource;
import com.devitis.repositorypatternwithrealm.data.sourse.MarkerDataSource;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmModel;
import io.realm.RealmResults;

/**
 * Created by Diana on 02.04.2019.
 */

public class MarkerRepository implements IMainRepository {


    private IMarkerDataSource dataSource;
    private RealmModelMarkerConverter converter;

    public MarkerRepository(@NonNull  final IMarkerDataSource dataSource, @NonNull final RealmModelMarkerConverter converter) {
        this.dataSource = dataSource;
        this.converter = converter;
    }

    @Nullable
    private List<Marker> transformRealResultsToList(@Nullable final RealmResults<RealmMarker> realmMarkerList, @NonNull List<Marker> list) {
        if (realmMarkerList != null) {
            for (RealmMarker realmMarker : realmMarkerList) {
                list.add(converter.realmMarkerToMarker(realmMarker));
            }
        }
        return list;
    }

    @Nullable
    @Override
    public List<Marker> getMarkers() {

        List<Marker> list;
        try (Realm realm = Realm.getDefaultInstance()) {
            list = new ArrayList<>();
            RealmResults<RealmMarker> realmMarkerList = dataSource.getMarkers(realm);
            transformRealResultsToList(realmMarkerList, list);
        }
        return list;
    }

    @Nullable
    @Override
    public Marker getMarkerById(final int id) {

        Marker marker;
        try (Realm realm = Realm.getDefaultInstance()) {
            marker = converter.realmMarkerToMarker(dataSource.getMarkerById(realm, id));
        }
        return marker;
    }

    @Nullable
    @Override
    public List<Marker> getMarkerByName(@NonNull final String name) {

        List<Marker> list;
        try (Realm realm = Realm.getDefaultInstance()) {
            list = new ArrayList<>();
            RealmResults<RealmMarker> realmMarkerListByName = dataSource.getMarkerByName(realm, name);
            transformRealResultsToList(realmMarkerListByName, list);
        }
        return list;
    }


    @Override
    public void createMarker(@NonNull final Marker marker) {

        try (Realm realm = Realm.getDefaultInstance()) {
            dataSource.createMarker(realm, converter.markerToRealmMarker(marker));
        }
    }

    @Override
    public void updateMarker(@NonNull final Marker marker) {

        try (Realm realm = Realm.getDefaultInstance()) {
            dataSource.updateMarker(realm, converter.markerToRealmMarker(marker));
        }
    }

    @Override
    public void deleteMarkerById(int markerId) {

        try (Realm realm = Realm.getDefaultInstance()) {
            dataSource.deleteMarkerById(realm, markerId);
        }
    }
}
