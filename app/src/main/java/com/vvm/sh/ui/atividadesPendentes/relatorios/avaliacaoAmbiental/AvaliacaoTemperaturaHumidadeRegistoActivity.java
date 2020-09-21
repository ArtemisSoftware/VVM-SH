package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Max;
import com.mobsandgeeks.saripaar.annotation.Min;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityAvaliacaoTemperaturaHumidadeRegistoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.pesquisa.Pesquisa;
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

    private List<Integer> categoriasProfissionais;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_numero_homens)
    TextInputEditText txt_inp_numero_homens;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_numero_mulheres)
    TextInputEditText txt_inp_numero_mulheres;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_temperatura)
    TextInputEditText txt_inp_temperatura;

    @Max(value = 100, message = Sintaxe.Alertas.VALOR_INVALIDO)
    @Min(value = 0, message = Sintaxe.Alertas.VALOR_INVALIDO)
    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_humidade_relativa)
    TextInputEditText txt_inp_humidade_relativa;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        validador = new Validator(this);
        validador.setValidationListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AvaliacaoAmbientalViewModel.class);

        activityAvaliacaoTemperaturaHumidadeRegistoBinding = (ActivityAvaliacaoTemperaturaHumidadeRegistoBinding) activityBinding;
        activityAvaliacaoTemperaturaHumidadeRegistoBinding.setLifecycleOwner(this);
        activityAvaliacaoTemperaturaHumidadeRegistoBinding.setViewmodel(viewModel);
        activityAvaliacaoTemperaturaHumidadeRegistoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_relatorio));
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

                        avancarRelatorio();
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
     * Metodo que permite calcular o nivel de temperatura
     * @return true quando for valido ou false caso contrario
     */
    private boolean calcularNivelTemperatura() {

        boolean resultado = true;

        try{

            int temperatura = Integer.parseInt(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpTemperatura.getText().toString());

            if(temperatura < 18 || temperatura > 22){
                resultado = false;
            }
        }
        catch(NumberFormatException | NullPointerException e){
            resultado = true;
        }

        return resultado;
    }


    /**
     * Metodo que permite calcular o nivel de humidade
     * @return true quando for valido ou false caso contrario
     */
    private boolean calcularNivelHumidade() {

        boolean resultado = true;

        try{

            int humidade = Integer.parseInt(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpHumidadeRelativa.getText().toString());

            if(humidade < 50 || humidade > 70){
                resultado = false;
            }
        }
        catch(NumberFormatException | NullPointerException e){
            resultado = true;
        }

        return resultado;
    }


    /**
     * Metodo que permite limpar o registo
     */
    private void limparRegisto() {

        //TODO: testar isto

        viewModel.avaliacao.setValue(null);

        /*
        ((EditText) vista.findViewById(R.id.edit_txt_temperatura)).setText(AppIF.SEM_TEXTO);
		((EditText) vista.findViewById(R.id.edit_txt_humidade_relativa)).setText(AppIF.SEM_TEXTO);

		((EditText) vista.findViewById(R.id.edit_txt_homens)).setText(AppIF.SEM_TEXTO);
		((EditText) vista.findViewById(R.id.edit_txt_mulheres)).setText(AppIF.SEM_TEXTO);
        */
        categoriasProfissionais = new ArrayList<>();
    }


    private void avancarRelatorio() {

        if(calcularNivelTemperatura() == false || calcularNivelHumidade() == false){

            //--dialogoMedidasRecomendadas();
        }
        else{
            limparRegisto();
        }

    }

    //----------------------
    //EVENTOS
    //----------------------


    @OnTextChanged(value = R.id.txt_inp_temperatura, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void txt_inp_temperatura_OnTextChanged(CharSequence text) {

        if(calcularNivelTemperatura() == false){
            activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtNivelDificienteTemperatura.setVisibility(View.VISIBLE);
        }
        else{
            activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtNivelDificienteTemperatura.setVisibility(View.GONE);
        }
    }

    @OnTextChanged(value = R.id.txt_inp_humidade_relativa, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void txt_inp_humidade_relativa_OnTextChanged(CharSequence text) {

        if(calcularNivelHumidade() == false){
            activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtNivelDificienteHumidadeRelativa.setVisibility(View.VISIBLE);
        }
        else{
            activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtNivelDificienteHumidadeRelativa.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.crl_btn_pesquisar_categorias_profissionais)
    public void crl_btn_pesquisar_categorias_profissionais_OnClickListener(View view) {

        Pesquisa pesquisa = new Pesquisa(true, TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS, viewModel.obterRegistosSelecionados());

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);

        Intent intent = new Intent(this, PesquisaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA);
    }



    @Override
    public void onValidationSucceeded() {

        Bundle bundle = getIntent().getExtras();
        int idRealtorio = bundle.getInt(getString(R.string.argumento_id_relatorio));

        Tipo area = (Tipo) activityAvaliacaoTemperaturaHumidadeRegistoBinding.spnrAreaPostoTrabalho.getItems().get(activityAvaliacaoTemperaturaHumidadeRegistoBinding.spnrAreaPostoTrabalho.getSelectedIndex());
        String anexoArea = activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpDescricaoArea.getText().toString();

        int homens = Integer.parseInt(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpNumeroHomens.getText().toString());
        int mulheres = Integer.parseInt(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpNumeroMulheres.getText().toString());
        double temperatura = Double.parseDouble(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpTemperatura.getText().toString());
        double humidadeRelativa = Integer.parseInt(activityAvaliacaoTemperaturaHumidadeRegistoBinding.txtInpHumidadeRelativa.getText().toString());


        AvaliacaoAmbientalResultado registo = new AvaliacaoAmbientalResultado(idRealtorio, area.id, anexoArea, homens, mulheres, temperatura, humidadeRelativa);

        viewModel.gravar(registo, categoriasProfissionais, calcularNivelHumidade(), calcularNivelTemperatura());
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

                ArrayList<Integer> resultado = data.getIntegerArrayListExtra(getString(R.string.resultado_pesquisa));

                viewModel.fixarCategoriasProfissionais(resultado);
            }
        }
    }
}
