package com.vvm.sh.util;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class Recurso <T> {

    @NonNull
    public final Status status;

    @Nullable
    public final T dados;

    @Nullable
    public final String messagem;

    public Recurso(@NonNull Status status, @Nullable T dados, @NonNull String messagem) {
        this.status = status;
        this.dados = dados;
        this.messagem = messagem;
    }


    public static <T> Recurso<T> successo() {
        return new Recurso<>(Status.SUCESSO, null, "");
    }

    public static <T> Recurso<T> successo(@NonNull T data) {
        return new Recurso<>(Status.SUCESSO, data, "");
    }

    public static <T> Recurso<T> successo(@NonNull T data, @NonNull String messagem) {
        return new Recurso<>(Status.SUCESSO, data, messagem);
    }



    public static <T> Recurso<T> erro(@NonNull String msg) {
        return new Recurso<>(Status.ERRO, null, msg);
    }

    public static <T> Recurso<T> erro( @Nullable T data, @NonNull String msg) {
        return new Recurso<>(Status.ERRO, data, msg);
    }



    public static <T> Recurso<T> loading(@Nullable T data) {
        return new Recurso<>(Status.LOADING, data, null);
    }

    public enum Status {SUCESSO, ERRO, LOADING }

    @Override
    public boolean equals(Object obj) {
        if(obj.getClass() != getClass() || obj.getClass() != Recurso.class){
            return false;
        }

        Recurso<T> recurso = (Recurso) obj;

        if(recurso.status != this.status){
            return false;
        }

        if(this.dados != null){
            if(recurso.dados != this.dados){
                return false;
            }
        }

        if(recurso.messagem != null){
            if(this.messagem == null){
                return false;
            }
            if(!recurso.messagem.equals(this.messagem)){
                return false;
            }
        }

        return true;
    }
}