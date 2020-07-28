package com.vvm.sh.ui.ocorrencias;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityOcorrenciaRegistarBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.ocorrencias.modelos.OcorrenciaResultado;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.metodos.Preferencias;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

import butterknife.BindView;
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
            viewModel.obterOcorrencia(Preferencias.obterIdTarefa(this), bundle.getInt(getString(R.string.argumento_id)));
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

    }


    //-----------------
    //Eventos
    //-----------------


    @OnCheckedChanged(R.id.chk_box_fiscalizado)
    void chk_box_fiscalizado_Selected(CompoundButton button, boolean checked) {

        if(checked == true){
            activityOcorrenciaRegistarBinding.spnrDias.setVisibility(View.GONE);
            activityOcorrenciaRegistarBinding.txtInpLytDias.setVisibility(View.VISIBLE);
        }
        else{
            activityOcorrenciaRegistarBinding.spnrDias.setVisibility(View.VISIBLE);
            activityOcorrenciaRegistarBinding.txtInpLytDias.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.fab_gravar)
    public void fab_calendario_OnClickListener(View view) {

        OcorrenciaResultado ocorrencia;
        Bundle bundle = getIntent().getExtras();
        int id = bundle.getInt(getString(R.string.argumento_id));



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
