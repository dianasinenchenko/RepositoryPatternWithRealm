package com.devitis.repositorypatternwithrealm.data.utils;

import android.support.annotation.Nullable;

import java.util.Collection;

/**
 * Created by Diana on 03.04.2019.
 */

public class CollectionUtils {

    public static boolean isEmpty(@Nullable Collection collections) {
        return collections == null || collections.isEmpty();
    }
}
