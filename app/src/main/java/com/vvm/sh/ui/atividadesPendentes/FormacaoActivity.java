package com.vvm.sh.ui.atividadesPendentes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;

import butterknife.OnClick;

public class FormacaoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formacao);
    }



    /**
     * Metodo que permite iniciar o formulario de formando
     */
    public void initFormando(){

        Intent intent = new Intent(this, FormandoActivity.class);
        startActivity(intent);

        /*
        Intent intent = new Intent(this, IndiceFormando_Activity.class);

        Bundle bundle = new Bundle();
        if(adaptador.obterRegistoSelecionado() != null) {

            bundle.putString(BundleIF.ID_RELATORIO, adaptador.obterRegistoSelecionado().obterId());
        }
        else{
            bundle.putString(BundleIF.ID_RELATORIO, AppIF.SEM_DADOS);
        }

        bundle.putString(BundleIF.ID_ATIVIDADE_PENDENTE, idAtividadePendente);
        intent.putExtras(bundle);
        startActivityForResult(intent, CodigoAtividadeIF.REGISTO_FORMANDO);
        */
    }



    //-------------------
    //Eventos
    //-------------------


    @OnClick(R.id.fab_adicionar_formando)
    public void fab_adicionar_formando_OnClickListener(View view) {
        initFormando();
    }



    @OnClick(R.id.fab_adicionar_acao_formacao)
    public void fab_adicionar_acao_formacao_OnClickListener(View view) {

        Intent intent = new Intent(this, AcaoFormacaoActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivityForResult(intent, 222222);
    }

}
