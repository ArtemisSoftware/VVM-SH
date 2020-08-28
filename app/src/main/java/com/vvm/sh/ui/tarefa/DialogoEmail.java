package com.vvm.sh.ui.tarefa;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoEmailBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.baseDados.entidades.EmailResultado;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Inject;

import butterknife.BindView;

public class DialogoEmail extends BaseDaggerDialogoPersistenteFragment {


    private DialogoEmailBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private TarefaViewModel viewModel;


    @BindView(R.id.txt_inp_email)
    TextInputEditText txt_inp_email;



    public DialogoEmail() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoEmail newInstance() {
        DialogoEmail fragmento = new DialogoEmail();
        return fragmento;
    }



    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TarefaViewModel.class);

        binding = (DialogoEmailBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);

        viewModel.obterEmail(PreferenciasUtil.obterIdTarefa(getContext()));
    }


    @Override
    protected int obterLayout() {
        return R.layout.dialogo_email;
    }

    @Override
    protected int obterTitulo() {
        return R.string.email;
    }


    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        dialogo.sucesso(listener);
                        break;

                    default:
                        break;
                }

            }
        });

    }


    @Override
    protected void clickPositivo() {

        if(validarEmail() == true) {
            String endereco = binding.txtInpEmail.getText().toString();
            Tipo autorizacao = (Tipo) binding.spnrEmail.getItems().get(binding.spnrEmail.getSelectedIndex());

            EmailResultado email = new EmailResultado(PreferenciasUtil.obterIdTarefa(getContext()), endereco, autorizacao);

            viewModel.gravarEmail(email);
        }
    }


    //-----------------------
    //Metodos locais
    //-----------------------



    /**
     * Metodo que valida o email
     * @return true caso os dados sejam válidos e false caso contrário
     */
    private boolean validarEmail(){

        boolean valido = true;

        String opcao = ((Tipo) binding.spnrEmail.getItems().get(binding.spnrEmail.getSelectedIndex())).descricao;

        if(opcao.equals(Sintaxe.Opcoes.AUTORIZADO) == true || opcao.equals(Sintaxe.Opcoes.NAO_AUTORIZADO) == true){

            if(txt_inp_email.getText().toString().equals(Sintaxe.SEM_TEXTO)
                    ||
                    txt_inp_email.getText().toString().contains(Sintaxe.ARROBA) == false){

                valido = false & valido;
            }
        }

        if(valido == false){
            txt_inp_email.setError(getString(R.string.email_endereco_invalido));
        }
        return valido;
    }


}