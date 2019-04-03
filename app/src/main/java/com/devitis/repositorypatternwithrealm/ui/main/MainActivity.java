package com.devitis.repositorypatternwithrealm.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.devitis.repositorypatternwithrealm.R;
import com.devitis.repositorypatternwithrealm.data.converter.RealmModelMarkerConverter;
import com.devitis.repositorypatternwithrealm.data.realm.RealmMarker;
import com.devitis.repositorypatternwithrealm.data.repository.IMainRepository;
import com.devitis.repositorypatternwithrealm.data.repository.MarkerRepository;
import com.devitis.repositorypatternwithrealm.data.sourse.MarkerDataSource;
import com.devitis.repositorypatternwithrealm.ui.presenter.MainPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private MainPresenter presenter;

    @BindView(R.id.edit_search)
    EditText searchText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        IMainRepository repository = new MarkerRepository(new MarkerDataSource(RealmMarker.class), new RealmModelMarkerConverter());
        presenter = new MainPresenter(repository, new MainView(this));
        presenter.init();
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0) {
                    presenter.reloadAdapter();
                } else {
                    presenter.searchMarker(charSequence);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.reloadAdapter();
    }

    @OnClick(R.id.btn_add_marker)
    void addMarker() {
        presenter.addMarker();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.reloadAdapter();
    }


}
