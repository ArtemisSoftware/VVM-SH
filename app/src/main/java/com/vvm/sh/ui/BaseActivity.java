package com.vvm.sh.ui;

import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.vvm.sh.R;

import butterknife.ButterKnife;

public class BaseActivity extends AppCompatActivity {

    private ProgressBar pgr_bar_carregamento;

    @Override
    public void setContentView(int layoutResID) {

        ConstraintLayout constraintLayout = (ConstraintLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        FrameLayout frameLayout = constraintLayout.findViewById(R.id.activity_content);
        pgr_bar_carregamento = constraintLayout.findViewById(R.id.pgr_bar_carregamento);

        getLayoutInflater().inflate(layoutResID, frameLayout, true);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);

        super.setContentView(constraintLayout);
        ButterKnife.bind(this);

    }

    public void apresentarProgresso(boolean visible) {
        pgr_bar_carregamento.setVisibility(visible ? View.VISIBLE : View.INVISIBLE);
    }


}