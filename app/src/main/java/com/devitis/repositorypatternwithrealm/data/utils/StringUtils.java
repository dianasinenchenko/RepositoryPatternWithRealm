package com.devitis.repositorypatternwithrealm.data.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by Diana on 03.04.2019.
 */

public final class StringUtils {

    public static final String NULL_STRING = "";
    public static final int INT_TEN = 10;
    public static final String ZERO_STRING = "0";
    @NonNull
    public static String changeNullToNullString(@Nullable String nulll) {
        return nulll == null ? NULL_STRING : nulll;
    }

    public static boolean isEmpty(@Nullable String text) {
        return text == null || text.isEmpty();
    }


    @NonNull
    public static String addZeroToStart(int number) {
        String formatNumber;
        if (number < INT_TEN) {
            formatNumber = ZERO_STRING + number;
        } else {
            formatNumber = number + NULL_STRING;
        }
        return formatNumber;
    }
}
