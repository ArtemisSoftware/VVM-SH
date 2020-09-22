package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityQuestoesChecklistBinding;

import butterknife.OnClick;

public class QuestoesChecklistActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questoes_checklist);
    }


    private ActivityQuestoesChecklistBinding activityQuestoesChecklistBinding;

//    @Inject
//    ViewModelProviderFactory providerFactory;
//
//
//    private ChecklistViewModel viewModel;
//
//
//    @Override
//    protected void intActivity(Bundle savedInstanceState) {
//
//        viewModel = ViewModelProviders.of(this, providerFactory).get(ChecklistViewModel.class);
//
//        activityRegistoVisitaBinding = (ActivityChecklistBinding) activityBinding;
//        activityRegistoVisitaBinding.setLifecycleOwner(this);
//        activityRegistoVisitaBinding.setViewmodel(viewModel);
//
//        activityRegistoVisitaBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));
//
//        subscreverObservadores();
//
//
//        Bundle bundle = getIntent().getExtras();
//
//        if(bundle != null) {
//
//            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));
//            viewModel.obterAreas(idAtividade);
//        }
//        else{
//            finish();
//        }
//
//    }
//
//    @Override
//    protected int obterLayout() {
//        return R.layout.activity_checklist;
//    }
//
//    @Override
//    protected BaseViewModel obterBaseViewModel() {
//        return viewModel;
//    }
//
//    @Override
//    protected void subscreverObservadores() {
//
//
//        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
//            @Override
//            public void onChanged(Recurso recurso) {
//
//                switch (recurso.status){
//
//                    case SUCESSO:
//
//                        dialogo.sucesso(recurso.messagem);
//                        break;
//
//                    case ERRO:
//
//                        dialogo.erro(recurso.messagem);
//                        break;
//
//                }
//            }
//        });
//    }



    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.fab_nao_aplicavel})
    public void fab_nao_aplicavel_OnClickListener(View view) {

//        Intent intent = new Intent(this, DadosClienteActivity.class);
//        startActivity(intent);
    }

}
