package com.vvm.sh.ui.pesquisa;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Pesquisa implements Parcelable {

    public boolean escolhaMultipla;
    public String metodo;
    public List<Integer> registosSelecionados;

    public Pesquisa(String metodo) {
        this.escolhaMultipla = false;
        this.metodo = metodo;
        this.registosSelecionados = new ArrayList<>();
    }

    public Pesquisa(boolean escolhaMultipla, String metodo) {
        this.escolhaMultipla = escolhaMultipla;
        this.metodo = metodo;
        this.registosSelecionados = new ArrayList<>();
    }

    public Pesquisa(boolean escolhaMultipla, String metodo, List<Integer> registosSelecionados) {
        this.escolhaMultipla = escolhaMultipla;
        this.metodo = metodo;
        this.registosSelecionados = registosSelecionados;
    }

    protected Pesquisa(Parcel in) {
        escolhaMultipla = in.readByte() != 0;
        metodo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (escolhaMultipla ? 1 : 0));
        dest.writeString(metodo);
        dest.writeList(registosSelecionados);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Pesquisa> CREATOR = new Creator<Pesquisa>() {
        @Override
        public Pesquisa createFromParcel(Parcel in) {
            return new Pesquisa(in);
        }

        @Override
        public Pesquisa[] newArray(int size) {
            return new Pesquisa[size];
        }
    };
}
