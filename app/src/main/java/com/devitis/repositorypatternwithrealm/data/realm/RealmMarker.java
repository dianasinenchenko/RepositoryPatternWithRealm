package com.devitis.repositorypatternwithrealm.data.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.RealmSchema;
import io.realm.annotations.PrimaryKey;

/**
 * Created by Diana on 02.04.2019.
 */

public class RealmMarker extends RealmObject {

    public static final String PK = "id";
    public static final String NAME = "name";


    @PrimaryKey
    private int id;

    private String name;

    private String dateAdd;

    private RealmList<RealmString> locations;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateAdd() {
        return dateAdd;
    }

    public void setDateAdd(String dateAdd) {
        this.dateAdd = dateAdd;
    }

    public RealmList<RealmString> getLocations() {
        return locations;
    }

    public void setLocations(RealmList<RealmString> locations) {
        this.locations = locations;
    }
}
