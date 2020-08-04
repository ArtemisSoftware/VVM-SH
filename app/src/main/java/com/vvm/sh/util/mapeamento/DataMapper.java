package com.vvm.sh.util.mapeamento;

import com.vvm.sh.util.metodos.DatasUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DataMapper {

    public String asString(Date date) {
        return date != null ? new SimpleDateFormat(DatasUtil.FORMATO_YYYY_MM_DD)
                .format( date ) : null;
    }

    public Date asDate(String date) {
        try {
            return date != null ? new SimpleDateFormat( DatasUtil.FORMATO_YYYY_MM_DD)
                    .parse( date ) : null;
        }
        catch ( ParseException e ) {
            //throw new RuntimeException( e );
            return new Date(); //TODO: devolver null ou a data de hoje????
        }
    }

}
