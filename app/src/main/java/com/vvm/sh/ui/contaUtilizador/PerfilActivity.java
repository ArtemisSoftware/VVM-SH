package com.vvm.sh.ui.contaUtilizador;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.ui.autenticacao.modelos.Utilizador;
import com.vvm.sh.ui.BaseActivity;

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

        Utilizador t1 = new Utilizador("3956342", getString(R.string.lorem_ipsum_titulo), "3444565436", "fddfd");

//        txt_nome.setText(t1.obterNome());
//        txt_numero.setText(t1.obterId());
//        txt_cap.setText(t1.obterCap());

        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }


}
