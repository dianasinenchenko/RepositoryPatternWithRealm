package com.devitis.repositorypatternwithrealm.ui.main;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Created by Diana on 03.04.2019.
 */

public class ActivityView<T> {

    private WeakReference<T> activityReference;

    protected ActivityView(T activity) {

        activityReference = new WeakReference<T>(activity);
    }

    @Nullable
    public T getActivity() {
        return activityReference.get();
    }

    @Nullable
    public Context getContext() {
        return (Context) getActivity();
    }

    public Resources getResources() {

        return getContext().getResources();
    }
}
