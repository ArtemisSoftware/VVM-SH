package com.vvm.sh.util.metodos;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Datas {

    public static final String DATA_FORMATO_DD_MM_YYYY = "dd-MM-yyyy";
    public static final String DATA_FORMATO_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATA_FORMATO_YYYY_MM = "yyyy-MM";
    public static final String DATA_FORMATO_YYYY_MM_DD__HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String DATA_FORMATO_DD_MM_YYYY__HH_MM = "dd-MM-yyyy HH:mm";
    public static final String DATA_FORMATO_DD_MM_YYYY__HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
    public static final String DATA_FORMATO_DD_MM_YYYY__HH_MM_SS_V2 = "dd/MM/yyyy HH:mm:ss";

    public static final String DATA_FORMATO_MMMM_YYYY = "MMMM yyyy";
    public static final String HORA_FORMATO_HH_MM = "HH:mm";
    public static final String HORA_FORMATO_HH_MM_SS = "HH:mm:ss";


    /**
     * Metodo que converte uma data no formato string para o formato date
     * @param data data a converter (YYYY-mm-dd)
     * @param formatoData o formato da data (ex:dd/MM/yyyy)
     * @return uma data
     */
    public static String converterData(String data, String formatoData){

        SimpleDateFormat formatter = new SimpleDateFormat(DATA_FORMATO_YYYY_MM_DD);
        Date novaData = null;

        try {

            novaData = formatter.parse(data);

            DateFormat df = new SimpleDateFormat(formatoData);
            data = df.format(novaData);
        }
        catch (ParseException e) {
            e.printStackTrace();
        }

        return data;
    }




    /**
     * Metodo qua permite escolher datas atrav?s de uma caixa de di?logo
     * @param view
     * @param txtData objecto onde escrever a data
     *//*
    public static void escolherData(View view, TextView txtData){

        int ano, mes, dia;
        DatePickerDialog dialog = null;

        PickDate pickerData = new PickDate();
        pickerData.fixarCaixa(txtData);

        if(txtData.getText().toString() != null && txtData.getText().toString().equals(AppIF.SEM_TEXTO) == false){

            StringTokenizer st = new StringTokenizer(txtData.getText().toString(),"-");
            dia = Integer.parseInt(st.nextToken());
            mes = Integer.parseInt(st.nextToken()) - 1;
            ano = Integer.parseInt(st.nextToken());
        }
        else { //N?o existe data escolhida

            Date dataHoje = MetodosDatas.obterDataActual();
            Calendar cal = Calendar.getInstance();
            cal.setTime(dataHoje);
            ano = cal.get(Calendar.YEAR);
            mes = cal.get(Calendar.MONTH);
            dia = cal.get(Calendar.DAY_OF_MONTH);
        }

        dialog = new DatePickerDialog(view.getContext(),pickerData,ano,mes,dia);
        dialog.show();
    }
*/



    //-----------------------------------
    //Classes
    //-----------------------------------
/*
    private static class PickDate implements DatePickerDialog.OnDateSetListener {

        TextView txt;
        boolean mostrarDia = true;

        public void fixarCaixa (TextView txtActual){

            txt = txtActual;
        }*/
		/*----
		public void fixarCaixa (TextView txtActual, boolean verDia){

			txt = txtActual;
			mostrarDia = verDia;
		}
		*/
/*
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
            view.updateDate(year, monthOfYear, dayOfMonth);
            String dia = (dayOfMonth + "");

            if(dia.length() == 1)
                dia = "0" + dia;


            if(mostrarDia == true){

                if(((monthOfYear + 1) + "").length() == 2)
                    txt.setText(dia + "-" + (monthOfYear + 1) + "-" + year);

                else txt.setText(dia + "-0" + (monthOfYear + 1) + "-" + year);
            }

            else{

                if(((monthOfYear + 1) + "").length() == 2)
                    txt.setText((monthOfYear + 1) + "-" + year);

                else txt.setText("0" + (monthOfYear + 1) + "-" + year);
            }

        }
    }

*/



    /**
     * Metodo qua permite escolher horas através de uma caixa de diálogo.<br> Minutos separados por intervalos de 5 minutos
     * @param view
     * @param txtHora objecto onde escrever a hora
     * @param intervaloMinutos o intervalo entre os minutos
     */
    /*
    public static void escolherHora(View view, TextView txtHora, int intervaloMinutos){

        int horaActual, minutosActuais;
        MetodosDatas.CustomTimePickerDialog  dialogo = null;
        MetodosDatas.PickHora pickerHora = new MetodosDatas.PickHora();
        pickerHora.fixarCaixa(txtHora);


        if(txtHora.getText().toString() != null && txtHora.getText().toString().equals(AppIF.SEM_TEXTO) == false){

            int hora, minutos;
            hora = Integer.parseInt(txtHora.getText().toString().split(":")[0]);
            minutos = Integer.parseInt(txtHora.getText().toString().split(":")[1]);

            dialogo = new MetodosDatas.CustomTimePickerDialog(view.getContext(),pickerHora,hora,minutos, true, intervaloMinutos);

        }
        else{

            final Calendar c = Calendar.getInstance();
            horaActual = c.get(Calendar.HOUR_OF_DAY);
            minutosActuais = c.get(Calendar.MINUTE);

            dialogo = new MetodosDatas.CustomTimePickerDialog(view.getContext(),pickerHora,horaActual, minutosActuais, true, intervaloMinutos);
        }


        dialogo.show();
    }





    public static class PickHora implements TimePickerDialog.OnTimeSetListener {

        TextView txtHoraInicio, txtHoraFim;

        public void fixarCaixa (TextView txtActual){

            txtHoraInicio = txtActual;
        }


        public void fixarCaixa (TextView txtInicio, TextView txtFim){

            txtHoraInicio = txtInicio;
            txtHoraFim = txtFim;
        }


        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {


            String hora = (hourOfDay + ""), minutos = (minute + "");

            if(hora.length() == 1)
                hora = "0" + hora;

            if(minutos.length() == 1)
                minutos = "0" + minutos;


            txtHoraInicio.setText(hora + ":" + minutos);

            try{

                SimpleDateFormat df = new SimpleDateFormat(DataIF.HORA_FORMATO_HH_MM);
                Calendar cal = Calendar.getInstance();


                String horaInicio = txtHoraInicio.getText().toString();
                Date dataActual = df.parse(horaInicio);
                cal.setTime(dataActual);
                //cal.add(Calendar.MINUTE, AppConfigIF.DURACAO_POR_OMISSAO_EVENTO);
                String novaHora = df.format(cal.getTime());

                txtHoraFim.setText(novaHora);

            }
            catch(NullPointerException e){
                e.printStackTrace();
            }
            catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }






    public static class CustomTimePickerDialog extends TimePickerDialog {

        private static int TIME_PICKER_INTERVAL = 5;
        private TimePicker timePicker;
        private final OnTimeSetListener callback;

        public CustomTimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView) {
            super(context, callBack, hourOfDay, minute / TIME_PICKER_INTERVAL, is24HourView);
            this.callback = callBack;
        }

        public CustomTimePickerDialog(Context context, OnTimeSetListener callBack, int hourOfDay, int minute, boolean is24HourView, int intervaloMinutos) {
            super(context, callBack, hourOfDay, minute / intervaloMinutos, is24HourView);
            this.callback = callBack;
            TIME_PICKER_INTERVAL = intervaloMinutos;
        }


        @Override
        public void onClick(DialogInterface dialog, int which) {
            if (callback != null && timePicker != null) {
                timePicker.clearFocus();
                callback.onTimeSet(timePicker, timePicker.getCurrentHour(), timePicker.getCurrentMinute() * TIME_PICKER_INTERVAL);
            }
        }

        @Override
        protected void onStop() {}



        @Override
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            try {
                Class<?> classForid = Class.forName("com.android.internal.R$id");
                Field timePickerField = classForid.getField("timePicker");
                this.timePicker = (TimePicker) findViewById(timePickerField.getInt(null));
                Field field = classForid.getField("minute");

                NumberPicker mMinuteSpinner = (NumberPicker) timePicker.findViewById(field.getInt(null));
                mMinuteSpinner.setMinValue(0);
                mMinuteSpinner.setMaxValue((60 / TIME_PICKER_INTERVAL) - 1);
                List<String> displayedValues = new ArrayList<String>();

                for (int i = 0; i < 60; i += TIME_PICKER_INTERVAL) {
                    displayedValues.add(String.format("%02d", i));
                }
                mMinuteSpinner.setDisplayedValues(displayedValues.toArray(new String[0]));

            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

*/
}
