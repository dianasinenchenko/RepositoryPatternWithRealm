package com.devitis.repositorypatternwithrealm.ui.add;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.devitis.repositorypatternwithrealm.R;
import com.devitis.repositorypatternwithrealm.data.model.Marker;
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

public class AddMarkerView extends ActivityView<AddMarkerActivity> {

    private static final String DATE_PICKER = "datePicker";

    @BindView(R.id.edit_marker_name)
    EditText markerName;
//    @BindView(R.id.edit_marker_date_add)
//    EditText markerDateAdd;
//    @BindView(R.id.edit_marker_date_end)
//    EditText markerDateEnd;
    @BindView(R.id.edit_marker_location)
    EditText markerLocation;
    @BindView(R.id.btn_edit_and_save)
    Button buttonSave;
    @BindView(R.id.btn_delete)
    Button buttonDelete;
    @BindView(R.id.txt_date_add)
    TextView textDateAdd;
//    @BindView(R.id.txt_date_end)
//    TextView textDateEnd;

    private DatePickerFragment datePickerFragment;

    public AddMarkerView(@NonNull AddMarkerActivity addMarkerActivity) {
        super(addMarkerActivity);
        ButterKnife.bind(this, addMarkerActivity);
    }

    @Nullable
    public Marker createMarkerFromViews() {
        Marker marker = null;

        String markerNameText = markerName.getText().toString();
        String markerLocationText = markerLocation.getText().toString();


        if (!StringUtils.isEmpty(markerNameText) &&
                !StringUtils.isEmpty(markerLocationText)
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

    public void setDateAdd(@NonNull String dateAdd) {
        textDateAdd.setText(dateAdd);
    }

//    public void setDateEnd(@NonNull String dateEnd) {
//        textDateAdd.setText(dateEnd);
//    }


    public void setButtonName() {
        buttonSave.setText(R.string.create_marker);
    }

    public void hideButtonDelete() {
        buttonDelete.setVisibility(View.INVISIBLE);
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


    public void finishActivity() {
        if (getActivity() != null) {
            getActivity().finish();
        }
    }

    public void showToast(@StringRes int resId) {
        Toast.makeText(getContext(), resId, Toast.LENGTH_LONG).show();
    }
}
