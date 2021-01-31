package com.vvm.sh.util.metodos;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.documentos.DadosTemplate;
import com.vvm.sh.documentos.certificadoTratamento.CertificadoTratamento;
import com.vvm.sh.documentos.certificadoTratamento.modelos.DadosCertificadoTratamento;
import com.vvm.sh.documentos.registoVisita.RegistoVisita;
import com.vvm.sh.documentos.registoVisita.modelos.DadosRegistoVisita;

public class PdfUtil {

    public static Template obterTemplate(Context contexto, int idTarefa, int idAtividade, DadosTemplate dadosTemplate){

        Template template = null;

        if(dadosTemplate instanceof DadosRegistoVisita){
            template = new RegistoVisita(contexto, idTarefa, (DadosRegistoVisita) dadosTemplate);
        }
        else if(dadosTemplate instanceof DadosCertificadoTratamento){
            template = new CertificadoTratamento(contexto, idTarefa, idAtividade, (DadosCertificadoTratamento) dadosTemplate);
        }

        return template;

    }

}
