package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

public class Item implements Parcelable {


    public int idArea;

    @ColumnInfo(name = "descricao")
    public String descricao;

    @ColumnInfo(name = "subDescricao")
    public String subDescricao;


    @ColumnInfo(name = "id")
    public int id;

    @ColumnInfo(name = "uid")
    public String uid;

    @ColumnInfo(name = "completos")
    public int completos;

    @ColumnInfo(name = "total")
    public int total;

    @ColumnInfo(name = "tipo")
    public int tipo;




    public Item(int idArea, String descricao, String subDescricao, int id, int completos, int total, int tipo, String uid) {
        this.idArea = idArea;
        this.descricao = descricao;
        this.subDescricao = subDescricao;
        this.id = id;
        this.completos = completos;
        this.total = total;
        this.tipo = tipo;
        this.uid = uid;
    }



    @Ignore
    protected Item(Parcel in) {
        idArea = in.readInt();
        descricao = in.readString();
        subDescricao = in.readString();;
        uid = in.readString();
        id = in.readInt();
        completos = in.readInt();
        total = in.readInt();
        tipo = in.readInt();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idArea);
        dest.writeString(descricao);
        dest.writeString(subDescricao);
        dest.writeString(uid);
        dest.writeInt(id);
        dest.writeInt(completos);
        dest.writeInt(total);
        dest.writeInt(tipo);
    }
}
