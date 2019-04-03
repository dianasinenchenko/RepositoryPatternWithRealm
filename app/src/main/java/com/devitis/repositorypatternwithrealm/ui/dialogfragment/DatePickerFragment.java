package com.devitis.repositorypatternwithrealm.ui.dialogfragment;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;

import com.devitis.repositorypatternwithrealm.data.utils.StringUtils;

import java.util.Calendar;

/**
 * Created by Diana on 03.04.2019.
 */

public class DatePickerFragment extends BaseDialogFragment<Integer> implements DatePickerDialog.OnDateSetListener {

    private static final int ONE_MONTH_CORRECTION = 1;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        publishSubject.onNext(StringUtils.addZeroToStart(day) + "" + StringUtils.addZeroToStart(month + ONE_MONTH_CORRECTION) + "" + year);
        unSubscribeToDialogFragment();
    }
}
