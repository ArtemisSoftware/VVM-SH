package com.vvm.sh.ui.ocorrencias;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityOcorrenciaRegistarBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.baseDados.entidades.OcorrenciaResultado;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaRegisto;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.MensagensUtil;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class RegistarOcorrenciaActivity extends BaseDaggerActivity {


    private ActivityOcorrenciaRegistarBinding activityOcorrenciaRegistarBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private OcorrenciasViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(OcorrenciasViewModel.class);

        activityOcorrenciaRegistarBinding = (ActivityOcorrenciaRegistarBinding) activityBinding;
        activityOcorrenciaRegistarBinding.setLifecycleOwner(this);
        activityOcorrenciaRegistarBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            viewModel.obterOcorrencia(Preferencias.obterIdTarefa(this), bundle.getInt(getString(R.string.argumento_id)), bundle.getInt(getString(R.string.argumento_id_tipo)));
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_ocorrencia_registar;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        //TODO: Completar metodo
                        MensagensUtil.sucesso();
                        finish();
                        break;

                    default:
                        break;
                }

            }
        });

    }


    //-----------------
    //Eventos
    //-----------------


    @OnCheckedChanged(R.id.chk_box_fiscalizado)
    void chk_box_fiscalizado_Selected(CompoundButton button, boolean checked) {

        OcorrenciaRegisto ocorrencia = viewModel.ocorrencia.getValue();

        if(checked == true & ocorrencia.existeDetalhe() == true){
            activityOcorrenciaRegistarBinding.spnrDias.setVisibility(View.VISIBLE);
            activityOcorrenciaRegistarBinding.txtDias.setVisibility(View.VISIBLE);
            activityOcorrenciaRegistarBinding.txtInpLytDias.setVisibility(View.GONE);
        }
        else if(checked == true & ocorrencia.existeDetalhe() == false){
            activityOcorrenciaRegistarBinding.spnrDias.setVisibility(View.GONE);
            activityOcorrenciaRegistarBinding.txtDias.setVisibility(View.GONE);
            activityOcorrenciaRegistarBinding.txtInpLytDias.setVisibility(View.VISIBLE);
        }
        else{
            activityOcorrenciaRegistarBinding.spnrDias.setVisibility(View.GONE);
            activityOcorrenciaRegistarBinding.txtDias.setVisibility(View.GONE);
            activityOcorrenciaRegistarBinding.txtInpLytDias.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {

        OcorrenciaResultado ocorrencia;
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt(getString(R.string.argumento_id_tipo));

        String dias = activityOcorrenciaRegistarBinding.txtInpDias.getText().toString();
        boolean fiscalizado = activityOcorrenciaRegistarBinding.chkBoxFiscalizado.isChecked();
        String observacao = activityOcorrenciaRegistarBinding.txtInpObservacao.getText().toString();


        if(fiscalizado == true){
            ocorrencia = new OcorrenciaResultado(Preferencias.obterIdTarefa(this), id, observacao, fiscalizado, dias);
        }
        else{

            dias = ((Tipo) activityOcorrenciaRegistarBinding.spnrDias.getItems().get(activityOcorrenciaRegistarBinding.spnrDias.getSelectedIndex())).descricao;
            ocorrencia = new OcorrenciaResultado(Preferencias.obterIdTarefa(this), id, observacao, fiscalizado, dias);
        }


        viewModel.gravar(ocorrencia);
    }

}
