package com.vvm.sh.ui.contaUtilizador;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.vvm.sh.R;
import com.vvm.sh.autenticacao.Utilizador;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.util.adaptadores.Item;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class PerfilActivity extends BaseActivity {

    @BindView(R.id.img_perfil)
    ImageView img_perfil;


    @BindView(R.id.txt_nome)
    TextView txt_nome;

    @BindView(R.id.txt_numero)
    TextView txt_numero;

    @BindView(R.id.txt_cap)
    TextView txt_cap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);


        subscreverObservadores();
        obterRegistos();
    }


    //------------------------
    //Metodos locais
    //------------------------


    private void obterRegistos(){

        //--TESTE (apagar quando houver dados)

        Utilizador t1 = new Utilizador("3956342", getString(R.string.lorem_ipsum_titulo), "3444565436");


        TextDrawable drawable = TextDrawable.builder().buildRect(t1.obterIniciais(), Color.GREEN);

        img_perfil.setImageDrawable(drawable);

        txt_nome.setText(t1.obterNome());
        txt_numero.setText(t1.obterId());
        txt_cap.setText(t1.obterCap());

        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }


}
