package com.vvm.sh.util.email;

import androidx.room.ColumnInfo;
import androidx.room.Ignore;

import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.EmailConfig;


public class CredenciaisEmail {

    public String destino, titulo, corpoEmail;

    @Ignore
    public boolean teste;

    public CredenciaisEmail(String destino, String titulo, String corpoEmail) {
        this.destino = destino;
        this.titulo = titulo;
        this.corpoEmail = corpoEmail;
        teste = false;

        if(AppConfig.VERSAO_TESTE){ //teste
            teste = true;
            this.destino = EmailConfig.Teste.EMISSOR;
            this.titulo = EmailConfig.Teste.PALAVRA_CHAVE;
            this.corpoEmail = EmailConfig.Teste.EMISSOR;
        }
    }
}
