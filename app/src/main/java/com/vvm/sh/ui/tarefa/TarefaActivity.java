package com.vvm.sh.ui.tarefa;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityTarefaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class TarefaActivity extends BaseDaggerActivity
        /*implements OnItemListener, DialogoEmail.DialogEmailListener*/ {

    private ActivityTarefaBinding activityTarefaBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private TarefaViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TarefaViewModel.class);

        activityTarefaBinding = (ActivityTarefaBinding) activityBinding;
        activityTarefaBinding.setLifecycleOwner(this);
        activityTarefaBinding.setViewmodel(viewModel);

        subscreverObservadores();

        viewModel.obterOpcoesCliente(Preferencias.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_tarefa;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }


//
//    @BindView(R.id.rcl_opcoes_cliente)
//    RecyclerView rcl_opcoes_cliente;
//
//    private OpcaoClienteRecyclerAdapter opcaoClienteRecyclerAdapter;
//
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_tarefa);
//
//        Toolbar myToolbar = (Toolbar) findViewById(R.id.z_toolbar);
//        setSupportActionBar(myToolbar);
//
//        getSupportActionBar().setHomeButtonEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//
//        iniciarAtividade();
//        subscreverObservadores();
//        obterRegistos();
//
//    }
//
//
//
//
//
//
//    @Override
//    public void gravarEmail(String email, int estado) {
//        //textViewUsername.setText(username);
//        //textViewPassword.setText(password);
//    }
//
//    //------------------------
//    //Metodos locais
//    //------------------------
//
//


//
//
//    @OnClick(R.id.crd_atividades_pendentes)
//    public void crd_atividades_pendentes_OnClickListener(View view) {
//        Intent intent = new Intent(this, AtividadesPendentesActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
//        startActivity(intent);
//    }
//
//    @OnClick(R.id.crd_anomalias)
//    public void crd_anomalias_OnClickListener(View view) {
//        Intent intent = new Intent(this, AnomaliasActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
//        startActivity(intent);
//    }
//
//    @OnClick(R.id.crd_atividades_executadas)
//    public void crd_atividades_executadas_OnClickListener(View view) {
//        Intent intent = new Intent(this, AtividadesExecutadasActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
//        startActivity(intent);
//    }
//
//
//    @OnClick(R.id.crd_conta_corrente)
//    public void crd_conta_corrente_OnClickListener(View view) {
//        Intent intent = new Intent(this, ContaCorrenteActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
//        startActivity(intent);
//    }
//
//
//    @OnClick(R.id.crd_ocorrencias)
//    public void crd_ocorrencias_OnClickListener(View view) {
//        Intent intent = new Intent(this, OcorrenciasActivity.class);
//        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
//        startActivity(intent);
//    }
//
//    //---------------------
//    //Eventos
//    //---------------------
//
//
//    @Override
//    public void onItemClick(int position) {
//
//        Intent intent = null;
//
//        switch (opcaoClienteRecyclerAdapter.obterRegisto(position).obterId()){
//
//            case OpcaoClienteRecyclerAdapter.OPCAO_INFORMACAO:
//
//                intent = new Intent(this, InformacaoActivity.class);
//                break;
//
//            case OpcaoClienteRecyclerAdapter.OPCAO_CROSS_SELLING:
//
//                intent = new Intent(this, CrossSellingActivity.class);
//                break;
//
//            case OpcaoClienteRecyclerAdapter.OPCAO_SINISTRALIDADE:
//
//                intent = new Intent(this, SinistralidadeActivity.class);
//                break;
//
//            case OpcaoClienteRecyclerAdapter.OPCAO_EXTINTORES:
//
//                intent = new Intent(this, ExtintoresActivity.class);
//                break;
//
//            case OpcaoClienteRecyclerAdapter.OPCAO_EMAIL:
//
//                DialogoEmail dialogo = new DialogoEmail();
//                dialogo.show(getSupportFragmentManager(), "example dialog");
//                break;
//
//            case OpcaoClienteRecyclerAdapter.OPCAO_ANOMALIA:
//
//                intent = new Intent(this, RegistoAnomaliasActivity.class);
//                break;
//
//            default:
//                break;
//        }
//
//        if(intent != null) {
//            //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
//            startActivity(intent);
//        }
//    }

    /*https://www.11zon.com/android/android_cardview.php*/
}
