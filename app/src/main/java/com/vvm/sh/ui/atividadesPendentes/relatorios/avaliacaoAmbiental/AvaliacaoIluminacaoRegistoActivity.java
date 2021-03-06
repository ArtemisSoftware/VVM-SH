package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.AvaliacaoAmbientalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityAvaliacaoIluminacaoRegistoBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.modelos.AvaliacaoAmbiental;
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

public class AvaliacaoIluminacaoRegistoActivity extends BaseDaggerActivity
        implements Validator.ValidationListener{


    private ActivityAvaliacaoIluminacaoRegistoBinding activityAvaliacaoIluminacaoRegistoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private AvaliacaoAmbientalViewModel viewModel;


    private Validator validador;



    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_nome)
    TextInputEditText txt_inp_nome;

    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_emedio_lx)
    TextInputEditText txt_inp_emedio_lx;

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

        activityAvaliacaoIluminacaoRegistoBinding = (ActivityAvaliacaoIluminacaoRegistoBinding) activityBinding;
        activityAvaliacaoIluminacaoRegistoBinding.setLifecycleOwner(this);
        activityAvaliacaoIluminacaoRegistoBinding.setViewmodel(viewModel);

        activityAvaliacaoIluminacaoRegistoBinding.spnrELxArea.setOnItemSelectedListener(spnr_eLx_area_ItemSelected);
        activityAvaliacaoIluminacaoRegistoBinding.spnrELx.setOnItemSelectedListener(spnr_eLx_ItemSelected);

        activityAvaliacaoIluminacaoRegistoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        medidas = new ArrayList<>();

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_avaliacao), -1);
            viewModel.obterAvalicao(id, Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO);
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_avaliacao_iluminacao_registo;
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

        viewModel.observarElxArea().observe(this, new Observer<List<Tipo>>() {
            @Override
            public void onChanged(List<Tipo> tipos) {
                viewModel.obterElx(tipos.get(0).id);
                calcularNivelIluminacao();
            }
        });

        viewModel.observarAvaliacao().observe(this, new Observer<AvaliacaoAmbiental>() {
            @Override
            public void onChanged(AvaliacaoAmbiental avaliacaoAmbiental) {
                if(avaliacaoAmbiental != null) {
                    viewModel.obterElx(avaliacaoAmbiental.resultado.eLxArea);
                    calcularNivelIluminacao();
                }
            }
        });
    }



    //-------------------
    //Metodos locais
    //-------------------



    /**
     * Metodo que permite calcular o nivel de iluminancia
     * @return true quando for valido ou false caso contrario
     */
    private boolean calcularNivelIluminacao(){

        boolean resultado = true;

        try{

            Tipo elx = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrELx.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrELx.getSelectedIndex());

            int valorEmedioLx = Integer.parseInt(activityAvaliacaoIluminacaoRegistoBinding.txtInpEmedioLx.getText().toString());
            int valorELx = Integer.parseInt(elx.codigo);

            if(valorEmedioLx < (valorELx - ((10 * valorELx) / 100))){
                resultado = false;
            }

        }
        catch(NumberFormatException | NullPointerException e){
            resultado = false;
        };

        if(resultado == false){
            activityAvaliacaoIluminacaoRegistoBinding.txtNivelDificiente.setVisibility(View.VISIBLE);
            activityAvaliacaoIluminacaoRegistoBinding.lnrLytMedidas.setVisibility(View.VISIBLE);
        }
        else{
            activityAvaliacaoIluminacaoRegistoBinding.lnrLytMedidas.setVisibility(View.GONE);
            activityAvaliacaoIluminacaoRegistoBinding.txtNivelDificiente.setVisibility(View.GONE);
        }

        return resultado;
    }


    /**
     * Metodo que permite limpar o registo
     */
    private void limparRegisto() {

        viewModel.avaliacao.setValue(null);

        activityAvaliacaoIluminacaoRegistoBinding.spnrTipoIluminacao.setSelectedIndex(0);
        activityAvaliacaoIluminacaoRegistoBinding.spnrGenero.setSelectedIndex(0);
        activityAvaliacaoIluminacaoRegistoBinding.spnrTipoIluminacao.setSelectedIndex(0);

        activityAvaliacaoIluminacaoRegistoBinding.txtInpNome.setText(Sintaxe.SEM_TEXTO);
        activityAvaliacaoIluminacaoRegistoBinding.txtInpEmedioLx.setText(Sintaxe.SEM_TEXTO);

        activityAvaliacaoIluminacaoRegistoBinding.txtCategoriasProfissionais.setText(Sintaxe.SEM_TEXTO);
        categoriasProfissionais = new ArrayList<>();

        activityAvaliacaoIluminacaoRegistoBinding.txtMedidas.setText(Sintaxe.SEM_TEXTO);
        medidas = new ArrayList<>();
    }






    @OnTextChanged(value = R.id.txt_inp_emedio_lx, callback = OnTextChanged.Callback.TEXT_CHANGED)
    public void txt_inp_emedio_lx_OnTextChanged(CharSequence text) {
        calcularNivelIluminacao();
    }



    MaterialSpinner.OnItemSelectedListener spnr_eLx_area_ItemSelected = new MaterialSpinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            Tipo elxArea = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrELxArea.getItems().get(position);

            viewModel.obterElx(elxArea.id);
            calcularNivelIluminacao();
        }
    };


    MaterialSpinner.OnItemSelectedListener spnr_eLx_ItemSelected = new MaterialSpinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            calcularNivelIluminacao();
        }
    };


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
                                         Identificadores.Codigos.ILUMINACAO, Identificadores.Origens.AVALIACAO_AMBIENTAL_ILUMINACAO_MEDIDAS_RECOMENDADAS,
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


        if(calcularNivelIluminacao() == false & activityAvaliacaoIluminacaoRegistoBinding.txtMedidas.getText().toString().equals("") == true){

            activityAvaliacaoIluminacaoRegistoBinding.txtMedidas.setError(Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO);
            return;
        }

        Bundle bundle = getIntent().getExtras();
        int idRealtorio = bundle.getInt(getString(R.string.argumento_id_relatorio));
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        Tipo area = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrAreaPostoTrabalho.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrAreaPostoTrabalho.getSelectedIndex());
        String anexoArea = activityAvaliacaoIluminacaoRegistoBinding.txtInpDescricaoArea.getText().toString();
        String nome = activityAvaliacaoIluminacaoRegistoBinding.txtInpNome.getText().toString();
        Tipo sexo = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrGenero.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrGenero.getSelectedIndex());
        Tipo tipoIluminacao = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrTipoIluminacao.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrTipoIluminacao.getSelectedIndex());
        String emedioLx = activityAvaliacaoIluminacaoRegistoBinding.txtInpEmedioLx.getText().toString();
        Tipo elx = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrELx.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrELx.getSelectedIndex());
        Tipo elxArea = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrELxArea.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrELxArea.getSelectedIndex());


        AvaliacaoAmbientalResultado registo = new AvaliacaoAmbientalResultado(idRealtorio, area.id, anexoArea, nome, sexo.id, tipoIluminacao.id, emedioLx, elxArea.id, elx.id, elx.codigo);

        viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), idAtividade,registo, categoriasProfissionais, medidas, calcularNivelIluminacao(),
                Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO, Identificadores.Origens.ORIGEM_RELATORIO_ILUMINACAO_MEDIDAS_RECOMENDADAS);

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
                viewModel.fixarMedidas(TiposUtil.MetodosTipos.MEDIDAS_ILUMINACAO_TERMICO, Identificadores.Codigos.ILUMINACAO, medidas);
            }
        }
    }


}
