package com.vvm.sh.ui.agenda;

import com.vvm.sh.util.base.BaseDatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Date;
import java.util.List;

public class DialogoCalendario extends BaseDatePickerDialog {

    public DialogoCalendario(DatePickerDialog.OnDateSetListener listener, List<Date> datas) {
        super(listener);

        fixarLimites(datas);
    }
}
