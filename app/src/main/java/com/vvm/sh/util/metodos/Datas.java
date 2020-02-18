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

}
