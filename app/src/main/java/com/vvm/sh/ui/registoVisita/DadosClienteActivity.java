package com.vvm.sh.ui.registoVisita;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.RegistoVisitaResultado;
import com.vvm.sh.databinding.ActivityDadosClienteBinding;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class DadosClienteActivity extends AppCompatActivity
        implements Validator.ValidationListener{


        private ActivityDadosClienteBinding activityDadosClienteBinding;


//        @Inject
//        ViewModelProviderFactory providerFactory;
//
//
//        private RegistoVisitaViewModel viewModel;


    private Validator validador;



    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_recebido_por)
    TextInputEditText txt_inp_recebido_por;


    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_funcao)
    TextInputEditText txt_inp_funcao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dados_cliente);
    }


//    @Override
//    protected void intActivity(Bundle savedInstanceState) {
//
//        validador = new Validator(this);
//        validador.setValidationListener(this);
//
//        viewModel = ViewModelProviders.of(this, providerFactory).get(RegistoVisitaViewModel.class);
//
//        activityDadosClienteBinding = (ActivityDadosClienteBinding) activityDadosClienteBinding;
//        activityDadosClienteBinding.setLifecycleOwner(this);
//        activityDadosClienteBinding.setViewmodel(viewModel);
//
//
//        activityAvaliacaoIluminacaoRegistoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));
//
//        subscreverObservadores();
//
//
//        viewModel.obterDadosCliente(PreferenciasUtil.obterIdTarefa(this));
//    }
//


//
//    @Override
//    protected int obterLayout() {
//        return R.layout.activity_dados_cliente;
//    }
//
//    @Override
//    protected BaseViewModel obterBaseViewModel() {
//        return viewModel;
//    }
//
//
//
//    @Override
//    protected void subscreverObservadores() {
//
//        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
//            @Override
//            public void onChanged(Recurso recurso) {
//
//                switch (recurso.status){
//
//                    case SUCESSO:
//
//                        avancarRelatorio();
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
//    }
//

    //-------------------
    //EVENTOS
    //-------------------

    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {
        validador.validate();
    }


    @Override
    public void onValidationSucceeded() {

        String funcao = activityDadosClienteBinding.txtInpFuncao.getText().toString();
        String recebidoPor = activityDadosClienteBinding.txtInpRecebidoPor.getText().toString();
        String observacao = activityDadosClienteBinding.txtInpObservacao.getText().toString();

        RegistoVisitaResultado resultado = new RegistoVisitaResultado(PreferenciasUtil.obterIdTarefa(this), recebidoPor, funcao);

        if(observacao.equals(Sintaxe.SEM_TEXTO) == false){
            resultado.observacao = observacao;
        }

        //viewmodel.gravar(resultado);

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
    }
}
