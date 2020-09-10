package com.vvm.sh.ui.quadroPessoal;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.databinding.ActivityColaboradorBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class ColaboradorActivity extends BaseDaggerActivity
        implements Validator.ValidationListener{


    private ActivityColaboradorBinding activityColaboradorBinding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private QuadroPessoalViewModel viewModel;


    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_nome)
    TextInputEditText txt_inp_nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_data_nascimento)
    TextInputEditText txt_inp_data_nascimento;

    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_data_admissao)
    TextInputEditText txt_inp_data_admissao;

    @NotEmpty(message = "Preenchimento obrigatório")
    @BindView(R.id.txt_inp_data_admissao_funcao)
    TextInputEditText txt_inp_data_admissao_funcao;


    private Validator validador;

    @Override
    protected void intActivity(Bundle savedInstanceState) {

        subscreverObservadores();

        validador = new Validator(this);
        validador.setValidationListener(this);


        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_colaborador));
            //--viewModel.obterFormando(idFormando);
            //--activityFormandoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_colaborador;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return null;
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
//                        dialogo.sucesso(recurso.messagem, listenerActivity);
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
    }



    //-----------------------
    //Metodos locais
    //-----------------------

    /**
     * Metodo que permite ativar a validacao de campos especificos
     * @param ativar true para ativar ou false caso contrario
     */
    private void ativarValidacao(boolean ativar){

        //binding.txtInpDataExecucao.setEnabled(ativar);
    }

    //-----------------------
    //EVENTOS
    //-----------------------


    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {


        //adicionar esta validadcao
//    			if(((AutoCompleteTextView) vista.findViewById(R.id.at_cmp_txt_profissao)).getText().toString().equals(AppIF.SEM_TEXTO) == false){
//
//        valido = MetodosValidacao.validarRotuloTexto((TextView) vista.findViewById(R.id.txt_view_tipificacao_profissao), (TextView) vista.findViewById(R.id.txt_view_tipificacao_profissao_erro)) & valido;
//    }

        ativarValidacao(true);
        validador.validate();
    }





    @Override
    public void onValidationSucceeded() {

        ativarValidacao(false);


        ColaboradorResultado resultado;

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_colaborador));

//            String nome =
//
//
//                    ((EditText) vista.findViewById(R.id.edit_txt_nome)).getText().toString(),
//                    ((TextView) vista.findViewById(R.id.txt_view_data_nascimento)).getText().toString(),
//                    ((TextView) vista.findViewById(R.id.txt_view_data_admissao)).getText().toString(),
//                    ((TextView) vista.findViewById(R.id.txt_view_data_admissao_funcao)).getText().toString(),
//                    ((AutoCompleteTextView) vista.findViewById(R.id.at_cmp_txt_profissao)).getText().toString(),
//                    ((TextView) vista.findViewById(R.id.txt_view_id_categoria)).getText().toString(),
//                    ((SpinnerAdaptador)((Spinner) vista.findViewById(R.id.spnr_genero)).getAdapter()).obterDescricao(),
//                    ((SpinnerAdaptador)((Spinner) vista.findViewById(R.id.spnr_moradas)).getAdapter()).obterIdItem(),
//                    ((AutoCompleteTextView) vista.findViewById(R.id.at_cmp_txt_posto)).getText().toString());
//
//
//
//                resultado = new ColaboradorResultado(id);
//
//                viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), resultado);
        }
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);

            // Display error messages ;)
            if (view instanceof TextInputEditText) {
                ((TextInputEditText) view).setError(message);
            }
        }

        ativarValidacao(false);
    }


}
