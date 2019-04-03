package com.devitis.repositorypatternwithrealm.data.sourse;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.devitis.repositorypatternwithrealm.data.realm.RealmMarker;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Diana on 03.04.2019.
 */

public class MarkerDataSource extends MainDataSource<RealmMarker> implements IMarkerDataSource {


    private static final int FIRST_ID = 1;


    public MarkerDataSource(@NonNull Class<RealmMarker> realmMarkerClass) {
        super(realmMarkerClass);
    }

    @Nullable
    @Override
    public RealmResults<RealmMarker> getMarkers(@NonNull Realm realm) {
        return realm.where(RealmMarker.class)
                .findAll();
    }


    @Nullable
    @Override
    public RealmMarker getMarkerById(@NonNull Realm realm, int id) {
        return realm.where(RealmMarker.class)
                .equalTo(RealmMarker.PK, id)
                .findFirst();
    }


    private int getId(Realm realm) {
        Number currentIdNum = realm.where(RealmMarker.class).max(getPrimaryKey());
        int nextId;
        if (currentIdNum == null) {
            nextId = FIRST_ID;
        } else {
            nextId = currentIdNum.intValue() + FIRST_ID;
        }
        return nextId;
    }

    @Override
    public void createMarker(@NonNull Realm realm, @NonNull RealmMarker realmMarker) {

        realmMarker.setId(getId(realm));
        createOrUpdate(realm, realmMarker);
    }

    @Override
    public void updateMarker(@NonNull Realm realm, @NonNull RealmMarker realmMarker) {

        createOrUpdate(realm, realmMarker);

    }

    @Override
    public void deleteMarkerById(@NonNull Realm realm, int markerId) {

        deleteById(realm, markerId);
    }

    @Override
    public RealmResults<RealmMarker> getMarkerByName(@NonNull Realm realm, @NonNull String markerName) {
        return realm.where(RealmMarker.class)
                .beginsWith(RealmMarker.NAME, markerName)
                .findAll();
    }

    @Override
    public RealmResults<RealmMarker> getMarkerByEndDate(@NonNull Realm realm, @NonNull String markerEndDate) {
        return null;
    }

    @Override
    public RealmResults<RealmMarker> getMarkerByLocation(@NonNull Realm realm, @NonNull String markerLocation) {
        return null;
    }

    @Nullable
    @Override
    protected String getPrimaryKey() {

        return RealmMarker.PK;
    }
}
