package com.devitis.repositorypatternwithrealm.ui.presenter;

import android.support.annotation.NonNull;

import com.devitis.repositorypatternwithrealm.R;
import com.devitis.repositorypatternwithrealm.data.model.Marker;
import com.devitis.repositorypatternwithrealm.data.repository.MarkerRepository;
import com.devitis.repositorypatternwithrealm.data.utils.StringUtils;
import com.devitis.repositorypatternwithrealm.ui.add.AddMarkerView;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Diana on 03.04.2019.
 */

public class AddMarkerPresenter {

    private AddMarkerView view;
    private MarkerRepository repository;

    public AddMarkerPresenter(AddMarkerView view, MarkerRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    public void createNewMarker() {
        Marker marker = view.createMarkerFromViews();
        if (marker != null) {
            repository.createMarker(marker);
            view.finishActivity();
        } else {
            view.showToast(R.string.fields_not_null);
        }
    }

    public void init() {
        view.setButtonName();
        view.hideButtonDelete();
    }

    public void showDatePicker() {
        view.showDatePickerFragment(new OnDateSelected());
    }

    private void setDateAdd(@NonNull String data) {
        view.setDateAdd(StringUtils.formatDateWithTodayLogic(data));
    }

    private void setDateEnd(@NonNull String data) {
        view.setDateEnd(StringUtils.formatDateWithTodayLogic(data));
    }

    public void unSubscribeListeners() {
        view.unSubscribeListeners();
    }

    private class OnDateSelected extends DisposableObserver<String> {


        @Override
        public void onNext(String data) {
            if (data != null) {
                setDateAdd(data);
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
