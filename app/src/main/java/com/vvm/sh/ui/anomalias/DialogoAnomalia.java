package com.vvm.sh.ui.anomalias;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoAnomaliaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.baseDados.entidades.AnomaliaResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;

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

                        dialogo.sucesso(recurso.messagem, listener);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                    default:
                        break;
                }

            }
        });
    }


    @Override
    protected void clickPositivo() {

        Tipo tipo = (Tipo) binding.spnrAnomalias.getItems().get(binding.spnrAnomalias.getSelectedIndex());

        AnomaliaResultado anomalia;

        if(getArguments() != null) {
            anomalia = new AnomaliaResultado(PreferenciasUtil.obterIdTarefa(getContext()), getArguments().getInt(ARGUMENTO_ID), tipo.id, binding.txtInpObservacao.getText().toString());
        }
        else{
            anomalia = new AnomaliaResultado(PreferenciasUtil.obterIdTarefa(getContext()), tipo.id, binding.txtInpObservacao.getText().toString());
        }

        viewModel.gravar(anomalia);
    }


}
