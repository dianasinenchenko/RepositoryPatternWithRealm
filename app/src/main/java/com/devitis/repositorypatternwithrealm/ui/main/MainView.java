package com.devitis.repositorypatternwithrealm.ui.main;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.devitis.repositorypatternwithrealm.R;
import com.devitis.repositorypatternwithrealm.data.model.Marker;
import com.devitis.repositorypatternwithrealm.ui.adapter.MarkerAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Diana on 03.04.2019.
 */

public class MainView extends ActivityView<MainActivity> {

    private MarkerAdapter adapter;

    @BindView(R.id.main_recycler_view)
    RecyclerView recyclerView;

    protected MainView(MainActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void init(@NonNull DisposableObserver<Marker> observer) {
        if (getActivity() != null) {
            adapter = new MarkerAdapter(getContext());
            adapter.subscribeToAdapter(observer);
            recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
            recyclerView.setAdapter(adapter);
        }
    }


    public void unsuscribeToAdapter() {
        adapter.unSubscribeToAdapter();
    }

    public void addMarker(Marker marker) {
        adapter.add(marker);
    }

    public void goToDetailPresenter(int markerId) {
        if (getActivity() != null) {
            getActivity().startActivity(DetailActivity.getIntent(getActivity(), markerId));
        }
    }

    public void goToAddMarkerActivity() {
        if (getActivity() != null) {
            getActivity().startActivity(AddMarkerActivity.getIntent(getActivity()));
        }
    }

    public void addAll(List<Marker> list) {
        adapter.addAll(list);
    }

    public void removeAll() {
        adapter.removeAll();
    }
}