package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputEditText;
import com.jaredrummler.materialspinner.MaterialSpinner;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.baseDados.entidades.RiscoResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.ActivityRiscoRegistoBinding;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.modelos.Risco;
import com.vvm.sh.ui.pesquisa.PesquisaMedidasActivity;
import com.vvm.sh.ui.pesquisa.modelos.Pesquisa;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.ImagemConstantes;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.ConversorUtil;
import com.vvm.sh.util.metodos.ImagemUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.io.IOException;
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
            viewModel.obterLevamentoRisco(id);
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
                        dialogo.sucesso(recurso.messagem);
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


        viewModel.observarRisco().observe(this, new Observer<Risco>() {
            @Override
            public void onChanged(Risco risco) {
                if(risco != null) {

                    medidasExistentes = viewModel.obterRegistosSelecionados(viewModel.medidasExistentes.getValue());
                    medidasRecomendadas = viewModel.obterRegistosSelecionados(viewModel.medidasRecomendadas.getValue());

                    viewModel.obteRiscoEspecifico(risco.resultado.idRisco);
                    calcularNP_NR();
                }

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

        viewModel.medidasRecomendadas.setValue(new ArrayList<>());
        viewModel.medidasExistentes.setValue(new ArrayList<>());
        viewModel.risco.setValue(null);
        viewModel.imagem.setValue(new byte[]{});
        medidasExistentes = new ArrayList<>();
        medidasRecomendadas = new ArrayList<>();
        activityRiscoRegistoBinding.txtMedidasExistentes.setText(getString(R.string.nenhuma_medida_selecionada));
        activityRiscoRegistoBinding.txtMedidasRecomendadas.setText(getString(R.string.nenhuma_medida_selecionada));
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
            activityRiscoRegistoBinding.txtMedidasExistentes.setText(getString(R.string.nenhuma_medida_selecionada));
            activityRiscoRegistoBinding.txtMedidasRecomendadas.setText(getString(R.string.nenhuma_medida_selecionada));
            medidasExistentes.clear();
            medidasRecomendadas.clear();
        }
    };


    MaterialSpinner.OnItemSelectedListener spnr_riscos_especificos_ItemSelected = new MaterialSpinner.OnItemSelectedListener() {

        @Override
        public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

            estatisticaRisco();
            activityRiscoRegistoBinding.txtMedidasExistentes.setText(getString(R.string.nenhuma_medida_selecionada));
            activityRiscoRegistoBinding.txtMedidasRecomendadas.setText(getString(R.string.nenhuma_medida_selecionada));
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

        Tipo riscoEspecifico = (Tipo) activityRiscoRegistoBinding.spnrRiscoEspecifico.getItems().get(activityRiscoRegistoBinding.spnrRiscoEspecifico.getSelectedIndex());


        Pesquisa pesquisa = new Pesquisa(true,
                TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_ADOPTADAS, medidasExistentes,
                riscoEspecifico.id + "");

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);

        Intent intent = new Intent(this, PesquisaMedidasActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA_MEDIDAS_ADOPTADAS);
    }

    @OnClick(R.id.crl_btn_pesquisar_medidas_existentes_limpar)
    public void crl_btn_pesquisar_medidas_existentes_limpar_OnClickListener(View view) {

        medidasExistentes.clear();
        activityRiscoRegistoBinding.txtMedidasExistentes.setText(getString(R.string.nenhuma_medida_selecionada));
    }

    @OnClick(R.id.crl_btn_pesquisar_medidas_recomendadas)
    public void crl_btn_pesquisar_medidas_recomendadas_OnClickListener(View view) {


        Tipo riscoEspecifico = (Tipo) activityRiscoRegistoBinding.spnrRiscoEspecifico.getItems().get(activityRiscoRegistoBinding.spnrRiscoEspecifico.getSelectedIndex());

        Pesquisa pesquisa = new Pesquisa(true,
                TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS, medidasRecomendadas,
                riscoEspecifico.id + "");

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);

        Intent intent = new Intent(this, PesquisaMedidasActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA_MEDIDAS_RECOMENDADAS);
    }

    @OnClick(R.id.crl_btn_pesquisar_medidas_recomendadas_limpar)
    public void crl_btn_pesquisar_medidas_recomendadas_limpar_OnClickListener(View view) {

        medidasRecomendadas.clear();
        activityRiscoRegistoBinding.txtMedidasRecomendadas.setText(getString(R.string.nenhuma_medida_selecionada));
    }



    @OnClick(R.id.crl_adicionar_imagem)
    public void crl_adicionar_imagem_OnClickListener(View view) {
        ImagemUtil.apresentarOpcoesCaptura(this);
    }


    @OnClick(R.id.crl_btn_imagem_limpar)
    public void crl_galeria_OnClickListener(View view) {
        viewModel.imagem.setValue(new byte []{});
    }



    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {
        validador.validate();
    }






    @Override
    public void onValidationSucceeded() {

        if(medidasExistentes.size() == 0 & medidasRecomendadas.size() == 0) {

            activityRiscoRegistoBinding.txtMedidasExistentes.setError(Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO);
            activityRiscoRegistoBinding.txtMedidasRecomendadas.setError(Sintaxe.Alertas.PREENCHIMENTO_OBRIGATORIO);
        }
        else{

            Bundle bundle = getIntent().getExtras();
            int idLevantamento = bundle.getInt(getString(R.string.argumento_id_levantamento));
            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

            Tipo risco = (Tipo) activityRiscoRegistoBinding.spnrRisco.getItems().get(activityRiscoRegistoBinding.spnrRisco.getSelectedIndex());
            Tipo riscoEspecifico = (Tipo) activityRiscoRegistoBinding.spnrRiscoEspecifico.getItems().get(activityRiscoRegistoBinding.spnrRiscoEspecifico.getSelectedIndex());

            String consequencias = activityRiscoRegistoBinding.txtInpConsequencias.getText().toString();

            Tipo nd = (Tipo) activityRiscoRegistoBinding.spnrNd.getItems().get(activityRiscoRegistoBinding.spnrNd.getSelectedIndex());
            Tipo ne = (Tipo) activityRiscoRegistoBinding.spnrNe.getItems().get(activityRiscoRegistoBinding.spnrNe.getSelectedIndex());
            Tipo nc = (Tipo) activityRiscoRegistoBinding.spnrNc.getItems().get(activityRiscoRegistoBinding.spnrNc.getSelectedIndex());
            String ni = ConversorUtil.obterNP_NR_NI(nd, ne, nc, viewModel.tiposNi)[3];

            RiscoResultado registo = new RiscoResultado(idLevantamento, risco.id, riscoEspecifico.id, consequencias, nd.obterDescricao(), ne.obterDescricao(), nc.obterDescricao(), ni);
            viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), idAtividade, registo, medidasExistentes, medidasRecomendadas);
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

        if (requestCode == Identificadores.CodigoAtividade.PESQUISA_MEDIDAS_RECOMENDADAS) {

            if(resultCode == RESULT_OK){

                medidasRecomendadas = data.getIntegerArrayListExtra(getString(R.string.resultado_pesquisa));
                viewModel.fixarMedidasRecomendadas(TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_RECOMENDADAS,  medidasRecomendadas);
            }
        }

        if (requestCode == Identificadores.CodigoAtividade.PESQUISA_MEDIDAS_ADOPTADAS) {

            if(resultCode == RESULT_OK){

                medidasExistentes = data.getIntegerArrayListExtra(getString(R.string.resultado_pesquisa));
                viewModel.fixarMedidasExistentes(TiposUtil.MetodosTipos.MEDIDAS_PREVENCAO_ADOPTADAS,  medidasExistentes);
            }
        }


        if (requestCode == ImagemConstantes.REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                    viewModel.imagem.setValue(ImagemUtil.converter(bitmap));
                }
                catch (IOException e) {
                    dialogo.alerta(Sintaxe.Palavras.IMAGEM , Sintaxe.Alertas.ERRO_GERAR_IMAGEM + e.getMessage());
                }
            }
        }

    }


}
