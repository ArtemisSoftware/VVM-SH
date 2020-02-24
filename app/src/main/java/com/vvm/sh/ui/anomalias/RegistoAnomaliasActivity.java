package com.vvm.sh.ui.anomalias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.vvm.sh.R;

import butterknife.BindView;

public class RegistoAnomaliasActivity extends AppCompatActivity {

    @BindView(R.id.rcl_registos)
    RecyclerView rcl_registos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registo_anomalias);
    }
}
