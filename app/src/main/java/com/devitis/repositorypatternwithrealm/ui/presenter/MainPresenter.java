package com.devitis.repositorypatternwithrealm.ui.presenter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.devitis.repositorypatternwithrealm.data.model.Marker;
import com.devitis.repositorypatternwithrealm.data.repository.IMainRepository;
import com.devitis.repositorypatternwithrealm.data.repository.MarkerRepository;
import com.devitis.repositorypatternwithrealm.data.utils.CollectionUtils;
import com.devitis.repositorypatternwithrealm.ui.main.MainView;

import java.util.List;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Diana on 03.04.2019.
 */

public class MainPresenter {

    private IMainRepository repository;
    private MainView view;

    public MainPresenter(IMainRepository repository, MainView view) {
        this.repository = repository;
        this.view = view;
    }


    public void init() {
        view.init(new OnItemClick());
    }

    private void goToDetailPresenter(int markerId) {
        view.goToDetailPresenter(markerId);
    }

    public void addMarker() {
        view.goToAddMarkerActivity();
    }

    public void reloadAdapter() {
        if (!CollectionUtils.isEmpty(repository.getMarkers())) {
            view.addAll(repository.getMarkers());
        } else {
            view.removeAll();
        }
    }

    public void searchMarker(@NonNull CharSequence charSequence) {

        List<Marker> markerFromSearch = repository.getMarkerByName(charSequence.toString());
        if (!CollectionUtils.isEmpty(markerFromSearch)) {
            view.addAll(markerFromSearch);
        } else {
            view.removeAll();
        }
    }

    private class OnItemClick extends DisposableObserver<Marker> {

        @Override
        public void onNext(@Nullable Marker marker) {
            if (marker != null) {
                goToDetailPresenter(marker.getId());
            }
        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onComplete() {
        }
    }

}
