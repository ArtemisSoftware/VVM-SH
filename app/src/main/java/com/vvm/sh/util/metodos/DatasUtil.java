package com.vvm.sh.util.metodos;

import android.app.Activity;
import android.content.Context;
import android.widget.TextView;

import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Sintaxe;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DatasUtil {

    public static final String FORMATO_DD_MM_YYYY = "dd-MM-yyyy";
    public static final String FORMATO_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String DATA_FORMATO_YYYY_MM = "yyyy-MM";
    public static final String DATA_FORMATO_YYYY_MM_DD__HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String FORMATO_FICHEIRO_BD = "dd-MM-yyyy_HHmmss";

    public static final String DATA_FORMATO_DD_MM_YYYY__HH_MM = "dd-MM-yyyy HH:mm";
    public static final String FORMATO_DD_MM_YYYY__HH_MM_SS = "dd-MM-yyyy HH:mm:ss";
    public static final String DATA_FORMATO_DD_MM_YYYY__HH_MM_SS_V2 = "dd/MM/yyyy HH:mm:ss";

    public static final String FORMATO_DD_MMMM_YYYY = "dd-MMMM-yyyy";
    public static final String DATA_FORMATO_MMMM_YYYY = "MMMM yyyy";
    public static final String HORA_FORMATO_HH_MM = "HH:mm";
    public static final String HORA_FORMATO_HH_MM_SS = "HH:mm:ss";

    public static final Locale LOCAL_PORTUGAL = new Locale( "pt" );


    /**
     * Metodo que permite obter a data de validade da autenticacao
     * @return data no formato long
     */
    public static long obterDataValidadeAutenticacao(){

        Calendar calendario = Calendar.getInstance();
        calendario.add(Calendar.DAY_OF_YEAR, AppConfig.DIAS_VALIDADE_AUTENTICACAO);
        Date data =new Date(calendario.getTimeInMillis());
        return data.getTime();
    }



    /**
     * Metodo que permite obter a dados atual
     * @param formato formato da dados
     * @return dados no formato escolhido
     */
    public static String obterDataAtual(String formato){

        DateFormat df = new SimpleDateFormat(formato);
        Date dataHoje = Calendar.getInstance().getTime();
        String dataActual = df.format(dataHoje);

        return dataActual;
    }


    /**
     * Metodo que permite obter a dados atual
     * @param formato formato da dados
     * @param local lingua do mes
     * @return dados no formato escolhido
     */
    public static String obterDataAtual(String formato, Locale local){

        Date dataHoje = Calendar.getInstance().getTime();

        DateFormat fmt = new SimpleDateFormat(formato, local);
        String data = fmt.format(dataHoje.getTime());

        return data;
    }

    public static long obterDataAtual(){

        String data = obterDataAtual(FORMATO_DD_MMMM_YYYY);
        SimpleDateFormat format = new SimpleDateFormat(FORMATO_DD_MMMM_YYYY);

        try {
            Date dataResultado = format.parse(data);
            return dataResultado.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;

    }



    /**
     * Metodo que permite converter uma data numformato especifico para String
     * @param data a data a converter
     * @param formato o formato da data convertida
     * @return uma data
     */
    public static String converterData(Date data, String formato){

       if(data == null){
           return "";
       }

       DateFormat fmt = new SimpleDateFormat(formato);
       return fmt.format(data.getTime());

    }


    public static Date converterString(String data, String formato){

        SimpleDateFormat format = new SimpleDateFormat(formato);
        try {
            return format.parse(data);

        } catch (ParseException e) {
            return new Date();
        }
    }


    public static long converterDataLong(String data, String formato){

        SimpleDateFormat format = new SimpleDateFormat(formato);

        try {
            Date dataResultado = format.parse(data);
            return dataResultado.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;

    }



    /**
     * Metodo que permite obter uma dados
     * @param ano
     * @param mesDoAno 0 - Janeiro , 1 - Fevereiro....
     * @param diaDoMes o dia do mes
     * @param formatoData o formato da dados (ex:dd/MM/yyyy)
     * @return uma data
     */
    public static String converterData(int ano, int mesDoAno, int diaDoMes, String formatoData){

        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mesDoAno, diaDoMes);

        SimpleDateFormat format = new SimpleDateFormat(formatoData);
        String data = format.format(calendar.getTime());

        return data;
    }


    /**
     * Metodo que permite obter uma dados
     * @param ano
     * @param mesDoAno 0 - Janeiro , 1 - Fevereiro....
     * @param diaDoMes o dia do mes
     * @return uma data
     */
    public static long converterData(int ano, int mesDoAno, int diaDoMes){

        String data = converterData(ano, mesDoAno, diaDoMes, FORMATO_DD_MMMM_YYYY);
        SimpleDateFormat format = new SimpleDateFormat(FORMATO_DD_MMMM_YYYY);

        try {
            Date dataResultado = format.parse(data);
            return dataResultado.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return 0;
    }



    /**
     * Metodo que permite obter uma dados com o mes por extenso
     * @param ano
     * @param mesDoAno 0 - Janeiro , 1 - Fevereiro....
     * @param diaDoMes o dia do mes
     * @param formatoData o formato da dados (ex:dd/MMMM/yyyy)
     * @param local lingua do mes
     * @return uma dados
     */
    public static String converterData(int ano, int mesDoAno, int diaDoMes, String formatoData, Locale local){

        Calendar calendar = Calendar.getInstance();
        calendar.set(ano, mesDoAno, diaDoMes);

        DateFormat fmt = new SimpleDateFormat(formatoData, local);
        String data = fmt.format(calendar.getTime());

        return data;
    }


    /**
     * Metodo que converte uma dados no formato string para o formato date
     * @param data dados a converter (YYYY-mm-dd)
     * @param formatoData o formato da dados (ex:dd/MM/yyyy)
     * @return uma dados
     */
    public static String converterData(String data, String formatoData){

        if(data == null){
            return "";
        }

        SimpleDateFormat formatter = new SimpleDateFormat(DATA_FORMATO_YYYY_MM_DD__HH_MM_SS);
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
     * Metodo que convert um long para uma dados
     * @param data long representativo da dados
     * @param formato formato pretendido para a dados
     * @return a nova dados
     */
    public static String converterData(long data, String formato) {

        Date resultado = new Date(data);
        SimpleDateFormat formatoData = new SimpleDateFormat(formato);
        return formatoData.format(resultado);
    }





    public static DatePickerDialog obterCalendarioAgenda(DatePickerDialog.OnDateSetListener listener){

        int ano, mes, dia;

        Calendar calendar = Calendar.getInstance();
        ano = calendar.get(Calendar.YEAR) ;
        mes = calendar.get(Calendar.MONTH);
        dia = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dpd = DatePickerDialog.newInstance(listener, ano, mes, dia);

        dpd.setTitle(Sintaxe.Titulos.AGENDA);

        // Setting Min Date to today date
        Calendar min_date_c = Calendar.getInstance();
        dpd.setMinDate(min_date_c);


        // Setting Max Date to next 2 years
        Calendar max_date_c = Calendar.getInstance();
        max_date_c.set(Calendar.YEAR, ano + 2);
        //max_date_c.set(Calendar.DAY_OF_MONTH, 15);
        dpd.setMaxDate(max_date_c);


        //Disable all SUNDAYS and SATURDAYS between Min and Max Dates
        for (Calendar loopdate = min_date_c; min_date_c.before(max_date_c); min_date_c.add(Calendar.DATE, 1), loopdate = min_date_c) {
            int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                Calendar[] disabledDays =  new Calendar[1];
                disabledDays[0] = loopdate;
                dpd.setDisabledDays(disabledDays);
            }
        }


        Calendar[] high = new Calendar[1];
        Calendar calendarff = Calendar.getInstance();
        calendarff.get(Calendar.YEAR) ;
        calendarff.get(Calendar.MONTH);
        calendarff.set(Calendar.DAY_OF_WEEK, 13);

        high [0] = calendarff;

        dpd.setHighlightedDays(high);

        /*
        dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                String date = "Date: "+Day+"/"+(Month+1)+"/"+Year;

                Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();
            }
        });
*/

        return dpd;
    }

    /**
     * Metodo que permite validar um horario
     * @param txt_hora_inicio
     * @param txt_hora_fim
     * @return true caso o horario seja valido ou false caso contrario
     */
    public static boolean validarHorario(TextView txt_hora_inicio, TextView txt_hora_fim) {

        String pattern = HORA_FORMATO_HH_MM;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);

        try {

            Date inicio = sdf.parse(txt_hora_inicio.getText().toString());
            Date fim = sdf.parse(txt_hora_fim.getText().toString());

            if(inicio.before(fim)) {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e){
            e.printStackTrace();
        }

        return false;
    }


    /**
     * Metdo que permite  validar a data de autenticacao
     * @param dataAutenticacao a data de autenticacao a validar
     * @return true caso a data seja válida ou false caso contrário
     */
    public static boolean validarSessao(long dataAutenticacao) {

        if(obterDataAtual() > dataAutenticacao){
            return false;
        }

        return true;
    }


    /**
     * Metodo qua permite escolher datas atrav?s de uma caixa de di?logo
     * @param view
     * @param txtData objecto onde escrever a dados
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
        else { //N?o existe dados escolhida

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
