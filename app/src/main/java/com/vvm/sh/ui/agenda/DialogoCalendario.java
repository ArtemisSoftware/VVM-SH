package com.vvm.sh.ui.agenda;

import com.vvm.sh.ui.agenda.modelos.DataAgendamento;
import com.vvm.sh.util.base.BaseDatePickerDialog;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DialogoCalendario extends BaseDatePickerDialog {

    public DialogoCalendario(DatePickerDialog.OnDateSetListener listener, List<DataAgendamento> datas) {
        super(listener);

        List<Date> registosDatas = new ArrayList<>();
        List<Date> registosDatasPendentes = new ArrayList<>();

        for (DataAgendamento item : datas) {
            registosDatas.add(item.data);

            if(item.numeroPendenciasUpload > 0){
                registosDatasPendentes.add(item.data);
            }
        }




        fixarLimites(registosDatas);
        desativarDias(registosDatas);

        datePickerDialog.setHighlightedDays(obterPendencias(registosDatasPendentes));

    }

    private Calendar[] obterPendencias(List<Date> datas) {

        Calendar[] pendencias = new Calendar[datas.size()];

        for (int index = 0; index < datas.size(); ++index) {
            Calendar calendario = Calendar.getInstance();
            calendario.setTime(datas.get(index));

            pendencias [0] = calendario;
        }

        return pendencias;

    }
}
