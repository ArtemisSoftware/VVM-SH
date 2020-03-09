package com.vvm.sh.carregamentos;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class AtualizacaoAppActivity extends BaseActivity {

    @BindView(R.id.pgr_bar_progresso_notificacao)
    ProgressBar pgr_bar_progresso_notificacao;

    @BindView(R.id.txt_progresso)
    TextView txt_progresso;

    @BindView(R.id.txt_atualizacoes)
    TextView txt_atualizacoes;

    @BindView(R.id.txt_versao_app)
    TextView txt_versao_app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atualizacao_app);


        pgr_bar_progresso_notificacao.setProgress(30);

        txt_progresso.setText(1 + "/"+ pgr_bar_progresso_notificacao.getMax());
    }


    //----------------------
    //Eventos
    //----------------------

    @OnClick(R.id.btn_terminar)
    public void btn_terminar_OnClickListener(View view) {



    }

}
