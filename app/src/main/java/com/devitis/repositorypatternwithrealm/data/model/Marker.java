package com.devitis.repositorypatternwithrealm.data.model;

import java.util.List;

/**
 * Created by Diana on 02.04.2019.
 */

public class Marker {

    private int id;

    private String name;

    private String dateAdd;

//    private String dateEnd;

    private List<String> location;

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

//    public String getDateEnd() {
//        return dateEnd;
//    }
//
//    public void setDateEnd(String dateEnd) {
//        this.dateEnd = dateEnd;
//    }

    public List<String> getLocation() {
        return location;
    }

    public void setLocation(List<String> location) {
        this.location = location;
    }
}
