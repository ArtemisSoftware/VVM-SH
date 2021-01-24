package com.vvm.sh.servicos.relatorio;

import android.content.Context;

import com.titan.pdfdocumentlibrary.bundle.Template;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.documentos.DadosTemplate;
import com.vvm.sh.documentos.DocumentoPdf;
import com.vvm.sh.documentos.certificadoTratamento.CertificadoTratamento;
import com.vvm.sh.documentos.certificadoTratamento.modelos.DadosCertificadoTratamento;
import com.vvm.sh.documentos.registoVisita.RegistoVisita;
import com.vvm.sh.documentos.registoVisita.modelos.DadosRegistoVisita;
import com.vvm.sh.util.email.Email;
import com.vvm.sh.util.metodos.MensagensUtil;

public class EnvioRelatorio implements EnvioDocumento{

    private Context contexto;
    private int idTarefa, idAtividade;
    private MensagensUtil dialogo;
    private DadosTemplate dadosTemplate;
    private DocumentoPdf linestenerPdf;
    private VvmshBaseDados vvmshBaseDados;

    public EnvioRelatorio(Context contexto, VvmshBaseDados vvmshBaseDados, int idTarefa, int idAtividade, DadosTemplate dadosTemplate, DocumentoPdf linestenerPdf) {

        this.contexto = contexto;
        this.idTarefa = idTarefa;
        this.idAtividade = idAtividade;
        this.dadosTemplate = dadosTemplate;
        dialogo = new MensagensUtil(contexto);
        this.vvmshBaseDados = vvmshBaseDados;
        this.linestenerPdf = linestenerPdf;
    }


    public void executar(){

        Template template = null;

        if(dadosTemplate instanceof DadosRegistoVisita){
            template = new RegistoVisita(contexto, idTarefa, (DadosRegistoVisita) dadosTemplate);
        }
        else if(dadosTemplate instanceof  DadosCertificadoTratamento){
            template = new CertificadoTratamento(contexto, idTarefa, idAtividade, (DadosCertificadoTratamento) dadosTemplate);
        }

        if(template != null) {
            GerarPdfAsyncTask servico = new GerarPdfAsyncTask(contexto, template, dialogo, this);
            servico.execute();
        }
    }

    @Override
    public void enviarEmail(String caminhoPdf) {

        Email email = new Email(dadosTemplate.credenciaisEmail);
        email.adicionarAnexo(caminhoPdf);

        EnvioEmailPdfAsyncTask servico = new EnvioEmailPdfAsyncTask(contexto, idTarefa, idAtividade, vvmshBaseDados, linestenerPdf, this);
        servico.execute(email);

    }

    @Override
    public void terminarExecucao() {

    }
}
