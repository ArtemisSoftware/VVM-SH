package com.vvm.sh.util.base;

import android.content.Context;
import android.widget.TextView;

import androidx.fragment.app.FragmentManager;

import com.wdullaer.materialdatetimepicker.time.TimePickerDialog;

public class BaseTimePickerDialog  {

    private TextView txt;
    private TimePickerDialog.OnTimeSetListener listener;
    TimePickerDialog tdp;

    public BaseTimePickerDialog(TextView txt) {


        listener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePickerDialog view, int hourOfDay, int minute, int second) {
                txt.setText(hourOfDay + ":" + minute);
            }
        };

        tdp = TimePickerDialog.newInstance(listener, true);

        this.txt = txt;
    }

    public void show(FragmentManager getSupportFragmentManager, String timepickerdialog){
        tdp.show(getSupportFragmentManager, "Timepickerdialog");
    }

    //    private static int TIME_PICKER_INTERVAL = 5;
//    private TimePicker timePicker;
//    private final OnTimeSetListener callback;
//
//    public CustomTimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
//        super(context, callBack, hourOfDay, minute / TIME_PICKER_INTERVAL, is24HourView);
//        this.callback = callBack;
//    }
//
//    public CustomTimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView, int intervaloMinutos) {
//        super(context, callBack, hourOfDay, minute / intervaloMinutos, is24HourView);
//        this.callback = callBack;
//        TIME_PICKER_INTERVAL = intervaloMinutos;
//    }
//
//
//    @Override
//    public void onClick(DialogInterface dialog, int which) {
//        if (callback != null && timePicker != null) {
//            timePicker.clearFocus();
//            callback.onTimeSet(timePicker, timePicker.getCurrentHour(), timePicker.getCurrentMinute() * TIME_PICKER_INTERVAL);
//        }
//    }
//
//    @Override
//    protected void onStop() {}
//
//
//
//    @Override
//    public void onAttachedToWindow() {
//        super.onAttachedToWindow();
//        try {
//            Class<?> classForid = Class.forName("com.android.internal.R$id");
//            Field timePickerField = classForid.getField("timePicker");
//            this.timePicker = (TimePicker) findViewById(timePickerField.getInt(null));
//            Field field = classForid.getField("minute");
//
//            NumberPicker mMinuteSpinner = (NumberPicker) timePicker.findViewById(field.getInt(null));
//            mMinuteSpinner.setMinValue(0);
//            mMinuteSpinner.setMaxValue((60 / TIME_PICKER_INTERVAL) - 1);
//            List<String> displayedValues = new ArrayList<String>();
//
//            for (int i = 0; i < 60; i += TIME_PICKER_INTERVAL) {
//                displayedValues.add(String.format("%02d", i));
//            }
//            mMinuteSpinner.setDisplayedValues(displayedValues.toArray(new String[0]));
//
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}