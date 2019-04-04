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


    public static final int FIRST_LOCATION = 0;

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

        String dateAdd = holder.marker.getDateAdd();
        if (!StringUtils.isEmpty(dateAdd)) {

            holder.textDateAdd.setText(dateAdd);
        }

//        String dateEnd = holder.marker.getDateEnd();
//        if (!StringUtils.isEmpty(dateEnd)) {
//
//            holder.textDateEnd.setText(dateEnd);
//        }

        if (!CollectionUtils.isEmpty(holder.marker.getLocation())) {

            String location = holder.marker.getLocation().get(FIRST_LOCATION);
            if (!StringUtils.isEmpty(location)) {

                holder.texLocation.setText(location);
            }
        }


    }

    class MarkerViewHolder extends RecyclerView.ViewHolder {


        @BindView(R.id.txt_marker_name)
        TextView textName;
        @BindView(R.id.txt_marker_date_add)
        TextView textDateAdd;
//        @BindView(R.id.txt_marker_date_end)
//        TextView textDateEnd;
        @BindView(R.id.txt_marker_location)
        TextView texLocation;

        private Marker marker;
        private WeakReference<Observer<Pair<Marker, Integer>>> observerWeakReference;


        public MarkerViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @OnClick(R.id.cue_card_container)
        void onItemClicked() {
            if (publishSubject != null) {
                publishSubject.onNext(marker);
            }
        }

        @OnClick(R.id.btn_copy_location)
        void onCopyButtonClick() {
            if (texLocation != null && getContext() != null) {
                String currentLocation = texLocation.getText().toString();
                if (!StringUtils.isEmpty(currentLocation)) {
                    ClipboardManager clipboard = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                    ClipData clip = ClipData.newPlainText("Location", texLocation.getText());
                    clipboard.setPrimaryClip(clip);
                    Toast.makeText(getContext(), "copied " + texLocation.getText(), Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getContext(), "There are not location", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
