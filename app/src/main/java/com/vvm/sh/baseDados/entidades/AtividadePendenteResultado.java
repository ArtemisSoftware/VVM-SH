package com.vvm.sh.baseDados.entidades;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;

import com.vvm.sh.baseDados.entidades.AtividadePendente;
import com.vvm.sh.util.constantes.Identificadores;

import java.util.Date;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "atividadesPendentesResultado",
        indices = {@Index(value="id", unique = false) },
        primaryKeys = {"id"},
        foreignKeys = @ForeignKey(entity = AtividadePendente.class,
                parentColumns = "id",
                childColumns = "id",
                onDelete = CASCADE))
public class AtividadePendenteResultado implements Parcelable {



    @NonNull
    public int id;


    @NonNull
    @ColumnInfo(name = "idEstado")
    public int idEstado;


    @ColumnInfo(name = "tempoExecucao")
    public String tempoExecucao;

    @ColumnInfo(name = "dataExecucao")
    public Date dataExecucao;


    @NonNull
    @ColumnInfo(name = "idAnomalia", defaultValue = Identificadores.SEM_VALOR)
    public int idAnomalia;

    @ColumnInfo(name = "observacao")
    public String observacao;


    @Ignore
    public AtividadePendenteResultado(int id, int idAnomalia, String observacao) {
        this.id = id;
        this.idEstado = Identificadores.Estados.ESTADO_NAO_EXECUTADO;
        this.idAnomalia = idAnomalia;
        this.observacao = observacao;
    }

    @Ignore
    public AtividadePendenteResultado(int id, String tempoExecucao, Date dataExecucao) {
        this.id = id;
        this.idEstado = Identificadores.Estados.ESTADO_EXECUTADO;
        this.idAnomalia = Identificadores.SEM_VALOR_INT;
        this.tempoExecucao = tempoExecucao;
        this.dataExecucao = dataExecucao;
    }

    public AtividadePendenteResultado(int id, int idEstado, String tempoExecucao, Date dataExecucao, int idAnomalia, String observacao) {
        this.id = id;
        this.idEstado = idEstado;
        this.tempoExecucao = tempoExecucao;
        this.dataExecucao = dataExecucao;
        this.idAnomalia = idAnomalia;
        this.observacao = observacao;
    }

    protected AtividadePendenteResultado(Parcel in) {
        id = in.readInt();
        idEstado = in.readInt();
        tempoExecucao = in.readString();
        idAnomalia = in.readInt();
        observacao = in.readString();
    }





    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(idEstado);
        dest.writeString(tempoExecucao);
        dest.writeInt(idAnomalia);
        dest.writeString(observacao);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<AtividadePendenteResultado> CREATOR = new Creator<AtividadePendenteResultado>() {
        @Override
        public AtividadePendenteResultado createFromParcel(Parcel in) {
            return new AtividadePendenteResultado(in);
        }

        @Override
        public AtividadePendenteResultado[] newArray(int size) {
            return new AtividadePendenteResultado[size];
        }
    };
}
