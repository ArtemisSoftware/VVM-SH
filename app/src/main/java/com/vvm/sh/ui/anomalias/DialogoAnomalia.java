package com.vvm.sh.ui.anomalias;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Select;
import com.skydoves.powerspinner.IconSpinnerAdapter;
import com.skydoves.powerspinner.IconSpinnerItem;
import com.skydoves.powerspinner.PowerSpinnerView;
import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoAnomaliaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.ui.BaseDialogFragment;
import com.vvm.sh.ui.anomalias.modelos.AnomaliaResultado;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesViewModel;
import com.vvm.sh.ui.atividadesPendentes.DialogoAtividadePendenteExecutada;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.MensagensUtil;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.Preferencias;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class DialogoAnomalia extends BaseDaggerDialogoPersistenteFragment{



    @BindView(R.id.spnr_anomalias)
    MaterialSpinner spnr_anomalias;


    private DialogoAnomaliaBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private AnomaliasViewModel viewModel;



    private static final String ARGUMENTO_ID= "id";


    public DialogoAnomalia() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoAnomalia newInstance(int id) {
        DialogoAnomalia fragmento = new DialogoAnomalia();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID, id);
        fragmento.setArguments(args);
        return fragmento;
    }


    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AnomaliasViewModel.class);
        binding = (DialogoAnomaliaBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);

        if(getArguments() != null) {
            viewModel.obterAnomalia(getArguments().getInt(ARGUMENTO_ID));
        }
        else{
            viewModel.obterAnomalia(0);
        }
    }

    @Override
    protected void clickPositivo() {

        Tipo tipo = (Tipo) binding.spnrAnomalias.getItems().get(binding.spnrAnomalias.getSelectedIndex());

        AnomaliaResultado anomalia = new AnomaliaResultado(Preferencias.obterIdTarefa(getContext()), tipo.id, binding.txtInpObservacao.getText().toString());

        viewModel.gravar(anomalia);
    }


    @Override
    protected int obterLayout() {
        return R.layout.dialogo_anomalia;
    }

    @Override
    protected int obterTitulo() {
        return R.string.anomalia;
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
                        terminarDialogo();
                        break;

                    default:
                        break;
                }

            }
        });
    }



}
