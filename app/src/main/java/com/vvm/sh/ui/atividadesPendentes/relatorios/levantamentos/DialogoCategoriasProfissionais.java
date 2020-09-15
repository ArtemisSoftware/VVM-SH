package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.databinding.DialogoCategoriaProfissionalBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.util.Recurso;

import javax.inject.Inject;

public class DialogoCategoriasProfissionais extends BaseDaggerDialogoPersistenteFragment {


    private DialogoCategoriaProfissionalBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private LevantamentosViewModel viewModel;


    private static final String ARGUMENTO_ID = "id";
    private static final String ARGUMENTO_HOMENS = "homens";
    private static final String ARGUMENTO_MULHERES = "mulheres";



    public DialogoCategoriasProfissionais() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoCategoriasProfissionais newInstance(int id, int homens, int mulheres) {
        DialogoCategoriasProfissionais fragmento = new DialogoCategoriasProfissionais();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID, id);
        args.putInt(ARGUMENTO_HOMENS, homens);
        args.putInt(ARGUMENTO_MULHERES, mulheres);
        fragmento.setArguments(args);
        return fragmento;
    }




    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(LevantamentosViewModel.class);
        binding = (DialogoCategoriaProfissionalBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        if(verificarArgumentos(ARGUMENTO_ID) == true){

            binding.txtInpHomens.setText(getArguments().getInt(ARGUMENTO_HOMENS));
            binding.txtInpMulheres.setText(getArguments().getInt(ARGUMENTO_MULHERES));
        }
        else{
            terminarDialogo();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_categoria_profissional;
    }

    @Override
    protected int obterTitulo() {
        return R.string.categoria_profissional;
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

        int id = getArguments().getInt(ARGUMENTO_ID);
        int homens = 0;
        int mulheres = 0;

        try{
            homens = Integer.parseInt(binding.txtInpHomens.getText().toString());
        }
        catch (NumberFormatException e){
            homens = 0;
        }

        try{
            mulheres = Integer.parseInt(binding.txtInpMulheres.getText().toString());
        }
        catch (NumberFormatException e){
            mulheres = 0;
        }


        CategoriaProfissionalResultado registo = new CategoriaProfissionalResultado(id, homens, mulheres);

        viewModel.atualizarCategoriaProfissional(registo);

    }
}
