package com.devitis.repositorypatternwithrealm.ui.adapter;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Pair;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.devitis.repositorypatternwithrealm.R;
import com.devitis.repositorypatternwithrealm.data.model.Marker;
import com.devitis.repositorypatternwithrealm.data.utils.CollectionUtils;
import com.devitis.repositorypatternwithrealm.data.utils.StringUtils;

import java.lang.ref.WeakReference;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;

/**
 * Created by Diana on 03.04.2019.
 */

public class MarkerAdapter extends MainAdapter<Marker, Pair<Marker, Integer>, MarkerAdapter.MarkerViewHolder> {


    public MarkerAdapter(Context context) {

        super(context);
    }


    @Override
    protected int getLayout() {
        return R.layout.item_marker;
    }

    @NonNull
    @Override
    protected MarkerViewHolder getViewHolder(View view) {
        return new MarkerViewHolder(view);
    }

    @Override
    public List<Marker> getItems() {
        return list;
    }

    @Override
    public void onBindViewHolder(MarkerViewHolder holder, int position) {

        holder.marker = list.get(position);

        String name = holder.marker.getName();
        if (!StringUtils.isEmpty(name)) {

            holder.textName.setText(name);
        }

    }

    class MarkerViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.txt_marker_name)
        TextView textName;

        private Marker marker;
        private WeakReference<Observer<Pair<Marker, Integer>>> observerWeakReference;


        MarkerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.cue_card_container)
        void onItemClicked() {
            if (publishSubject != null) {
                publishSubject.onNext(marker);
            }
        }

    }

}
