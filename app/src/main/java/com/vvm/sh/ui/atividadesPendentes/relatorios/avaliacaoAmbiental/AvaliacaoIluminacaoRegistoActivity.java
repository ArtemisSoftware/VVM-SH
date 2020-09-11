package com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

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
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import butterknife.OnItemSelected;
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


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        validador = new Validator(this);
        validador.setValidationListener(this);

        viewModel = ViewModelProviders.of(this, providerFactory).get(AvaliacaoAmbientalViewModel.class);

        activityAvaliacaoIluminacaoRegistoBinding = (ActivityAvaliacaoIluminacaoRegistoBinding) activityBinding;
        activityAvaliacaoIluminacaoRegistoBinding.setLifecycleOwner(this);
        activityAvaliacaoIluminacaoRegistoBinding.setViewmodel(viewModel);
        activityAvaliacaoIluminacaoRegistoBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            int id = bundle.getInt(getString(R.string.argumento_id_relatorio));
            viewModel.obterAvalicao(id);
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


    private void avancarRelatorio() {

        if(calcularNivelIluminacao() == false){

            //--dialogoMedidasRecomendadas();
        }
        else{
            limparRegisto();
        }

    }


//
//    /**
//     * Metodo que inicia o dialogo de medidas recomendadas
//     */
//    private void dialogoMedidasRecomendadas() {
//
//        final EscolhaMultipla registos = new EscolhaMultipla(((Item_AvaliacaoAmbiental) adaptador.obterRegistoSelecionado()).obterMedidas(IdentificadoresIF.ORIGEM_MEDIDAS_RECOMENDADAS),  obterMedidasRecomendadas());
//
//        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
//        builder.setTitle(SintaxeIF.TITULO_MEDIDAS_RECOMENDADAS);
//
//        builder.setMultiChoiceItems(registos.obterListaBase(), registos.obterValoresMarcados(), new DialogInterface.OnMultiChoiceClickListener() {
//
//            @Override
//            public void onClick(DialogInterface dialog,int posicao, boolean isChecked) {
//
//                if (isChecked == true){
//                    registos.adicionarRegisto(posicao);
//                }
//                else{
//                    registos.removerRegisto(posicao);
//                }
//            }
//        });
//
//        builder.setPositiveButton(SintaxeIF.OPCAO_OK, new DialogInterface.OnClickListener() {
//
//            public void onClick(DialogInterface dialog,int id) {
//
//                adicionarMedidas(registos.obterMedidasSelecionadas());
//                dialog.cancel();
//            }
//        });
//
//        AlertDialog alert = builder.create();
//        alert.show();
//    }
//
//    @Override
//    protected Mapeamento obterMedidasRecomendadas() {
//        return acessoBdRelatorio.obterMedidasRecomendadas(TiposBdIF_v3.SECCAO_MedidasIluminacaoTermico, "iluminacao");
//    }
//
//    /**
//     * Metodo que permite adicionar as medidas de uma avaliacao
//     */
//    protected void adicionarMedidas(ArrayList<String> idMedidas){
//
//        acessoBdRelatorio.gravarMedidas(adaptador.obterRegistoSelecionado().obterId(), idMedidas);
//
//        MetodosMensagens.gerarToast(contexto, SintaxeIF.DADOS_GRAVADOS_SUCESSO);
//        adaptador.atualizar();
//        ((IndiceRelatorioActivity) contexto).atualizar(idRelatorio);
//    }
//


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
            resultado = true;
        };

        return resultado;
    }


    /**
     * Metodo que permite limpar o registo
     */
    private void limparRegisto() {

        //TODO: testar isto

        viewModel.avaliacao.setValue(null);

        /*
        ((Spinner)vista.findViewById(R.id.spnr_tipo_iluminacao)).setSelection(0);

        ((EditText) vista.findViewById(R.id.edit_txt_nome)).setText(AppIF.SEM_TEXTO);
        ((Spinner)vista.findViewById(R.id.spnr_sexo)).setSelection(0);

        ((EditText) vista.findViewById(R.id.edit_txt_emedio_lx)).setText(AppIF.SEM_TEXTO);
        ((Spinner)vista.findViewById(R.id.spnr_eLx)).setSelection(0);
        ((Spinner)vista.findViewById(R.id.spnr_eLx_area)).setSelection(0);
        */
        categoriasProfissionais = new ArrayList<>();
    }




    @OnTextChanged(value = R.id.txt_inp_emedio_lx, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    public void txt_inp_emedio_lx_OnTextChanged(CharSequence text) {

        if(calcularNivelIluminacao() == false){
            activityAvaliacaoIluminacaoRegistoBinding.txtNivelDificiente.setVisibility(View.VISIBLE);
        }
        else{
            activityAvaliacaoIluminacaoRegistoBinding.txtNivelDificiente.setVisibility(View.GONE);
        }
    }


    @OnItemSelected(R.id.spnr_eLx_area)
    public void spinnerItemSelected(MaterialSpinner spinner, int position) {
//        if(selecionado == true){
//
//            ItemSpinner item = (ItemSpinner) ((SpinnerAdaptador) spnr.getAdapter()).getItem(position);
//            ((Spinner)vista.findViewById(R.id.spnr_eLx)).setAdapter(acessoBd.obterElx(item.obterId()));
//        }

        calcularNivelIluminacao();
    }

    @OnItemSelected(R.id.spnr_eLx)
    public void spinnerItemSelected_(MaterialSpinner spinner, int position) {
        calcularNivelIluminacao();
    }



    @OnClick(R.id.crl_btn_pesquisar)
    public void crl_btn_pesquisar_OnClickListener(View view) {


    }

    @OnClick(R.id.fab_gravar)
    public void fab_gravar_OnClickListener(View view) {
        validador.validate();
    }



    @Override
    public void onValidationSucceeded() {

        Bundle bundle = getIntent().getExtras();
        int idRealtorio = bundle.getInt(getString(R.string.argumento_id_relatorio));
        Tipo area = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrAreaPostoTrabalho.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrAreaPostoTrabalho.getSelectedIndex());
        String anexoArea = activityAvaliacaoIluminacaoRegistoBinding.txtInpDescricaoArea.getText().toString();
        String nome = activityAvaliacaoIluminacaoRegistoBinding.txtInpNome.getText().toString();
        Tipo sexo = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrGenero.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrGenero.getSelectedIndex());
        Tipo tipoIluminacao = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrTipoIluminacao.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrTipoIluminacao.getSelectedIndex());
        String emedioLx = activityAvaliacaoIluminacaoRegistoBinding.txtInpEmedioLx.getText().toString();
        Tipo elx = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrELx.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrELx.getSelectedIndex());
        Tipo elxArea = (Tipo) activityAvaliacaoIluminacaoRegistoBinding.spnrELxArea.getItems().get(activityAvaliacaoIluminacaoRegistoBinding.spnrELxArea.getSelectedIndex());


        AvaliacaoAmbientalResultado registo = new AvaliacaoAmbientalResultado(idRealtorio, area.id, anexoArea, nome, sexo.id, tipoIluminacao.id, emedioLx, elxArea.id, elx.id, elx.codigo);


        viewModel.gravar(registo, categoriasProfissionais, calcularNivelIluminacao());

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
}
