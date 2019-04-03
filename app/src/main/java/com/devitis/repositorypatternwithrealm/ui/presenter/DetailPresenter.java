package com.devitis.repositorypatternwithrealm.ui.presenter;

import android.support.annotation.NonNull;

import com.devitis.repositorypatternwithrealm.R;
import com.devitis.repositorypatternwithrealm.data.model.Marker;
import com.devitis.repositorypatternwithrealm.data.repository.IMainRepository;
import com.devitis.repositorypatternwithrealm.data.utils.StringUtils;
import com.devitis.repositorypatternwithrealm.ui.detail.DetailView;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Diana on 03.04.2019.
 */

public class DetailPresenter {
    private DetailView view;
    private IMainRepository repository;
    private int markerId;
    private Marker currentMarker;
    private boolean isEditable = false;
    private boolean isFirstTime = true;

    public DetailPresenter(IMainRepository repository, DetailView view, int markerId) {
        this.repository = repository;
        this.view = view;
        this.markerId = markerId;
    }

    public void init() {
        currentMarker = repository.getMarkerById(markerId);
        if (currentMarker != null) {
            view.setMarkerData(currentMarker);
        }
    }

    private void decideIfCreateMarker() {
        if (!isFirstTime && !isEditable) {
            currentMarker = view.createMarkerFromViews();
            if (currentMarker != null) {
                addMarkerAndShowSuccess();
            } else {
                view.showMessage(R.string.fields_not_null);
            }

        }
    }

    public void onEditOrSaveClick() {
        isFirstTime = false;
        isEditable = !isEditable;
        view.setEnabled(isEditable);
        view.setButtonText(isEditable ? R.string.save : R.string.edit);
        decideIfCreateMarker();
    }


    private void addMarkerAndShowSuccess() {
        currentMarker.setId(markerId);
        repository.updateMarker(currentMarker);
        view.showMessage(R.string.created_marker_message);
    }

    public void deleteMarker() {
        repository.deleteMarkerById(markerId);
        view.finishActivity();
    }

    public void showDatePicker() {
        view.showDatePickerFragment(new OnDateSelected());
    }


    private void setDataAddText(@NonNull String dateAdd) {

        view.setDateAdd(StringUtils.formatDateWithTodayLogic(dateAdd));
    }

    private void setDataEndText(@NonNull String dateEnd) {

        view.setDateEnd(StringUtils.formatDateWithTodayLogic(dateEnd));
    }


    public void unSubscribeListeners() {
        view.unSubscribeListeners();
    }

    private class OnDateSelected extends DisposableObserver<String> {


        @Override
        public void onNext(String date) {
            if (date != null) {
                setDataAddText(date);
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
