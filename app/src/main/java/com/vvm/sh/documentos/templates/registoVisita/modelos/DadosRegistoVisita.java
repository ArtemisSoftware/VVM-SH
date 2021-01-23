package com.vvm.sh.documentos.templates.registoVisita.modelos;

import com.vvm.sh.documentos.DadosTemplate;
import com.vvm.sh.ui.registoVisita.modelos.TrabalhoRealizado;
import com.vvm.sh.util.email.CredenciaisEmail;

import java.util.List;

public class DadosRegistoVisita extends DadosTemplate {


    public List<TrabalhoRealizado> trabalhoRealizados;

    public String referencia = "";
    public CredenciaisEmail credenciaisEmail;
}
