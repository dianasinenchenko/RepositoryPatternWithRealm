package com.devitis.repositorypatternwithrealm.data.converter;

import android.support.annotation.Nullable;

import com.devitis.repositorypatternwithrealm.data.model.Marker;
import com.devitis.repositorypatternwithrealm.data.realm.RealmMarker;
import com.devitis.repositorypatternwithrealm.data.utils.StringUtils;

/**
 * Created by Diana on 03.04.2019.
 */

public class RealmModelMarkerConverter extends MainRealmModelConverter {


    @Nullable
    public Marker realmMarkerToMarker(@Nullable RealmMarker realmMarker) {

        Marker marker = null;
        if (realmMarker != null) {
            marker = new Marker();
            marker.setId(realmMarker.getId());
            marker.setName(StringUtils.changeNullByEmptyString(realmMarker.getName()));
            marker.setDateAdd(StringUtils.changeNullByEmptyString(realmMarker.getDateAdd()));
            marker.setLocation(realmListToList(realmMarker.getLocations()));
        }
        return marker;
    }

    @Nullable
    public RealmMarker markerToRealmMarker(@Nullable Marker marker) {
        RealmMarker realmMarker = null;
        if (marker != null) {
            realmMarker.setId(marker.getId());
            realmMarker.setName(StringUtils.changeNullByEmptyString(marker.getName()));
            realmMarker.setDateAdd(StringUtils.changeNullByEmptyString(marker.getDateAdd()));
            realmMarker.setLocations(listToRealmList(marker.getLocation()));
        }
        return realmMarker;
    }

}
