package com.vvm.sh.ui.anomalias.modelos;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.vvm.sh.baseDados.entidades.Tarefa;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "anomalias",
        indices = {@Index(value="idTarefa", unique = false) },

        foreignKeys = @ForeignKey(entity = Tarefa.class,
                parentColumns = "idTarefa",
                childColumns = "idTarefa",
                onDelete = CASCADE))
public class Anomalia{


    @NonNull
    public int idTarefa;

    @NonNull
    @PrimaryKey(autoGenerate = true)
    public int id;

    @NonNull
    @ColumnInfo(name = "data")
    public Date data;

    @NonNull
    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "observacao")
    public String observacao;

    @ColumnInfo(name = "contacto")
    public String contacto;

    @NonNull
    @ColumnInfo(name = "tipo")
    public String tipo;


    @Ignore
    public Anomalia() {
    }


    public Anomalia(int idTarefa, @NonNull Date data, @NonNull String descricao, String observacao, String contacto, @NonNull String tipo) {
        this.idTarefa = idTarefa;
        this.data = data;
        this.descricao = descricao;
        this.observacao = observacao;
        this.contacto = contacto;
        this.tipo = tipo;
    }

//TODO: verificar os tipos de anomalia

//        public final static int TIPO_ANOMALIA = 1;
//        public final static int TIPO_NOVA_ANOMALIA = 2;
//
//        private String data, observacoes, contacto, tipo;
//        private int tipoAnomalia;
//
//        public Anomalia(int id, String descricao, String observacoes) {
//            super(id, descricao);
//
//            this.observacoes = observacoes;
//            this.tipoAnomalia = TIPO_NOVA_ANOMALIA;
//        }
//
//
//        public Anomalia(int id, String descricao, String data, String observacoes, String contacto, String tipo) {
//            super(id, descricao);
//
//            this.data = data;
//            this.observacoes = observacoes;
//            this.contacto = contacto;
//            this.tipo = tipo;
//
//            this.tipoAnomalia = TIPO_ANOMALIA;
//        }
//
//
//        /**
//         * Metodo que permite obter o contacto
//         * @return a contacto
//         */
//    public String obterContacto(){
//        return contacto;
//    }
//
//
//    /**
//     * Metodo que permite obter o tipo
//     * @return o tipo
//     */
//    public String obterTipo(){
//        return tipo;
//    }
//
//    /**
//     * Metodo que permite obter a observacao
//     * @return a observacao
//     */
//    public String obterObservacao(){
//        return observacoes;
//    }
//
//
//    /**
//     * Metodo que permite obter a dados
//     * @return a dados
//     */
//    public String obterData(){
//        return data;
//    }
//
//
//
//    /**
//     * Metodo que permite obter o tipo de anomalia
//     * @return o tipo de anomalia
//     */
//    public int obterTipoAnomalia(){
//        return tipoAnomalia;
//    }


}
