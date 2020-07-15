package com.vvm.sh.ui;

import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.vvm.sh.R;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

public class BaseActivity extends DaggerAppCompatActivity {

    private ProgressBar pgr_bar_carregamento;
    private LinearLayout lnr_lyt_desabilitar;

    @Override
    public void setContentView(int layoutResID) {

        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.activity_content);
        pgr_bar_carregamento = constraintLayout.findViewById(R.id.pgr_bar_carregamento);
        lnr_lyt_desabilitar = constraintLayout.findViewById(R.id.lnr_lyt_desabilitar);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        super.setContentView(constraintLayout);
        ButterKnife.bind(this);

    }


    /**
     * Metodo que permite apresentar o estado de progresso
     * @param visivel true para apresentar ou false para remover a apresentacao
     */
    public void apresentarProgresso(boolean visivel) {

        pgr_bar_carregamento.setVisibility(visivel ? View.VISIBLE : View.INVISIBLE);

        lnr_lyt_desabilitar.setVisibility(visivel ? View.VISIBLE : View.INVISIBLE);

        if(visivel == true) { //desativar interacao do utilizador

            getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
        else{ //ativar interacao do utilizador

            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }


}