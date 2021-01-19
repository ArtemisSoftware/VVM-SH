package com.vvm.sh.documentos.registoVisita.modelos;

import com.vvm.sh.documentos.modelos.Rubrica;
import com.vvm.sh.documentos.modelos.DadosCliente;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.email.CredenciaisEmail;

import java.util.List;

public class DadosRegistoVisita {

    public DadosCliente dadosCliente;
    public List<TrabalhoRealizado> trabalhoRealizados;
    public Rubrica rubrica;
    public String referencia = "";
    public CredenciaisEmail credenciaisEmail;
}
