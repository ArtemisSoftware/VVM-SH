package com.vvm.sh.baseDados.entidades;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.util.constantes.Identificadores;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "questionarioChecklistResultado",

        foreignKeys = @ForeignKey(entity = AreaChecklistResultado.class,
                parentColumns = "id",
                childColumns = "idArea",
                onDelete = CASCADE))
public class QuestionarioChecklistResultado {


    @NonNull
    public int idArea;

    @NonNull
    public String idSeccao;

    @NonNull
    public String idItem;



    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    public String tipo;



    public String resposta;
    public String nr;
    public String ni;


    @NonNull
    @ColumnInfo(name = "ut1", defaultValue = Identificadores.VALOR_0)
    public int ut1;

    @NonNull
    @ColumnInfo(name = "ut1_CategoriasRisco", defaultValue = Identificadores.VALOR_0)
    public int ut1_CategoriasRisco;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_A", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_A;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_B", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_B;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_C", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_C;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_D", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_D;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_E", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_E;

    @NonNull
    @ColumnInfo(name = "ut1_LocalRisco_F", defaultValue = Identificadores.VALOR_0)
    public boolean ut1_LocalRisco_F;

    @NonNull
    @ColumnInfo(name = "ut2", defaultValue = Identificadores.VALOR_0)
    public int ut2;

    @NonNull
    @ColumnInfo(name = "ut2_CategoriasRisco", defaultValue = Identificadores.VALOR_0)
    public int ut2_CategoriasRisco;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_A", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_A;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_B", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_B;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_C", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_C;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_D", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_D;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_E", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_E;

    @NonNull
    @ColumnInfo(name = "ut2_LocalRisco_F", defaultValue = Identificadores.VALOR_0)
    public boolean ut2_LocalRisco_F;

    public String observacao;


    @NonNull
    public int origem;

    @Ignore
    public QuestionarioChecklistResultado() {
    }

    @Ignore
    public QuestionarioChecklistResultado(int idRegistoArea, String idSeccao, String idQuestao, String resposta, String ni) {
        this.idArea = idRegistoArea;
        this.idSeccao = idSeccao;
        this.idItem = idQuestao;
        this.tipo = Identificadores.Checklist.TIPO_QUESTAO;
        this.resposta = resposta;
        this.ni = ni;
    }

    @Ignore
    public QuestionarioChecklistResultado(Item item, String idQuestao, String resposta, String ni) {
        this.idArea = item.id;
        this.idSeccao = item.uid;
        this.idItem = idQuestao;
        this.tipo = Identificadores.Checklist.TIPO_QUESTAO;
        this.resposta = resposta;
        this.ni = ni;
    }


    @Ignore
    public QuestionarioChecklistResultado(Item item, String idQuestao, String observacao) {
        this.idArea = item.id;
        this.idSeccao = item.uid;
        this.idItem = idQuestao;
        this.tipo = Identificadores.Checklist.TIPO_OBSERVACOES;
        this.observacao = observacao;
    }


    @Ignore
    public QuestionarioChecklistResultado(Item item, String idQuestao) {
        this.idArea = item.id;
        this.idSeccao = item.uid;
        this.idItem = idQuestao;
        this.tipo = Identificadores.Checklist.TIPO_FOTOS;
    }


    public QuestionarioChecklistResultado(Item item, String idQuestao, int numeroUt, int ut, int ut_CategoriasRisco,
                                          boolean ut_LocalRisco_A, boolean ut_LocalRisco_B, boolean ut_LocalRisco_C,
                                          boolean ut_LocalRisco_D, boolean ut_LocalRisco_E, boolean ut_LocalRisco_F) {
        this.idArea = item.id;
        this.idSeccao = item.uid;
        this.idItem = idQuestao;
        this.tipo = Identificadores.Checklist.TIPO_UTS;


        if(numeroUt == 1) {
            this.ut1 = ut;
            this.ut1_CategoriasRisco = ut_CategoriasRisco;
            this.ut1_LocalRisco_A = ut_LocalRisco_A;
            this.ut1_LocalRisco_B = ut_LocalRisco_B;
            this.ut1_LocalRisco_C = ut_LocalRisco_C;
            this.ut1_LocalRisco_D = ut_LocalRisco_D;
            this.ut1_LocalRisco_E = ut_LocalRisco_E;
            this.ut1_LocalRisco_F = ut_LocalRisco_F;
        }
        else {
            this.ut2 = ut;
            this.ut2_CategoriasRisco = ut_CategoriasRisco;
            this.ut2_LocalRisco_A = ut_LocalRisco_A;
            this.ut2_LocalRisco_B = ut_LocalRisco_B;
            this.ut2_LocalRisco_C = ut_LocalRisco_C;
            this.ut2_LocalRisco_D = ut_LocalRisco_D;
            this.ut2_LocalRisco_E = ut_LocalRisco_E;
            this.ut2_LocalRisco_F = ut_LocalRisco_F;
        }

    }




    public QuestionarioChecklistResultado(int idArea, @NonNull String idSeccao, @NonNull String idItem, int id,
                                          @NonNull String tipo, String resposta, String nr, String ni, int ut1, int ut1_CategoriasRisco,
                                          boolean ut1_LocalRisco_A, boolean ut1_LocalRisco_B, boolean ut1_LocalRisco_C, boolean ut1_LocalRisco_D,
                                          boolean ut1_LocalRisco_E, boolean ut1_LocalRisco_F, int ut2, int ut2_CategoriasRisco,
                                          boolean ut2_LocalRisco_A, boolean ut2_LocalRisco_B, boolean ut2_LocalRisco_C, boolean ut2_LocalRisco_D,
                                          boolean ut2_LocalRisco_E, boolean ut2_LocalRisco_F, String observacao, int origem) {
        this.idArea = idArea;
        this.idSeccao = idSeccao;
        this.idItem = idItem;
        this.id = id;
        this.tipo = tipo;
        this.resposta = resposta;
        this.nr = nr;
        this.ni = ni;
        this.ut1 = ut1;
        this.ut1_CategoriasRisco = ut1_CategoriasRisco;
        this.ut1_LocalRisco_A = ut1_LocalRisco_A;
        this.ut1_LocalRisco_B = ut1_LocalRisco_B;
        this.ut1_LocalRisco_C = ut1_LocalRisco_C;
        this.ut1_LocalRisco_D = ut1_LocalRisco_D;
        this.ut1_LocalRisco_E = ut1_LocalRisco_E;
        this.ut1_LocalRisco_F = ut1_LocalRisco_F;
        this.ut2 = ut2;
        this.ut2_CategoriasRisco = ut2_CategoriasRisco;
        this.ut2_LocalRisco_A = ut2_LocalRisco_A;
        this.ut2_LocalRisco_B = ut2_LocalRisco_B;
        this.ut2_LocalRisco_C = ut2_LocalRisco_C;
        this.ut2_LocalRisco_D = ut2_LocalRisco_D;
        this.ut2_LocalRisco_E = ut2_LocalRisco_E;
        this.ut2_LocalRisco_F = ut2_LocalRisco_F;
        this.observacao = observacao;
        this.origem = origem;
    }


    public void fixarUt(int numeroUt, int ut, int ut_CategoriasRisco,
                        boolean ut_LocalRisco_A, boolean ut_LocalRisco_B, boolean ut_LocalRisco_C,
                        boolean ut_LocalRisco_D, boolean ut_LocalRisco_E, boolean ut_LocalRisco_F){
        if(numeroUt == 1) {
            this.ut1 = ut;
            this.ut1_CategoriasRisco = ut_CategoriasRisco;
            this.ut1_LocalRisco_A = ut_LocalRisco_A;
            this.ut1_LocalRisco_B = ut_LocalRisco_B;
            this.ut1_LocalRisco_C = ut_LocalRisco_C;
            this.ut1_LocalRisco_D = ut_LocalRisco_D;
            this.ut1_LocalRisco_E = ut_LocalRisco_E;
            this.ut1_LocalRisco_F = ut_LocalRisco_F;
        }
        else {
            this.ut2 = ut;
            this.ut2_CategoriasRisco = ut_CategoriasRisco;
            this.ut2_LocalRisco_A = ut_LocalRisco_A;
            this.ut2_LocalRisco_B = ut_LocalRisco_B;
            this.ut2_LocalRisco_C = ut_LocalRisco_C;
            this.ut2_LocalRisco_D = ut_LocalRisco_D;
            this.ut2_LocalRisco_E = ut_LocalRisco_E;
            this.ut2_LocalRisco_F = ut_LocalRisco_F;
        }
    }

}
