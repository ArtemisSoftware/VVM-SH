package com.vvm.sh.ui.agenda;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.atividadesExecutadas.AtividadesExecutadasActivity;
import com.vvm.sh.ui.cliente.InformacaoActivity;
import com.vvm.sh.ui.contaCorrente.ContaCorrenteActivity;

import butterknife.OnClick;

public class TarefaActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarefa);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.z_toolbar);
        setSupportActionBar(myToolbar);

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @OnClick(R.id.crd_informacao)
    public void crd_informacao_OnClickListener(View view) {
        Intent intent = new Intent(this, InformacaoActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);
    }

    @OnClick(R.id.crd_atividades_executadas)
    public void crd_atividades_executadas_OnClickListener(View view) {
        Intent intent = new Intent(this, AtividadesExecutadasActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);
    }


    @OnClick(R.id.crd_conta_corrente)
    public void crd_conta_corrente_OnClickListener(View view) {
        Intent intent = new Intent(this, ContaCorrenteActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);
    }
}
