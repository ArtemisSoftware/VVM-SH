package com.vvm.sh.servicos.relatorio;

public interface EnvioDocumento {

    void enviarEmail(String caminhoPdf);
    void terminarExecucao();
}
