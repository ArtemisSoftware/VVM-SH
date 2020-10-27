package com.vvm.sh.ui.imagens.modelos;

import android.os.Parcel;
import android.os.Parcelable;

public class Galeria implements Parcelable {

    public static final int GALERIA_LEVANTAMENTO = 1;
    public static final int GALERIA_CAPA_RELATORIO = 2;

    public int idGaleria;
    public int id;


    public Galeria(int idGaleria, int id) {
        this.idGaleria = idGaleria;
        this.id = id;
    }

    protected Galeria(Parcel in) {
        idGaleria = in.readInt();
        id = in.readInt();
    }

    public static final Creator<Galeria> CREATOR = new Creator<Galeria>() {
        @Override
        public Galeria createFromParcel(Parcel in) {
            return new Galeria(in);
        }

        @Override
        public Galeria[] newArray(int size) {
            return new Galeria[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(idGaleria);
        dest.writeInt(id);
    }
}
