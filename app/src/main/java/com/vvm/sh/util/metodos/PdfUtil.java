package com.vvm.sh.util.metodos;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.documentos.DadosTemplate;
import com.vvm.sh.documentos.certificadoTratamento.CertificadoTratamento;
import com.vvm.sh.documentos.certificadoTratamento.modelos.DadosCertificadoTratamento;
import com.vvm.sh.documentos.informacaoSst.InformacaoSst;
import com.vvm.sh.documentos.informacaoSst.modelos.DadosInformacaoSst;
import com.vvm.sh.documentos.registoVisita.RegistoVisita;
import com.vvm.sh.documentos.registoVisita.modelos.DadosRegistoVisita;
import com.vvm.sh.util.excepcoes.DocumentoPdfException;
import com.vvm.sh.util.viewmodel.BaseViewModel;

public class PdfUtil {

    public static Template obterTemplate(Context contexto, int idTarefa, int idAtividade, DadosTemplate dadosTemplate) throws DocumentoPdfException {

        Template template = null;

        if(dadosTemplate instanceof DadosRegistoVisita){
            template = new RegistoVisita(contexto, idTarefa, (DadosRegistoVisita) dadosTemplate);
        }
        else if(dadosTemplate instanceof DadosCertificadoTratamento){
            template = new CertificadoTratamento(contexto, idTarefa, idAtividade, (DadosCertificadoTratamento) dadosTemplate);
        }
        else if(dadosTemplate instanceof DadosInformacaoSst){
            template = new InformacaoSst(contexto, idTarefa, (DadosInformacaoSst) dadosTemplate);
        }

        if(template == null) {
            throw new DocumentoPdfException("Template do pdf indisponível");
        }

        return template;

    }

}
