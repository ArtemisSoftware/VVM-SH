package com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.TrabalhadorVulneravelResultado;
import com.vvm.sh.databinding.ActivityTrabalhadorVulneravelRegistoBinding;
import com.vvm.sh.databinding.ActivityTrabalhadoresVulneraveisBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.modelos.TrabalhadorVulneravel;
import com.vvm.sh.ui.pesquisa.Pesquisa;
import com.vvm.sh.ui.pesquisa.PesquisaActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.OnClick;

public class TrabalhadorVulneravelRegistoActivity extends BaseDaggerActivity {


    private ActivityTrabalhadorVulneravelRegistoBinding activityTrabalhadorVulneravelRegistoBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private TrabalhadoresVulneraveisViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(TrabalhadoresVulneraveisViewModel.class);

        activityTrabalhadorVulneravelRegistoBinding = (ActivityTrabalhadorVulneravelRegistoBinding) activityBinding;
        activityTrabalhadorVulneravelRegistoBinding.setLifecycleOwner(this);
        activityTrabalhadorVulneravelRegistoBinding.setViewmodel(viewModel);
        activityTrabalhadorVulneravelRegistoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            viewModel.obterVulnerabilidade(bundle.getInt(getString(R.string.argumento_id)));
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_trabalhador_vulneravel_registo;
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

                        dialogo.sucesso(recurso.messagem, listenerActivity);
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
     * Metoodo que permite validar os dados do formulário
     * @return true caso os dados sejam validos e false caso contrário
     */
    private boolean validarDados(){

        boolean valido = true, valido_homens = true, valido_mulheres = true;
        int quantidadeMulheres = 0, quantidadeHomens = 0;

        try{

            quantidadeMulheres = Integer.parseInt(activityTrabalhadorVulneravelRegistoBinding.txtInpNumeroMulheres.getText().toString());

            if(quantidadeMulheres > 0 & viewModel.categoriasProfissionaisMulheres.getValue().size() == 0){
                valido_mulheres = valido_mulheres & false;
            }

            if(quantidadeMulheres < viewModel.categoriasProfissionaisMulheres.getValue().size()){

                valido_mulheres = valido_mulheres & false;
                activityTrabalhadorVulneravelRegistoBinding.txtCategoriasProfissionaisMulheres.setError(Sintaxe.Alertas.VALOR_INVALIDO);

            }
        }
        catch(NumberFormatException | NullPointerException e){
            valido_mulheres = false;
            activityTrabalhadorVulneravelRegistoBinding.txtInpNumeroMulheres.setError(Sintaxe.Alertas.VALOR_INVALIDO);

        }


        if(viewModel.vulnerabilidade.getValue().feminina == false){

            try{

                quantidadeHomens = Integer.parseInt(activityTrabalhadorVulneravelRegistoBinding.txtInpNumeroHomens.getText().toString());

                if(quantidadeHomens > 0 & viewModel.categoriasProfissionaisHomens.getValue().size() == 0){
                    valido_homens = valido_homens & false;
                }

                if(quantidadeHomens < viewModel.categoriasProfissionaisHomens.getValue().size()){

                    valido_homens = valido_homens & false;
                    activityTrabalhadorVulneravelRegistoBinding.txtCategoriasProfissionais.setError(Sintaxe.Alertas.VALOR_INVALIDO);
                }
            }
            catch(NumberFormatException | NullPointerException e){
                valido_homens = false;
                activityTrabalhadorVulneravelRegistoBinding.txtInpNumeroHomens.setError(Sintaxe.Alertas.VALOR_INVALIDO);
            }
        }


        return valido_homens & valido_mulheres;
    }





    //---------------------
    //EVENTOS
    //---------------------


    @OnClick(R.id.crl_btn_pesquisar_categorias_profissionais_homens)
    public void crl_btn_pesquisar_categorias_profissionais_homens_OnClickListener(View view) {

        Pesquisa pesquisa = new Pesquisa(true, TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS/*, viewModel.obterRegistosSelecionados()*/);

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);

        Intent intent = new Intent(this, PesquisaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA_CATEGORIAS_PROFISSIONAIS_HOMENS);
    }



    @OnClick(R.id.crl_btn_pesquisar_categorias_profissionais_mulheres)
    public void crl_btn_pesquisar_categorias_profissionais_mulheres_OnClickListener(View view) {

        Pesquisa pesquisa = new Pesquisa(true, TiposUtil.MetodosTipos.CATEGORIAS_PROFISSIONAIS/*, viewModel.obterRegistosSelecionados()*/);

        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.argumento_configuracao_pesquisa), pesquisa);

        Intent intent = new Intent(this, PesquisaActivity.class);
        intent.putExtras(bundle);
        startActivityForResult(intent, Identificadores.CodigoAtividade.PESQUISA_CATEGORIAS_PROFISSIONAIS_MULHERES);
    }


    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {
        if(validarDados() == true){

            int quantidadeMulheres = Integer.parseInt(activityTrabalhadorVulneravelRegistoBinding.txtInpNumeroMulheres.getText().toString());
            int quantidadeHomens = Integer.parseInt(activityTrabalhadorVulneravelRegistoBinding.txtInpNumeroHomens.getText().toString());

            TrabalhadorVulneravelResultado registo = viewModel.vulnerabilidade.getValue().resultado;
            registo.homens = quantidadeHomens;
            registo.mulheres = quantidadeMulheres;


            viewModel.gravar(registo);
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Identificadores.CodigoAtividade.PESQUISA_CATEGORIAS_PROFISSIONAIS_HOMENS) {

            if(resultCode == RESULT_OK){

                ArrayList<Integer> categoriasProfissionais = data.getIntegerArrayListExtra(getString(R.string.resultado_pesquisa));
                viewModel.fixarCategoriasProfissionais(true, categoriasProfissionais);
            }
        }


        if (requestCode == Identificadores.CodigoAtividade.PESQUISA_CATEGORIAS_PROFISSIONAIS_MULHERES) {

            if(resultCode == RESULT_OK){

                ArrayList<Integer> categoriasProfissionais = data.getIntegerArrayListExtra(getString(R.string.resultado_pesquisa));
                viewModel.fixarCategoriasProfissionais(false, categoriasProfissionais);
            }
        }
    }

}
