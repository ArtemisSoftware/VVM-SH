package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.DecimalMax;
import com.mobsandgeeks.saripaar.annotation.DecimalMin;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityAvaliacaoTemperaturaHumidadeRegistoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.pesquisa.PesquisaMedidasActivity;
import com.vvm.sh.ui.pesquisa.modelos.Pesquisa;
import com.vvm.sh.ui.pesquisa.PesquisaActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnTextChanged;

public class AvaliacaoTemperaturaHumidadeRegistoActivity extends BaseDaggerActivity
        implements Validator.ValidationListener{


    private ActivityAvaliacaoTemperaturaHumidadeRegistoBinding activityAvaliacaoTemperaturaHumidadeRegistoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private AvaliacaoAmbientalViewModel viewModel;



    private Validator validador;



    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_numero_homens)
    TextInputEditText txt_inp_numero_homens;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_numero_mulheres)
    TextInputEditText txt_inp_numero_mulheres;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_temperatura)
    TextInputEditText txt_inp_temperatura;

    @DecimalMax(value = 100, sequence = 3, message = Sintaxe.Alertas.VALOR_INVALIDO)
    @DecimalMin(value = 0, sequence = 2, message = Sintaxe.Alertas.VALOR_INVALIDO)
    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_humidade_relativa)
    TextInputEditText txt_inp_humidade_relativa;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_categorias_profissionais)
    TextView txt_categorias_profissionais;

    private List<Integer> categoriasProfissionais;
    private List<Integer> medidas;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        validador = new Validator(this);
        validador.setValidationListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AvaliacaoAmbientalViewModel.class);

        activityAvaliacaoTemperaturaHumidadeRegistoBinding = (ActivityAvaliacaoTemperaturaHumidadeRegistoBinding) activityBinding;
        activityAvaliacaoTemperaturaHumidadeRegistoBinding.setLifecycleOwner(this);
        activityAvaliacaoTemperaturaHumidadeRegistoBinding.setViewmodel(viewModel);
        activityAvaliacaoTemperaturaHumidadeRegistoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        medidas = new ArrayList<>();

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_avaliacao), -1);
            viewModel.obterAvalicao(id, Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE);
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_avaliacao_temperatura_humidade_registo;
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

                        dialogo.sucesso(recurso.messagem);
                        limparRegisto();
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });
    }





    //-------------------
    //Metodos locais
    //-------------------


    /**
     * Metodo que permite calcular o nivel de temperatura e humidade
     * @return  true quando for valido ou false caso contrario
     */
    private boolean calcularNivelTemperaturaHumidade() {

        boolean resultadoTemperatura = true;

        try {

            double temperatura = Double.parseDouble(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpTemperatura.getText().toString());

            if (temperatura < 18 || temperatura > 22) {
                resultadoTemperatura = false;
            }
        } catch (NumberFormatException | NullPointerException e) {
            resultadoTemperatura = true;
        }


        boolean resultadoHumidade = true;

        try {

            double humidade = Double.parseDouble(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpHumidadeRelativa.getText().toString());

            if (humidade < 50 || humidade > 70) {
                resultadoHumidade = false;
            }
        } catch (NumberFormatException | NullPointerException e) {
            resultadoHumidade = true;
        }


        if ((resultadoHumidade & resultadoTemperatura) == false) {
            activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtNivelDificienteTemperatura.setVisibility(View.VISIBLE);
            activityAvaliacaoTemperaturaHumidadeRegistoBinding.lnrLytMedidas.setVisibility(View.VISIBLE);
        } else {
            activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtNivelDificienteTemperatura.setVisibility(View.GONE);
            activityAvaliacaoTemperaturaHumidadeRegistoBinding.lnrLytMedidas.setVisibility(View.GONE);
        }

        return (resultadoHumidade & resultadoTemperatura);
    }



    /**
     * Metodo que permite limpar o registo
     */
    private void limparRegisto() {

        viewModel.avaliacao.setValue(null);

        activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpTemperatura.setText(Sintaxe.SEM_TEXTO);
        activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpHumidadeRelativa.setText(Sintaxe.SEM_TEXTO);
        activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpNumeroHomens.setText(Sintaxe.SEM_TEXTO);
        activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpNumeroMulheres.setText(Sintaxe.SEM_TEXTO);

        activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtCategoriasProfissionais.setText(Sintaxe.SEM_TEXTO);
        categoriasProfissionais = new ArrayList<>();

        activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtMedidas.setText(Sintaxe.SEM_TEXTO);
        medidas = new ArrayList<>();
    }



    //----------------------
    //EVENTOS
    //----------------------


    @OnTextChanged(value = R.id.txt_inp_temperatura, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void txt_inp_temperatura_OnTextChanged(CharSequence text) {

        calcularNivelTemperaturaHumidade();
    }

    @OnTextChanged(value = R.id.txt_inp_humidade_relativa, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void txt_inp_humidade_relativa_OnTextChanged(CharSequence text) {

        calcularNivelTemperaturaHumidade();
    }


    @OnClick(R.id.crl_btn_pesquisar_categorias_profissionais)
    public void crl_btn_pesquisar_categorias_profissionais_OnClickListener(View view) {

        Pesquisa pesquisa = new Pesquisa(true, TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS,
                                        viewModel.obterRegistosSelecionados(viewModel.categoriasProfissionais.getValue()));

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);

        Intent intent = new Intent(this, PesquisaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA);
    }


    @OnClick(R.id.crl_btn_pesquisar_medidas)
    public void crl_btn_pesquisar_medidas_OnClickListener(View view) {

        Pesquisa pesquisa = new Pesquisa(true,
                TiposUtil.MetodosTipos.MEDIDAS_ILUMINACAO_TERMICO,
                Identificadores.Codigos.TERMICO, Identificadores.Origens.AVALIACAO_AMBIENTAL_TEMPERATURA_HUMIDADE,
                viewModel.obterRegistosSelecionados(viewModel.medidas.getValue()));

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);

        Intent intent = new Intent(this, PesquisaMedidasActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA_MEDIDAS_RECOMENDADAS);

    }




    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {
        validador.validate();
    }



    @Override
    public void onValidationSucceeded() {

        Bundle bundle = getIntent().getExtras();
        int idRealtorio = bundle.getInt(getString(R.string.argumento_id_relatorio));
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        if(calcularNivelTemperaturaHumidade() == false & activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtMedidas.getText().toString().equals("") == true){

            activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtMedidas.setError(Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO);
            return;
        }


        Tipo area = (Tipo) activityAvaliacaoTemperaturaHumidadeRegistoBinding.spnrAreaPostoTrabalho.getItems().get(activityAvaliacaoTemperaturaHumidadeRegistoBinding.spnrAreaPostoTrabalho.getSelectedIndex());
        String anexoArea = activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpDescricaoArea.getText().toString();

        int homens = Integer.parseInt(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpNumeroHomens.getText().toString());
        int mulheres = Integer.parseInt(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpNumeroMulheres.getText().toString());
        double temperatura = Double.parseDouble(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpTemperatura.getText().toString().replace(",","."));
        double humidadeRelativa = Double.parseDouble(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpHumidadeRelativa.getText().toString().replace(",","."));


        AvaliacaoAmbientalResultado registo = new AvaliacaoAmbientalResultado(idRealtorio, area.id, anexoArea, homens, mulheres, temperatura, humidadeRelativa);

        viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), idAtividade, registo, categoriasProfissionais, medidas, calcularNivelTemperaturaHumidade(),
                Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE, Identificadores.Origens.ORIGEM_RELATORIO_TEMPERATURA_HUMIDADE_MEDIDAS_RECOMENDADAS);
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

            if (view instanceof TextView) {
                ((TextView) view).setError(message);
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Identificadores.CodigoAtividade.PESQUISA) {

            if(resultCode == RESULT_OK){

                categoriasProfissionais = data.getIntegerArrayListExtra(getString(R.string.resultado_pesquisa));
                viewModel.fixarCategoriasProfissionais(categoriasProfissionais);
            }
        }

        if (requestCode == Identificadores.CodigoAtividade.PESQUISA_MEDIDAS_RECOMENDADAS) {

            if(resultCode == RESULT_OK){

                medidas = data.getIntegerArrayListExtra(getString(R.string.resultado_pesquisa));
                viewModel.fixarMedidas(TiposUtil.MetodosTipos.MEDIDAS_ILUMINACAO_TERMICO, Identificadores.Codigos.TERMICO, medidas);
            }
        }
    }
}
