package com.devitis.repositorypatternwithrealm.ui.presenter;

import java.lang.ref.WeakReference;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

/**
 * Created by Diana on 03.04.2019.
 */

public class DefaultPresenter<T, P> implements Observer<T> {

    private WeakReference<P> weakReference;

    public DefaultPresenter(P weakReference) {
        this.weakReference = new WeakReference<>(weakReference);
    }

    protected P getPresenter() {
        return weakReference.get();
    }


    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
