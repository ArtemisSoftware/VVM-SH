package com.vvm.sh.carregamentos;

import android.os.Handler;
import android.os.Message;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.util.Notificacao;

import butterknife.BindView;

import static com.vvm.sh.util.Notificacao.Codigo.*;

public abstract class CarregamentoActivity extends BaseActivity {

    @BindView(R.id.pgr_bar_progresso_notificacao)
    ProgressBar pgr_bar_progresso_notificacao;
/*
    @BindView(R.id.txt_titulo)
    TextView txt_titulo;
*/
    @BindView(R.id.txt_progresso)
    TextView txt_progresso;





    //----------------------------------------
    //HANDLER (notificacoes para o ui)
    //----------------------------------------


    final Handler handlerNotificacoesUI = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            Notificacao.Comunicado comunicado = (Notificacao.Comunicado) msg.obj;

            switch (comunicado.obterCodigo()) {

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
