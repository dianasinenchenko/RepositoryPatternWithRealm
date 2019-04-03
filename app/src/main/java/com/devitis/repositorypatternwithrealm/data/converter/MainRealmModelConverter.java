package com.devitis.repositorypatternwithrealm.data.converter;

import android.support.annotation.Nullable;

import com.devitis.repositorypatternwithrealm.data.realm.RealmString;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;

/**
 * Created by Diana on 03.04.2019.
 */

class MainRealmModelConverter {

    @Nullable
    List<String> realmListToList(@Nullable RealmList<RealmString> realmList) {

        List<String> list = null;
        if (realmList != null) {

            list = new ArrayList<>();
            for (int i = 0; i < realmList.size(); i++) {
                String string = (realmList.get(i).getText());
                list.add(string);
            }
        }
        return list;
    }

    @Nullable
    RealmList<RealmString> listToRealmList(@Nullable List<String> list) {
        RealmList<RealmString> realmList = null;
        if (list != null) {
            realmList = new RealmList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) != null) {
                    RealmString realmString = new RealmString();
                    realmString.setText(list.get(i));
                    list.get(i);
                    realmList.add(realmString);
                }
            }
        }
        return realmList;
    }


}
