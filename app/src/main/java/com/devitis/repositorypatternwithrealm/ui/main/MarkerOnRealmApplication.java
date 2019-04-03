package com.devitis.repositorypatternwithrealm.ui.main;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by Diana on 03.04.2019.
 */

public class MarkerOnRealmApplication extends Application {

    private static final int REALM_SCHEMA_VERSION = 1;
    private static MarkerOnRealmApplication instance;

    @NonNull
    public static MarkerOnRealmApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        Realm.init(instance);
        setRealmConfiguration();
    }

    private void setRealmConfiguration() {
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .schemaVersion(REALM_SCHEMA_VERSION)
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

}
