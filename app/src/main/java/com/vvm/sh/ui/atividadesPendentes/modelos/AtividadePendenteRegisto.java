package com.vvm.sh.ui.atividadesPendentes.modelos;

import androidx.room.ColumnInfo;
import androidx.room.Embedded;
import androidx.room.Ignore;
import androidx.room.Relation;

import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.baseDados.entidades.AtividadePendenteResultado;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

public class AtividadePendenteRegisto {


    @Embedded
    public AtividadePendente atividade;


    @Relation(
            parentColumn = "id",
            entityColumn = "id"
    )
    public AtividadePendenteResultado resultado;


    @ColumnInfo(name = "nomeRelatorio")
    public String nomeRelatorio;


    @ColumnInfo(name = "possuiRelatorio")
    public boolean possuiRelatorio;

    @ColumnInfo(name = "relatorioCompleto")
    public boolean relatorioCompleto;


    @ColumnInfo(name = "validade_processo_produtivo")
    public boolean completudeProcessoProdutivo;

    @ColumnInfo(name = "validade_trabalhadores_vulneraveis")
    public boolean completudeTrabalhadoresVulneraveis;

    @ColumnInfo(name = "validade_equipamentos")
    public boolean completudeEquipamentos;

    @ColumnInfo(name = "validade_proposta_plano_acao")
    public boolean completudePlanoAcao;

    @ColumnInfo(name = "validade_checklist")
    public boolean completudeChecklist;


    @Ignore
    //@ColumnInfo(name = "validade_avaliacao_riscos")
    public boolean completudeAvaliacaoRisco;

    @Ignore
    //@ColumnInfo(name = "validade_capa_relatorio")
    public boolean completudeCapaRelatorio;

    /**
     * Metodo que indica se existe um resultado
     * @return true caso exista
     */
    public boolean existeResultado(){
        return resultado != null;
    }




}
