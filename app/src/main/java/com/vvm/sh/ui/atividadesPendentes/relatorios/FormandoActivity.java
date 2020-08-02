package com.vvm.sh.ui.atividadesPendentes.relatorios;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityFormandoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.AssinaturaActivity;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormacaoViewModel;
import com.vvm.sh.ui.atividadesPendentes.relatorios.Formando;
import com.vvm.sh.ui.opcoes.modelos.Tipo;
import com.vvm.sh.util.BottomNavigationBehavior;
import com.vvm.sh.util.MensagensUtil;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class FormandoActivity extends BaseDaggerActivity implements Validator.ValidationListener{


    private ActivityFormandoBinding activityFormandoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private FormacaoViewModel viewModel;


    private Validator validador;


    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_nome)
    TextInputEditText txt_inp_nome;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_identificacao)
    TextInputEditText txt_inp_identificacao;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_data_nascimento)
    TextInputEditText txt_inp_data_nascimento;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_niss)
    TextInputEditText txt_inp_niss;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_naturalidade)
    TextInputEditText txt_inp_naturalidade;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_nacionalidade)
    TextInputEditText txt_inp_nacionalidade;




    @Override
    protected void intActivity(Bundle savedInstanceState) {

        validador = new Validator(this);
        validador.setValidationListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(FormacaoViewModel.class);

        activityFormandoBinding = (ActivityFormandoBinding) activityBinding;
        activityFormandoBinding.setLifecycleOwner(this);
        //activityFormandoBinding.setListener(this);
        activityFormandoBinding.setViewmodel(viewModel);

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int idFormando = bundle.getInt(getString(R.string.argumento_id_formando));

            if(idFormando != 0){
                viewModel.obterFormando(idFormando);
            }
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_formando;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {


                switch (recurso.status){

                    case SUCESSO:

                        //TODO: mensagem de sucesso
                        MensagensUtil.sucesso();
                        finish();
                        break;

                    case ERRO:

                        //TODO: mensagem de erro
                        break;

                }
            }
        });
    }


    //---------------------
    //Metodos locais
    //---------------------


    /**
     * Metodo que permite ativar a validacao de campos especificos
     * @param ativar true para ativar ou false caso contrario
     */
    private void ativarValidacao(boolean ativar){

        activityFormandoBinding.txtInpDataNascimento.setEnabled(ativar);
    }


    //---------------------
    //Eventos
    //---------------------





    @Override
    public void onValidationSucceeded() {

        ativarValidacao(false);

        Bundle bundle = getIntent().getExtras();
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));


        String nome = activityFormandoBinding.txtInpNome.getText().toString();

        Tipo genero = (Tipo) activityFormandoBinding.spnrGenero.getItems().get(activityFormandoBinding.spnrGenero.getSelectedIndex());

        String identificacao = activityFormandoBinding.txtInpIdentificacao.getText().toString();
        Date dataNascimento = DatasUtil.converterString(activityFormandoBinding.txtInpDataNascimento.getText().toString(), DatasUtil.FORMATO_DD_MM_YYYY);
        String niss = activityFormandoBinding.txtInpNiss.getText().toString();
        String naturalidade = activityFormandoBinding.txtInpNaturalidade.getText().toString();
        String nacionalidade = activityFormandoBinding.txtInpNacionalidade.getText().toString();


        Formando formando;

        int idFormando = bundle.getInt(getString(R.string.argumento_id_formando));

        if(idFormando == 0){
            formando = new Formando(idAtividade, nome, identificacao, genero.codigo, niss, dataNascimento, naturalidade, nacionalidade);
        }
        else{
            formando = new Formando(idAtividade, idFormando, nome, identificacao, genero.codigo, niss, dataNascimento, naturalidade, nacionalidade);
        }


        viewModel.gravar(formando);
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



    //-------------------
    //Eventos
    //-------------------


    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {
        ativarValidacao(true);
        validador.validate();
    }


    @OnClick(R.id.img_assinatura)
    public void img_assinatura_OnClickListener(View view) {

        //if( ((IndiceFormando_Activity) contexto).prontoAssinar() == true){

        Intent intent = new Intent(this, AssinaturaActivity.class);
        //intent.putExtra(BundleIF.ID_TAREFA, acessoBDFormando.obterIdTarefa(idAtividade));
        //intent.putExtra(BundleIF.ID_FORMANDO, idRelatorio);
        //intent.putExtra(BundleIF.ORIGEM, IdentificadoresIF.ORIGEM_FORMANDO);
       startActivityForResult(intent, /*CodigoAtividadeIF.REGISTO_ASSINATURA*/ 11);
        /*}
        else{
            MetodosDialogo.dialogoAlerta(contexto, SintaxeIF.TITULO_ASSINATURA, "O relatório só pode ser assinado quando estiver completo.");
        }*/

    }

}
