package com.devitis.repositorypatternwithrealm.data.realm;

import io.realm.RealmObject;

/**
 * Created by Diana on 02.04.2019.
 */

public class RealmString extends RealmObject {

    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
