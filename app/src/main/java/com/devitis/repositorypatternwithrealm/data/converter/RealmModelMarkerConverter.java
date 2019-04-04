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
            marker.setName(StringUtils.changeNullToNullString(realmMarker.getName()));
            marker.setDateAdd(StringUtils.changeNullToNullString(realmMarker.getDateAdd()));
//            marker.setDateEnd(StringUtils.changeNullToNullString(realmMarker.getDateEnd()));
            marker.setLocation(realmListToList(realmMarker.getLocations()));
        }
        return marker;
    }

    @Nullable
    public RealmMarker markerToRealmMarker(@Nullable Marker marker) {
        RealmMarker realmMarker = null;
        if (marker != null) {
            realmMarker.setId(marker.getId());
            realmMarker.setName(StringUtils.changeNullToNullString(marker.getName()));
            realmMarker.setDateAdd(StringUtils.changeNullToNullString(marker.getDateAdd()));
//            realmMarker.setDateEnd(StringUtils.changeNullToNullString(marker.getDateEnd()));
            realmMarker.setLocations(listToRealmList(marker.getLocation()));
        }
        return realmMarker;
    }

}
