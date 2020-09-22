package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.databinding.ActivityChecklistBinding;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores.OnChecklistListener;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import butterknife.OnClick;

public class ChecklistActivity extends AppCompatActivity
    implements OnChecklistListener {


    private ActivityChecklistBinding activityRegistoVisitaBinding;

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


    @Override
    public void OnAreaClick(AreaChecklistResultado registo) {

    }

    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.fab_adicionar_area})
    public void fab_adicionar_area_OnClickListener(View view) {

//        Intent intent = new Intent(this, DadosClienteActivity.class);
//        startActivity(intent);
    }


    @OnClick({R.id.fab_eliminar})
    public void fab_eliminar_OnClickListener(View view) {

//        Intent intent = new Intent(this, DadosClienteActivity.class);
//        startActivity(intent);
    }


}
