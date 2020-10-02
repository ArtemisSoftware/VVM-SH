package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

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
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityRiscoRegistoBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class RiscoRegistoActivity extends BaseDaggerActivity
        implements Validator.ValidationListener{


    private ActivityRiscoRegistoBinding activityRiscoRegistoBinding;

    private LevantamentosViewModel viewModel;


    @NotEmpty(message = Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO)
    @BindView(R.id.txt_inp_consequencias)
    TextInputEditText txt_inp_consequencias;




    private Validator validador;


    private List<Integer> medidasExistentes = new ArrayList<>();
    private List<Integer> medidasRecomendadas = new ArrayList<>();
    private List<Integer> imagens;

    @Override
    protected void intActivity(Bundle savedInstanceState) {

        validador = new Validator(this);
        validador.setValidationListener(this);


        viewModel = ViewModelProviders.of(this, providerFactory_).get(LevantamentosViewModel.class);

        activityRiscoRegistoBinding = (ActivityRiscoRegistoBinding) activityBinding;
        activityRiscoRegistoBinding.setLifecycleOwner(this);
        activityRiscoRegistoBinding.setViewmodel(viewModel);
        activityRiscoRegistoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));



        activityRiscoRegistoBinding.spnrRisco.setOnItemSelectedListener(spnr_riscos_ItemSelected);
        activityRiscoRegistoBinding.spnrRiscoEspecifico.setOnItemSelectedListener(spnr_riscos_especificos_ItemSelected);

        activityRiscoRegistoBinding.spnrNc.setOnItemSelectedListener(spnr_NP_NR_ItemSelected);
        activityRiscoRegistoBinding.spnrNd.setOnItemSelectedListener(spnr_NP_NR_ItemSelected);
        activityRiscoRegistoBinding.spnrNe.setOnItemSelectedListener(spnr_NP_NR_ItemSelected);



        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id));
            viewModel.obteRisco(id);
        }


    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_risco_registo;
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

                        limparRegisto();
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });


        viewModel.observarRiscos().observe(this, new Observer<List<Tipo>>() {
            @Override
            public void onChanged(List<Tipo> tipos) {
                viewModel.obteRiscoEspecifico(tipos.get(0).id);
            }
        });
    }



    //-------------------
    //Metodos locais
    //-------------------


    /**
     * Metodo que permite limpar o registo
     */
    private void limparRegisto() {

        //TODO: testar isto

        //--viewModel.avaliacao.setValue(null);

//        ((Button) vista.findViewById(R.id.btn_adicionar_medidas_existentes)).setText(SintaxeIF.NENHUMA_MEDIDA_SELECIONADA);
//        ((Button) vista.findViewById(R.id.btn_adicionar_medidas_recomendadas)).setText(SintaxeIF.NENHUMA_MEDIDA_SELECIONADA);
//        ((Button) vista.findViewById(R.id.btn_galeria)).setText(SintaxeIF.GALERIA);
//
//        ((Spinner) vista.findViewById(R.id.spnr_nd)).setSelection(0);
//        ((Spinner) vista.findViewById(R.id.spnr_ne)).setSelection(0);
//        ((Spinner) vista.findViewById(R.id.spnr_nc)).setSelection(0);
//        ((Spinner) vista.findViewById(R.id.spnr_risco)).setSelection(0);
//        ((Spinner) vista.findViewById(R.id.spnr_risco)).setEnabled(true);
//        ((Spinner) vista.findViewById(R.id.spnr_risco_especifico)).setSelection(0);
//        ((Spinner) vista.findViewById(R.id.spnr_risco_especifico)).setEnabled(true);
//        calcularNP_NR(vista);
//        ((Button) vista.findViewById(R.id.btn_galeria)).setVisibility(View.GONE);

        medidasExistentes = new ArrayList<>();
        medidasRecomendadas = new ArrayList<>();
    }




    /**
     * Metodo que calcula o NP e o NR
     */
    private void calcularNP_NR(){

        try{

            Tipo nd = (Tipo) activityRiscoRegistoBinding.spnrNd.getItems().get(activityRiscoRegistoBinding.spnrNd.getSelectedIndex());
            Tipo ne = (Tipo) activityRiscoRegistoBinding.spnrNe.getItems().get(activityRiscoRegistoBinding.spnrNe.getSelectedIndex());
            Tipo nc = (Tipo) activityRiscoRegistoBinding.spnrNc.getItems().get(activityRiscoRegistoBinding.spnrNc.getSelectedIndex());

            String valores [] = ConversorUtil.obterNP_NR_NI(nd, ne, nc, viewModel.tiposNi);

            activityRiscoRegistoBinding.txtNp.setText(valores[0]);
            activityRiscoRegistoBinding.txtNr.setText(valores[1]);
            activityRiscoRegistoBinding.txtNi.setText(valores[3]);
        }
        catch(NumberFormatException e){}
    }



    /**
     * Metodo que permite completar as estatisticas do risco

     */
    private void estatisticaRisco(/*String idRiscoEspecifico, View vista*/) {

//        ((TextView) vista.findViewById(R.id.txt_numero_medidas_existentes)).setText("(" + acessoBdAvaliacao.obterMedidas(idRiscoEspecifico, TiposBdIF_v3.SECCAO_MedidasPrevencaoAdoptadas).tamanho() + " medidas disponíveis)");
//        ((TextView) vista.findViewById(R.id.txt_numero_medidas_recomendadas)).setText("(" + acessoBdAvaliacao.obterMedidas(idRiscoEspecifico, TiposBdIF_v3.SECCAO_MedidasPrevencaoRecomendadas).tamanho() + " medidas disponíveis)");
//
//        if(adaptador.obterRegistoSelecionado() == null){
//
//            ((Button) vista.findViewById(R.id.btn_adicionar_medidas_existentes)).setText(SintaxeIF.NENHUMA_MEDIDA_SELECIONADA);
//            ((Button) vista.findViewById(R.id.btn_adicionar_medidas_recomendadas)).setText(SintaxeIF.NENHUMA_MEDIDA_SELECIONADA);
//            ((Button) vista.findViewById(R.id.btn_galeria)).setText(SintaxeIF.GALERIA);
//        }
//        else{
//
//            ((Button) vista.findViewById(R.id.btn_adicionar_medidas_existentes)).setText(medidasExistentes.size() + SintaxeIF.MEDIDAS_SELECIONADAS);
//            ((Button) vista.findViewById(R.id.btn_adicionar_medidas_recomendadas)).setText(medidasRecomendadas.size() + SintaxeIF.MEDIDAS_SELECIONADAS);
//
//            ((Button) vista.findViewById(R.id.btn_galeria)).setText(SintaxeIF.GALERIA + ": " + imagens.size() + " " + SintaxeIF.FOTOGRAFIA);
//
//        }
    }


    //-------------------
    //EVENTOS
    //-------------------


    MaterialSpinner.OnItemSelectedListener spnr_riscos_ItemSelected = new MaterialSpinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            Tipo risco = (Tipo) activityRiscoRegistoBinding.spnrRisco.getItems().get(position);

            viewModel.obteRiscoEspecifico(risco.id);
            medidasExistentes.clear();
            medidasRecomendadas.clear();
        }
    };


    MaterialSpinner.OnItemSelectedListener spnr_riscos_especificos_ItemSelected = new MaterialSpinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            estatisticaRisco();
            medidasExistentes.clear();
            medidasRecomendadas.clear();
        }
    };


    MaterialSpinner.OnItemSelectedListener spnr_NP_NR_ItemSelected = new MaterialSpinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            calcularNP_NR();
        }
    };




    @OnClick(R.id.crl_btn_pesquisar_medidas_existentes)
    public void crl_btn_pesquisar_medidas_existentes_OnClickListener(View view) {
//        Pesquisa pesquisa = new Pesquisa(true, TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS, viewModel.obterRegistosSelecionados());
//
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);
//
//        Intent intent = new Intent(this, PesquisaActivity.class);
//        intent.putExtras(bundle);
//        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA);
    }


    @OnClick(R.id.crl_btn_pesquisar_medidas_existentes_limpar)
    public void crl_btn_pesquisar_medidas_existentes_limpar_OnClickListener(View view) {


        medidasExistentes.clear();
        activityRiscoRegistoBinding.txtMedidasExistentes.setText(getString(R.string.nenhuma_medida_selecionada));
    }

    @OnClick(R.id.crl_btn_pesquisar_medidas_recomendadas)
    public void crl_btn_pesquisar_medidas_recomendadas_OnClickListener(View view) {
//        Pesquisa pesquisa = new Pesquisa(true, TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS, viewModel.obterRegistosSelecionados());
//
//        Bundle bundle = new Bundle();
//        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);
//
//        Intent intent = new Intent(this, PesquisaActivity.class);
//        intent.putExtras(bundle);
//        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA);
    }

    @OnClick(R.id.crl_btn_pesquisar_medidas_recomendadas_limpar)
    public void crl_btn_pesquisar_medidas_recomendadas_limpar_OnClickListener(View view) {

        medidasRecomendadas.clear();
        activityRiscoRegistoBinding.txtMedidasRecomendadas.setText(getString(R.string.nenhuma_medida_selecionada));
    }



    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {
        validador.validate();
    }






    @Override
    public void onValidationSucceeded() {

        if(medidasExistentes.size() == 0 & medidasRecomendadas.size() == 0) {
            //((TextView) view).setError(message);
        }
        else{

            Bundle bundle = getIntent().getExtras();
            int idLevantamento = bundle.getInt(getString(R.string.argumento_id_levantamento));

            Tipo risco = (Tipo) activityRiscoRegistoBinding.spnrRisco.getItems().get(activityRiscoRegistoBinding.spnrRisco.getSelectedIndex());
            Tipo riscoEspecifico = (Tipo) activityRiscoRegistoBinding.spnrRiscoEspecifico.getItems().get(activityRiscoRegistoBinding.spnrRisco.getSelectedIndex());

            String consequencias = activityRiscoRegistoBinding.txtInpConsequencias.getText().toString();

            Tipo nd = (Tipo) activityRiscoRegistoBinding.spnrNd.getItems().get(activityRiscoRegistoBinding.spnrNd.getSelectedIndex());
            Tipo ne = (Tipo) activityRiscoRegistoBinding.spnrNe.getItems().get(activityRiscoRegistoBinding.spnrNe.getSelectedIndex());
            Tipo nc = (Tipo) activityRiscoRegistoBinding.spnrNc.getItems().get(activityRiscoRegistoBinding.spnrNc.getSelectedIndex());

            String ni = activityRiscoRegistoBinding.txtNi.getText().toString();



            //--RiscoResultado registo = new RegistoResultado(idLevantamento, risco.id, riscoEspecifico.id, nd.obterDescricao(), ne.obterDescricao(), nc.obterDescricao(), ni,medidasExistentes, medidasRecomendadas, imagens)
            //--viewModel.gravar(registo);
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

                //ArrayList<Integer> resultado = data.getIntegerArrayListExtra(getString(R.string.resultado_pesquisa));

                //viewModel.fixarCategoriasProfissionais(resultado);
            }
        }
    }


}
