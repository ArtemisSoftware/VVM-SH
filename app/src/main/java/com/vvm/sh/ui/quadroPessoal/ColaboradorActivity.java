package com.vvm.sh.ui.quadroPessoal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ColaboradorResultado;
import com.vvm.sh.baseDados.entidades.Morada;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityColaboradorBinding;
import com.vvm.sh.databinding.ActivityQuadroPessoalBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.pesquisa.Pesquisa;
import com.vvm.sh.ui.pesquisa.PesquisaActivity;
import com.vvm.sh.util.base.BaseDatePickerDialog;
import com.vvm.sh.util.base.BaseTimePickerDialog;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.DatasUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.ArrayList;
import java.util.Date;
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

        viewModel = ViewModelProviders.of(this, providerFactory).get(QuadroPessoalViewModel.class);

        activityColaboradorBinding = (ActivityColaboradorBinding) activityBinding;
        activityColaboradorBinding.setLifecycleOwner(this);
        activityColaboradorBinding.setViewmodel(viewModel);

        activityColaboradorBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_colaborador));
            viewModel.obterColaborador(PreferenciasUtil.obterIdTarefa(this), id);
        }
        else{
            viewModel.obterColaborador(PreferenciasUtil.obterIdTarefa(this));
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_colaborador;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
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

        activityColaboradorBinding.txtInpDataNascimento.setEnabled(ativar);
        activityColaboradorBinding.txtInpDataAdmissao.setEnabled(ativar);
        activityColaboradorBinding.txtInpDataAdmissaoFuncao.setEnabled(ativar);
    }

    //-----------------------
    //EVENTOS
    //-----------------------



    @OnClick(R.id.crl_btn_data_nascimento)
    public void crl_btn_data_nascimento_OnClickListener(View view) {

        BaseDatePickerDialog dialogo = new BaseDatePickerDialog(activityColaboradorBinding.txtInpDataNascimento);
        dialogo.obterDatePickerDialog().show(getSupportFragmentManager(), "Datepickerdialog");
    }

    @OnClick(R.id.crl_btn_data_admissao)
    public void crl_btn_data_admissao_OnClickListener(View view) {

        BaseTimePickerDialog dialogo = new BaseTimePickerDialog(activityColaboradorBinding.txtInpDataAdmissao);
        dialogo.show(getSupportFragmentManager(), "Timepickerdialog");

    }

    @OnClick(R.id.crl_btn_data_admissao_funcao)
    public void crl_btn_data_admissao_funcao_OnClickListener(View view) {

        BaseTimePickerDialog dialogo = new BaseTimePickerDialog(activityColaboradorBinding.txtInpDataAdmissaoFuncao);
        dialogo.show(getSupportFragmentManager(), "Timepickerdialog");
    }


    @OnClick(R.id.crl_btn_categoria_profissional)
    public void crl_btn_categoria_profissional_OnClickListener(View view) {

        Pesquisa pesquisa = new Pesquisa(false, TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS);

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);

        Intent intent = new Intent(this, PesquisaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA);
    }



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
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Identificadores.CodigoAtividade.PESQUISA) {

            if(resultCode == RESULT_OK){

                ArrayList<Integer> resultado = data.getIntegerArrayListExtra(getString(R.string.resultado_pesquisa));

                //viewModel.fixarCategoriasProfissionais(resultado);
            }
        }
    }







    @Override
    public void onValidationSucceeded() {

        ativarValidacao(false);


        ColaboradorResultado resultado;

        String nome = activityColaboradorBinding.txtInpNome.getText().toString();
        String posto = activityColaboradorBinding.txtInpPosto.getText().toString();
        String profissao = activityColaboradorBinding.txtInpProfissao.getText().toString();

        Date dataNascimento = DatasUtil.converterString(activityColaboradorBinding.txtInpDataNascimento.getText().toString(), DatasUtil.FORMATO_DD_MM_YYYY);
        Date dataAdmissao = DatasUtil.converterString(activityColaboradorBinding.txtInpDataAdmissao.getText().toString(), DatasUtil.FORMATO_DD_MM_YYYY);
        Date dataAdmissaoFuncao = DatasUtil.converterString(activityColaboradorBinding.txtInpDataAdmissaoFuncao.getText().toString(), DatasUtil.FORMATO_DD_MM_YYYY);

        Tipo genero = (Tipo) activityColaboradorBinding.spnrGenero.getItems().get(activityColaboradorBinding.spnrGenero.getSelectedIndex());
        Morada morada = (Morada) activityColaboradorBinding.spnrMorada.getItems().get(activityColaboradorBinding.spnrMorada.getSelectedIndex());


        //--resultado = new ColaboradorResultado(PreferenciasUtil.obterIdTarefa(this), nome, posto, profissao, dataNascimento, dataAdmissao, dataAdmissaoFuncao, genero.codigo, morada.id, idCategoriaProfissional);


        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            //--resultado.id = bundle.getInt(getString(R.string.argumento_id_colaborador));
        }

        //--viewModel.gravar(resultado);
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
