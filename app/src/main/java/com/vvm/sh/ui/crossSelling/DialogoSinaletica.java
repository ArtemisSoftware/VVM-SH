package com.vvm.sh.ui.crossSelling;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoSinaleticaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.crossSelling.adaptadores.OnCrossSellingListener;
import com.vvm.sh.ui.BaseDaggerDialogFragment;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.constantes.Sintaxe;

import javax.inject.Inject;

public class DialogoSinaletica extends BaseDaggerDialogFragment {



    private DialogoSinaleticaBinding binding;


    @Inject
    ViewModelProviderFactory providerFactory;

    private CrossSellingViewModel viewModel;


    private OnCrossSellingListener listener;

    private static final String ARGUMENTO_ID_PRODUTO = "idProduto";


    public DialogoSinaletica() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoSinaletica newInstance(int idProduto) {
        DialogoSinaletica frag = new DialogoSinaletica();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID_PRODUTO, idProduto);
        frag.setArguments(args);
        return frag;
    }



//    @Override
//    protected void initDialogo(AlertDialog.Builder builder) {
//
//        listener = (OnCrossSellingListener) getContext();

//
//        builder.setPositiveButton(Sintaxe.Opcoes.GRAVAR,  new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//
//
//
//
//
//                listener.OnGravarSinaletica(idProduto, dimensao, tipo);
//            }
//        });
//
//        builder.setNegativeButton(Sintaxe.Opcoes.CANCELAR, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                terminarDialogo();
//            }
//        });
//
//

//
//    }

    @Override
    protected void initDialogo(AlertDialog.Builder builder) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(CrossSellingViewModel.class);

        binding = (DialogoSinaleticaBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        if(verificarArgumentos(ARGUMENTO_ID_PRODUTO) == true){
            viewModel.obterProdutos();
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

    }

    @Override
    protected void clickPositivo() {

        int idProduto = getArguments().getInt(ARGUMENTO_ID_PRODUTO);
        Tipo dimensao =  (Tipo) binding.spnrDimensao.getSelectedItem();
        Tipo tipo =  (Tipo) binding.spnrTipos.getSelectedItem();
    }





}
