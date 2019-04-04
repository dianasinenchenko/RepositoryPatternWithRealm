package com.devitis.repositorypatternwithrealm.data.sourse;

import android.support.annotation.NonNull;

import com.devitis.repositorypatternwithrealm.data.realm.RealmMarker;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by Diana on 02.04.2019.
 */

public interface IMarkerDataSource {

    RealmResults<RealmMarker> getMarkers(@NonNull Realm realm);

    RealmMarker getMarkerById(@NonNull Realm realm, int id);

    void createMarker(@NonNull Realm realm, @NonNull RealmMarker realmMarker);

    void updateMarker(@NonNull Realm realm, @NonNull RealmMarker realmMarker);

    void deleteMarkerById(@NonNull Realm realm, int markerId);

    RealmResults<RealmMarker> getMarkerByName(@NonNull Realm realm, @NonNull String markerName);

}
