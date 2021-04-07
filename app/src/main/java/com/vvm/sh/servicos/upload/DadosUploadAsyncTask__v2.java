package com.vvm.sh.servicos.upload;

import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.os.Handler;

import com.vvm.sh.api.modelos.bd.CertificadoBd;
import com.vvm.sh.api.modelos.bd.FormandoBd;
import com.vvm.sh.api.modelos.envio.AcaoFormacao;
import com.vvm.sh.api.modelos.envio.AtividadePendente;
import com.vvm.sh.api.modelos.envio.CertificadoTratamento;
import com.vvm.sh.api.modelos.envio.CrossSelling;
import com.vvm.sh.api.modelos.envio.DadosFormulario;
import com.vvm.sh.api.modelos.envio.Email;
import com.vvm.sh.api.modelos.envio.Formando;
import com.vvm.sh.api.modelos.envio.Imagem;
import com.vvm.sh.api.modelos.envio.Ocorrencia;
import com.vvm.sh.baseDados.VvmshBaseDados;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.baseDados.entidades.Resultado;
import com.vvm.sh.repositorios.UploadRepositorio;
import com.vvm.sh.ui.transferencias.modelos.DadosUpload;
import com.vvm.sh.ui.transferencias.modelos.Upload;
import com.vvm.sh.util.AtualizacaoUI;
import com.vvm.sh.util.ResultadoId;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.mapeamento.UploadMapping;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.MensagensUtil;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_ATIVIDADE_PENDENTE;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_CROSS_SELLING;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_EMAIL;
import static com.vvm.sh.util.constantes.Identificadores.Resultados.ID_OCORRENCIA;

public class DadosUploadAsyncTask__v2 extends AsyncTask<List<Upload>, Void, Void> {

    private String erro;
    private VvmshBaseDados vvmshBaseDados;

    private JSONArray dadosTarefas = new JSONArray();

    protected UploadRepositorio repositorio;

    /**
     * variavel que contem os identificadores das imagens a fazer upload
     */
    protected List<Integer> idImagens;
    protected DadosUpload dadosUpload;
    protected String idUtilizador;

    private AtualizacaoUI atualizacaoUI;
    private MensagensUtil dialogo;




    public DadosUploadAsyncTask__v2(VvmshBaseDados vvmshBaseDados, Handler handler, UploadRepositorio repositorio, String idUtilizador){
        this.vvmshBaseDados = vvmshBaseDados;
        this.repositorio = repositorio;
        this.idUtilizador = idUtilizador;
        this.idImagens = new ArrayList<>();
        this.dadosUpload = new DadosUpload(idUtilizador);
        atualizacaoUI = new AtualizacaoUI(handler);
    }



    @Override
    protected Void doInBackground(List<Upload>... resultados) {

        if(resultados[0] == null)
            return null;

        List<Upload> resposta = resultados[0];


        this.vvmshBaseDados.runInTransaction(new Runnable(){
            @Override
            public void run(){

                try {

                    //--obterDados(resposta);
                    //--obterImagens();

                    atualizacaoUI.atualizarUI(AtualizacaoUI.Codigo.PROCESSAMENTO_UPLOAD_CONCLUIDO, dadosUpload);
                    
                }
                catch(SQLiteConstraintException throwable){
                    erro = throwable.getMessage();
                }
            }
        });


        return null;
    }


}