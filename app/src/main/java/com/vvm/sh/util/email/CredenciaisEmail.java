package com.vvm.sh.util.email;

import androidx.room.Ignore;

import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.EmailConfig;


public class CredenciaisEmail {

    public String destino, titulo, corpoEmail, emissor, palavraChave;

    @Ignore
    public boolean teste;

    public CredenciaisEmail(String destino, String titulo, String corpoEmail) {

        this.emissor = EmailConfig.ENDERECO_EMAIL;
        this.palavraChave = EmailConfig.PALAVRA_CHAVE;

        this.destino = destino;
        this.titulo = titulo;
        this.corpoEmail = corpoEmail;
        teste = false;

        if(AppConfig.VERSAO_TESTE){ //teste
            teste = true;
            this.emissor = EmailConfig.Teste.ENDERECO_EMAIL;
            this.palavraChave = EmailConfig.Teste.PALAVRA_CHAVE;

            this.destino = EmailConfig.Teste.ENDERECO_EMAIL;
        }
    }
}
