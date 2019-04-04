package com.devitis.repositorypatternwithrealm.ui.add;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.devitis.repositorypatternwithrealm.R;
import com.devitis.repositorypatternwithrealm.data.converter.RealmModelMarkerConverter;
import com.devitis.repositorypatternwithrealm.data.model.Marker;
import com.devitis.repositorypatternwithrealm.data.realm.RealmMarker;
import com.devitis.repositorypatternwithrealm.data.repository.IMainRepository;
import com.devitis.repositorypatternwithrealm.data.repository.MarkerRepository;
import com.devitis.repositorypatternwithrealm.data.sourse.MarkerDataSource;

import com.devitis.repositorypatternwithrealm.ui.presenter.AddMarkerPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Diana on 03.04.2019.
 */

public class AddMarkerActivity extends AppCompatActivity {

    public static Intent getIntent(final AppCompatActivity activity) {
        return new Intent(activity, AddMarkerActivity.class);
    }

    private AddMarkerPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_marker);
        ButterKnife.bind(this);
        IMainRepository repository = new MarkerRepository(new MarkerDataSource(RealmMarker.class), new RealmModelMarkerConverter());
        presenter = new AddMarkerPresenter(repository, new AddMarkerView(this));
        presenter.init();
    }

    @OnClick(R.id.btn_edit_and_save)
    void saveNewContact() {
        presenter.createNewMarker();
    }

    @OnClick(R.id.btn_change_date)
    void changeDate() {
        presenter.showDatePicker();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.unSubscribeListeners();
    }
}
