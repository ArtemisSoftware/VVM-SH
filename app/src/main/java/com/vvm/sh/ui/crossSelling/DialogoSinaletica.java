package com.vvm.sh.ui.crossSelling;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoSinaleticaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogFragment;
import com.vvm.sh.baseDados.entidades.CrossSellingResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.util.metodos.MensagensUtil;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.Preferencias;

import javax.inject.Inject;

public class DialogoSinaletica extends BaseDaggerDialogFragment {



    private DialogoSinaleticaBinding binding;


    @Inject
    ViewModelProviderFactory providerFactory;

    private CrossSellingViewModel viewModel;



    private static final String ARGUMENTO_ID_PRODUTO = "idProduto";
    private static final String ARGUMENTO_ID = "id";

    public DialogoSinaletica() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoSinaletica newInstance(int idProduto, int id) {
        DialogoSinaletica frag = new DialogoSinaletica();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID_PRODUTO, idProduto);
        args.putInt(ARGUMENTO_ID, id);
        frag.setArguments(args);
        return frag;
    }



    @Override
    protected void initDialogo(AlertDialog.Builder builder) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(CrossSellingViewModel.class);

        binding = (DialogoSinaleticaBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        if(verificarArgumentos(ARGUMENTO_ID_PRODUTO) == true){
            viewModel.obterSinaletica();
        }
        else{
            terminarDialogo();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_sinaletica;
    }

    @Override
    protected int obterTitulo() {
        return R.string.sinaletica;
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

    @Override
    protected void clickPositivo() {

        int idProduto = getArguments().getInt(ARGUMENTO_ID_PRODUTO);
        int id = getArguments().getInt(ARGUMENTO_ID);
        Tipo dimensao =  (Tipo) binding.spnrDimensao.getItems().get(binding.spnrDimensao.getSelectedIndex());
        Tipo tipo =  (Tipo) binding.spnrTipos.getItems().get(binding.spnrTipos.getSelectedIndex());

        CrossSellingResultado registo = new CrossSellingResultado(Preferencias.obterIdTarefa(getContext()), idProduto, id, dimensao, tipo);

        viewModel.gravar(registo);
    }





}
