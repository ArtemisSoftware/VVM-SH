package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "propostaPlanoAcaoResultado",
        indices = {@Index(value="idAtividade", unique = false) },

        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "idAtividade",
                onDelete = CASCADE))
public class PropostaPlanoAcaoResultado {


    @NonNull
    @ColumnInfo(name = "idAtividade")
    public int idAtividade;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;


    @NonNull
    @ColumnInfo(name = "origem", defaultValue = Identificadores.VALOR_0 + "")
    public int origem;


    @ColumnInfo(name = "idQuestao", defaultValue = Identificadores.VALOR_0 + "")
    public int idQuestao;

    @ColumnInfo(name = "idMedida", defaultValue = Identificadores.VALOR_0 + "")
    public int idMedida;

    @ColumnInfo(name = "idNi", defaultValue = Identificadores.VALOR_0 + "")
    public int idNi;

    @ColumnInfo(name = "idPrazo", defaultValue = Identificadores.VALOR_0 + "")
    public int idPrazo;


    @NonNull
    @ColumnInfo(name = "selecionado", defaultValue = Sintaxe.Codigos.NAO_SELECIONADO)
    public boolean selecionado;


    @Ignore
    public PropostaPlanoAcaoResultado(int idAtividade, int idQuestao) {
        this.idAtividade = idAtividade;
        this.origem = Identificadores.Origens.CHECKLIST;
        this.idQuestao = idQuestao;
        this.selecionado = true;
    }

    @Ignore
    public PropostaPlanoAcaoResultado(int idAtividade, int idQuestao, int idMedida) {
        this.idAtividade = idAtividade;
        this.idMedida = idMedida;
        this.origem = Identificadores.Origens.ORIGEM_LEVANTAMENTO_RISCO;
        this.idQuestao = idQuestao;
        this.selecionado = true;
    }


    public PropostaPlanoAcaoResultado(int idAtividade, int id, int origem, int idQuestao, int idMedida, int idNi, int idPrazo, boolean selecionado) {
        this.idAtividade = idAtividade;
        this.id = id;
        this.origem = origem;
        this.idQuestao = idQuestao;
        this.idMedida = idMedida;
        this.idNi = idNi;
        this.idPrazo = idPrazo;
        this.selecionado = selecionado;
    }
}
