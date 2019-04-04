package com.devitis.repositorypatternwithrealm.ui.detail;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devitis.repositorypatternwithrealm.R;
import com.devitis.repositorypatternwithrealm.data.model.Marker;
import com.devitis.repositorypatternwithrealm.data.utils.CollectionUtils;
import com.devitis.repositorypatternwithrealm.data.utils.StringUtils;
import com.devitis.repositorypatternwithrealm.ui.dialogfragment.DatePickerFragment;
import com.devitis.repositorypatternwithrealm.ui.main.ActivityView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Diana on 03.04.2019.
 */

public class DetailView extends ActivityView<DetailActivity> {

    private static final int FIRST_ITEM = 0;
    private static final String DATE_PICKER = "Date";


    @BindView(R.id.edit_marker_name)
    EditText markerName;
    @BindView(R.id.txt_date_add)
    TextView textDateAdd;
//    @BindView(R.id.txt_date_end)
//    TextView textDateEnd;
    @BindView(R.id.edit_marker_location)
    EditText markerLocation;
    @BindView(R.id.btn_edit_and_save)
    Button buttonEditAndSave;
    @BindView(R.id.btn_delete)
    Button buttonDelete;
    @BindView(R.id.btn_change_date)
    Button buttonChangeDate;


    private DatePickerFragment datePickerFragment;


    public DetailView(DetailActivity activity) {
        super(activity);
        ButterKnife.bind(this, activity);
    }

    public void setMarkerData(@NonNull Marker currentMarker) {

        markerName.setText(StringUtils.changeNullByEmptyString(currentMarker.getName()));
        markerLocation.setText(getCollectionFirstItemText(currentMarker.getLocation(), currentMarker));
        textDateAdd.setText(StringUtils.changeNullByEmptyString(currentMarker.getDateAdd()));
//        textDateEnd.setText(StringUtils.changeNullByEmptyString(currentMarker.getDateEnd()));
        setEnabled(false);
    }

    public void setEnabled(boolean enabled) {

        markerName.setEnabled(enabled);
        markerLocation.setEnabled(enabled);
        textDateAdd.setEnabled(enabled);
//        textDateEnd.setEnabled(enabled);
        buttonChangeDate.setEnabled(enabled);
    }

    @Nullable
    public Marker createMarkerFromViews() {
        Marker marker = null;

        String markerNameText = markerName.getText().toString();
//        String markerLocationText = markerLocation.getText().toString();

        if (!StringUtils.isEmpty(markerNameText)

//                &&
//                !StringUtils.isEmpty(markerLocationText)
                ) {

            marker = new Marker();
            marker.setName(markerNameText);
            marker.setDateAdd(textDateAdd.getText().toString());
//            marker.setDateEnd(textDateEnd.getText().toString());

            List<String> location = new ArrayList<>();
            location.add(markerLocation.getText().toString());
            marker.setLocation(location);

        }

        return marker;
    }


    private String getCollectionFirstItemText(List list, Marker currentMarker) {
        return !CollectionUtils.isEmpty(list) ?
                list.get(FIRST_ITEM).toString() :
                StringUtils.NULL_STRING;
    }

    public void finishActivity() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public void setButtonText(@StringRes int resId) {
        buttonEditAndSave.setText(resId);
    }

    public void showMessage(@StringRes int restId) {
        if (getContext() != null) {
            Toast.makeText(getContext(), restId, Toast.LENGTH_LONG).show();
        }
    }

//    public void setDateEnd(@NonNull String dateEnd) {
//
//        textDateEnd.setText(dateEnd);
//    }

    public void setDateAdd(@NonNull String dateAdd) {

        textDateAdd.setText(dateAdd);
    }

    public void showDatePickerFragment(@NonNull DisposableObserver<String> observer) {
        if (getActivity() == null) return;
        DatePickerFragment datePickerFragment = new DatePickerFragment();
        datePickerFragment.subscribeToDialogFragment(observer);
        datePickerFragment.show(getActivity().getFragmentManager(), DATE_PICKER);
    }

    public void unSubscribeListeners() {
        if (datePickerFragment != null) {
            datePickerFragment.unSubscribeToDialogFragment();
            datePickerFragment.dismiss();
        }
    }
}
