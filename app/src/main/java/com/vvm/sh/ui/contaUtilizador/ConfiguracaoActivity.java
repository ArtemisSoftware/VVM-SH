package com.vvm.sh.ui.contaUtilizador;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class ConfiguracaoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // load settings fragment
        getSupportFragmentManager().beginTransaction().replace(android.R.id.content, /*new MainPreferenceFragment()*/null).commit();
    }
}
