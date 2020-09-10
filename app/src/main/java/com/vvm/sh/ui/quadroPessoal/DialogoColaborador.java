package com.vvm.sh.ui.quadroPessoal;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;

public class DialogoColaborador extends BaseDaggerDialogoPersistenteFragment {


    private static final String ARGUMENTO_ID= "id";


    public DialogoColaborador() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoColaborador newInstance(int id) {
        DialogoColaborador fragmento = new DialogoColaborador();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID, id);
        fragmento.setArguments(args);
        return fragmento;
    }




    @Override
    protected void iniciarDialogo() {

        if(verificarArgumentos(ARGUMENTO_ID) == true){

            //--viewModel.obterDadosAtualizacao(getArguments().getInt(ARGUMENTO_ID));
        }
        else{
            terminarDialogo();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_colaborador;
    }

    @Override
    protected int obterTitulo() {
        return R.string.colaborador;
    }

    @Override
    protected void subscreverObservadores() {

//        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
//            @Override
//            public void onChanged(Recurso recurso) {
//
//                switch (recurso.status){
//
//                    case SUCESSO:
//
//                        dialogo.sucesso(recurso.messagem, listener);
//                        break;
//
//                    case ERRO:
//
//                        dialogo.erro(recurso.messagem);
//                        break;
//
//                    default:
//                        break;
//                }
//
//            }
//        });
    }

    @Override
    protected void clickPositivo() {

//        int idAtividade = getArguments().getInt(ARGUMENTO_ID_ATIVIDADE);
//        int idAnomalia = ((Tipo) binding.spnrAnomalias.getItems().get(binding.spnrAnomalias.getSelectedIndex())).id;
//        String observacao = binding.txtInpObservacao.getText().toString();
//
//        AtividadePendenteResultado atividade = new AtividadePendenteResultado(idAtividade, idAnomalia, observacao);
//        viewModel.gravarAtividade(PreferenciasUtil.obterIdTarefa(getContext()), atividade);

    }
}
