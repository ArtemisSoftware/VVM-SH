package com.vvm.sh.ui.contaUtilizador;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.vvm.sh.R;
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

        TextDrawable drawable = TextDrawable.builder().buildRect("LI", Color.GREEN);

        img_perfil.setImageDrawable(drawable);


        /*
        List<Item> t1 = new ArrayList<>();
        t1.add(new Tarefa(1, "Tarefa numero 1", "SH"));
        t1.add(new Tarefa(2, "Tarefa numero 2", "SA"));

        tarefaRecyclerAdapter.fixarRegistos(t1);
*/
        //TODO: chamar metodo do viewmodel
    }

    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }


}
