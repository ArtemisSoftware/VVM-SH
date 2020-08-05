package com.vvm.sh.util.base;

import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

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


    public void fixarLimiteInferior(int tipo, int desvio){

        Calendar dataMinima = Calendar.getInstance();
        dataMinima.set(tipo, calcularDesvio(tipo, desvio));
        datePickerDialog.setMinDate(dataMinima);

        Calendar dataMaxima = Calendar.getInstance();
        datePickerDialog.setMaxDate(dataMaxima);
    }


    public void realcarDias(List<Date> datas){

        Calendar[] high = new Calendar[datas.size()];

        for(int index = 0; index < datas.size(); ++index) {

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(datas.get(index));


            Calendar calendarff = Calendar.getInstance();
            calendarff.set(Calendar.YEAR, calendar.get(Calendar.YEAR));
            calendarff.set(Calendar.MONTH, calendar.get(Calendar.MONTH));
            calendarff.set(Calendar.DAY_OF_WEEK, calendar.get(Calendar.DAY_OF_WEEK));

            high[0] = calendarff;
        }

        datePickerDialog.setHighlightedDays(high);


    }


    private int calcularDesvio(int tipo, int desvio){

        int resultado = 0;

        switch (tipo){

            case Calendar.YEAR:

                resultado = ano + desvio;
                break;

            case Calendar.MONTH:

                resultado = mes + desvio;
                break;

            case Calendar.DATE:

                resultado = dia + desvio;
                break;

            default:
                break;

        }


        return resultado;

    }


}
