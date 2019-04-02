package com.devitis.repositorypatternwithrealm.data.sourse;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import io.realm.Realm;
import io.realm.RealmObject;

/**
 * Created by Diana on 02.04.2019.
 */

public abstract class MainDataSource<T extends RealmObject> {

    private Class<T> tClass;

    public MainDataSource(Class<T> tClass) {
        this.tClass = tClass;
    }

    public void createOrUpdate(@NonNull Realm realmInstance, @NonNull final T input) {
        realmInstance.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                long start = System.currentTimeMillis();
                realm.copyToRealmOrUpdate(input);
                Log.d("Realm insertion time", tClass.getSimpleName() + " Insertion time: " + (System.currentTimeMillis() - start) + " ms");
            }

        });
    }

    public void deleteById(@NonNull Realm realmInstance, final int id) {
        if (getPrimaryKey() == null) {
            throw new UnsupportedOperationException("Not supported for repositories with null primary key");
        } else {
            realmInstance.executeTransaction(new Realm.Transaction() {
                @Override
                public void execute(Realm realm) {

                    T object = realm
                            .where(tClass)
                            .equalTo(getPrimaryKey(), id)
                            .findFirst();
                    if (object != null) {
                        object.deleteFromRealm();
                    }
                }
            });
        }
    }

    @Nullable
    protected abstract String getPrimaryKey();
}
