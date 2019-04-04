package com.devitis.repositorypatternwithrealm.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.devitis.repositorypatternwithrealm.R;
import com.devitis.repositorypatternwithrealm.data.converter.RealmModelMarkerConverter;
import com.devitis.repositorypatternwithrealm.data.realm.RealmMarker;
import com.devitis.repositorypatternwithrealm.data.repository.IMainRepository;
import com.devitis.repositorypatternwithrealm.data.repository.MarkerRepository;
import com.devitis.repositorypatternwithrealm.data.sourse.MarkerDataSource;
import com.devitis.repositorypatternwithrealm.ui.presenter.DetailPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Diana on 03.04.2019.
 */

public class DetailActivity extends AppCompatActivity {


    public static final String EXTRA_MARKER_ID = "EXTRA_MARKER_ID";
    private DetailPresenter presenter;



    public static Intent getIntent(final AppCompatActivity activity, int markerId) {
        Intent intent = new Intent(activity, DetailActivity.class);
        intent.putExtra(EXTRA_MARKER_ID, markerId);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Intent intent = getIntent();
        if (intent == null || !intent.hasExtra(EXTRA_MARKER_ID)) {
            return;
        }
        IMainRepository repository = new MarkerRepository(new MarkerDataSource(RealmMarker.class), new RealmModelMarkerConverter());
        presenter = new DetailPresenter(repository, new DetailView(this), intent.getIntExtra(EXTRA_MARKER_ID, -1));
        presenter.init();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @OnClick(R.id.btn_delete)
    void deleteMarker() {
        presenter.deleteMarker();
    }

    @OnClick(R.id.btn_edit_and_save)
    void onEditOrSaveClick() {
        presenter.onEditOrSaveClick();
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
