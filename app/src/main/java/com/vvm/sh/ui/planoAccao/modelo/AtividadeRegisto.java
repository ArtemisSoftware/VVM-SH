package com.vvm.sh.ui.planoAccao.modelo;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.PlanoAcaoAtividade;
import com.vvm.sh.baseDados.entidades.PlanoAccaoResultado;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.DatasUtil;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class AtividadeRegisto {

    @Embedded
    public PlanoAcaoAtividade atividade;


    @Relation(
            parentColumn = "id",
            entityColumn = "idAtividadePlano"
    )
    public PlanoAccaoResultado resultado;

    @ColumnInfo(name = "tipo")
    public int tipo;


    @ColumnInfo(name = "nota")
    public String nota;

    @ColumnInfo(name = "reprogramavel")
    public boolean reprogramavel;

    @ColumnInfo(name = "tipoExecucao")
    public int tipoExecucao;


    @ColumnInfo(name = "data")
    public String data;


    @Ignore
    private int corExecucao;

    @Ignore
    private List<Date> datas;

    @Ignore
    public int mes;


    /**
     * Metodo que permite gerar a marcacao.
     */
    public void gerarMarcacao() { //(yyyy-mm-dd)

        datas = new ArrayList<>();


        if(tipo == Identificadores.TIPO_NOTA){
            return;
        }

        for(int index = 0; index < data.split(",").length; ++index){

            Date periodo = DatasUtil.converterString(data.split(",")[index]);

            if(periodo == null){
                this.datas.add(DatasUtil.converterString(data.split(",")[index], DatasUtil.DATA_FORMATO_YYYY_MM));
            }
            else{
                this.datas.add(periodo);
            }
        }


        switch (tipoExecucao) {

            case Identificadores.EXECUTADA:

                corExecucao = R.color.cor_executada;
                break;

            case Identificadores.PROGRAMADA_FIXA:

                corExecucao = R.color.cor_programada_fixa;
                break;

            case Identificadores.PROGRAMADA:

                corExecucao = R.color.cor_programada;
                break;

            case Identificadores.REPROGRAMADA:

                corExecucao = R.color.cor_reprogramada;
                break;

            default:
                corExecucao = R.color.cor_omissao/*cor_omissao*/;
                break;
        }
    }



    /**
     * Metodo que permite obter a cor da execucao de um mes
     * @param ano o ano do mes
     * @param mes o mes pretendido (0,1....11)
     * @return a cor
     */
    public int obterCorExecucao(int ano, int mes){

        this.mes = mes;
        Calendar cal = Calendar.getInstance();

        for(Date data : datas){

            cal.setTime(data);

            if(cal.get(Calendar.YEAR) == ano & cal.get(Calendar.MONTH) == mes){
                return corExecucao;
            }
        }

        return R.color.cor_omissao;
    }


}
