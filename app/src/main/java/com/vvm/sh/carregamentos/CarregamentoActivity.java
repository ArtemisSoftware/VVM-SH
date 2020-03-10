package com.vvm.sh.carregamentos;

import android.widget.ProgressBar;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;

import butterknife.BindView;

public abstract class CarregamentoActivity extends BaseActivity {

    @BindView(R.id.pgr_bar_progresso_notificacao)
    ProgressBar pgr_bar_progresso_notificacao;
/*
    @BindView(R.id.txt_titulo)
    TextView txt_titulo;
*/
    @BindView(R.id.txt_progresso)
    TextView txt_progresso;

}
