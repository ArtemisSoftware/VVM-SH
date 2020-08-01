package com.vvm.sh.util.base;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class BaseDatePickerDialog {

    int ano, mes, dia;
    private DatePickerDialog datePickerDialog;

    public BaseDatePickerDialog(DatePickerDialog.OnDateSetListener listener) {

        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR) ;
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        datePickerDialog = DatePickerDialog.newInstance(listener, ano, mes, dia);
    }

    public DatePickerDialog obterDatePickerDialog() {
        return datePickerDialog;
    }
}
