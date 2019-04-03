package com.devitis.repositorypatternwithrealm.ui.dialogfragment;

import android.app.DialogFragment;
import android.support.annotation.NonNull;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.subjects.PublishSubject;

/**
 * Created by Diana on 03.04.2019.
 */

public class BaseDialogFragment<O> extends DialogFragment {

    protected PublishSubject<String> publishSubject = PublishSubject.create();

    public void subscribeToDialogFragment(@NonNull DisposableObserver<String> observer) {
        publishSubject.subscribe(observer);
    }

    public void unSubscribeToDialogFragment() {
        publishSubject.onComplete();
    }
}