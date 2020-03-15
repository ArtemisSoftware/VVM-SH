package com.vvm.sh.carregamentos;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.util.Notificacao;
import com.vvm.sh.util.constantes.Sintaxe;

import butterknife.BindView;

import static com.vvm.sh.util.Notificacao.Codigo.*;

public abstract class CarregamentoActivity extends BaseActivity {

    @BindView(R.id.pgr_bar_progresso_notificacao)
    ProgressBar pgr_bar_progresso_notificacao;

    @BindView(R.id.txt_titulo_progresso)
    TextView txt_titulo_progresso;

    @BindView(R.id.txt_progresso)
    TextView txt_progresso;

    @BindView(R.id.rlt_lyt_progresso)
    RelativeLayout rlt_lyt_progresso;


    //--------------------------
    //Metodos locais
    //--------------------------


    /**
     * Metodo que permite apresentar o progresso da execucao de um servico
     * @param comunicado os dados da execucao
     */
    private void imprimirProgresso(Notificacao.Comunicado comunicado){

        if(comunicado.obterLimite() != Sintaxe.SEM_REGISTO){
            if(pgr_bar_progresso_notificacao.getMax() != comunicado.obterLimite()){
                pgr_bar_progresso_notificacao.setMax(comunicado.obterLimite());
            }
        }

        txt_progresso.setText(comunicado.obterPosicao() + "/" + comunicado.obterLimite());
        txt_titulo_progresso.setText(comunicado.obterMensagem());
        rlt_lyt_progresso.setVisibility(View.VISIBLE);
    }

    /**
     * Metodo que permite limpar o registo do progresso
     */
    protected void limparProgresso(){

        pgr_bar_progresso_notificacao.setProgress(0);
        txt_progresso.setText(Sintaxe.SEM_TEXTO);
        txt_titulo_progresso.setText(Sintaxe.SEM_TEXTO);
    }

    //----------------------------------------
    //HANDLER (notificacoes para o ui)
    //----------------------------------------


    final Handler handlerNotificacoesUI = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            Notificacao.Comunicado comunicado = (Notificacao.Comunicado) msg.obj;

            switch (comunicado.obterCodigo()) {

                case PROCESSAMENTO_DADOS:

                    imprimirProgresso(comunicado);
                    break;

/*
                case NotificacaoUIIF.REGISTAR_PROGRESSO_DIAGNOSTICO:

                    imprimirRelatorio(R.id.lnr_lyt_progresso_1, R.id.pBar_progresso_1, R.id.txt_view_progresso_1, (Comunicado)msg.obj);
                    break;


                case NotificacaoUIIF.REDE_INDISPONIVEL:

                    DialogInterface.OnClickListener metodo = new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                            falharSeguranca();
                            dialog.cancel();
                        }
                    };

                    MetodosDialogo.dialogoAlerta(contexto, SintaxeIF.TITULO_REDE, SintaxeIF.LIGACAO_REDE_INDISPONIVEL, metodo);
                    break;
*/
                default:
                    break;
            }

            gerirNotificacoesWs(comunicado);

            super.handleMessage(msg);
        }
    };



    /**
     * Metodo que permite gerir as notificaoees e dados provenientes do ws
     * @param comunicado dados da notificacao
     */
    protected abstract void gerirNotificacoesWs(Notificacao.Comunicado comunicado);

}
