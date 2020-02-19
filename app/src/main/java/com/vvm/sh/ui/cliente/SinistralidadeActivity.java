package com.vvm.sh.ui.cliente;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;

import com.google.android.material.textfield.TextInputLayout;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;

import butterknife.BindView;

public class SinistralidadeActivity extends BaseActivity {


    @BindView(R.id.txt_inp_acidentes_trabalho)
    TextInputLayout txt_inp_acidentes_trabalho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sinistralidade);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null)
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txt_inp_acidentes_trabalho.getEditText().setText("lolo");

    }
}
