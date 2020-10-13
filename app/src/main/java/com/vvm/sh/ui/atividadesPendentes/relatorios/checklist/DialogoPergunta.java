package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.jaredrummler.materialspinner.MaterialSpinner;
import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.DialogoChecklistPerguntaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.TiposConstantes;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Inject;

public class DialogoPergunta  extends BaseDaggerDialogoPersistenteFragment
 implements MaterialSpinner.OnItemSelectedListener {

    private DialogoChecklistPerguntaBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private ChecklistViewModel viewModel;

    private static final String ARGUMENTO_ITEM = "item";
    private static final String ARGUMENTO_ID_QUESTAO = "idQuestao";
    private static final String ARGUMENTO_ID_REGISTO = "idRegisto";
    private static final String ARGUMENTO_TIPO_QUESTAO = "tipoQuestao";
    private static final String ARGUMENTO_NUMERO_UT = "numeroUt";
    private static final String ARGUMENTO_ID_ATIVIDADE = "idAtividade";


    public DialogoPergunta() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoPergunta newInstance(Item item, int idAtividade, String idQuestao, String tipoQuestao, int idRegisto) {
        DialogoPergunta fragmento = new DialogoPergunta();

        Bundle args = new Bundle();
        args.putParcelable(ARGUMENTO_ITEM, item);
        args.putString(ARGUMENTO_ID_QUESTAO, idQuestao);
        args.putString(ARGUMENTO_TIPO_QUESTAO, tipoQuestao);
        args.putInt(ARGUMENTO_ID_ATIVIDADE, idAtividade);
        args.putInt(ARGUMENTO_ID_REGISTO, idRegisto);
        fragmento.setArguments(args);
        return fragmento;
    }


    public static DialogoPergunta newInstance(Item item, int idAtividade, String idQuestao, String tipoQuestao, int idRegisto, int numeroUt) {
        DialogoPergunta fragmento = new DialogoPergunta();

        Bundle args = new Bundle();
        args.putParcelable(ARGUMENTO_ITEM, item);
        args.putString(ARGUMENTO_ID_QUESTAO, idQuestao);
        args.putString(ARGUMENTO_TIPO_QUESTAO, tipoQuestao);
        args.putInt(ARGUMENTO_ID_REGISTO, idRegisto);
        args.putInt(ARGUMENTO_ID_ATIVIDADE, idAtividade);
        args.putInt(ARGUMENTO_NUMERO_UT, numeroUt);
        fragmento.setArguments(args);
        return fragmento;
    }

    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ChecklistViewModel.class);
        binding = (DialogoChecklistPerguntaBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        binding.spnrResposta.setOnItemSelectedListener(this);

        if(verificarArgumentos(ARGUMENTO_ITEM) == true){

            formatarDialogo(getArguments().getString(ARGUMENTO_TIPO_QUESTAO));
            viewModel.obterRespostas(getArguments().getInt(ARGUMENTO_ID_REGISTO));
        }
        else{
            terminarDialogo();
        }

    }



    @Override
    protected int obterLayout() {
        return R.layout.dialogo_checklist_pergunta;
    }

    @Override
    protected int obterTitulo() {
        return R.string.questao;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        terminarDialogo();
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                    default:
                        break;
                }

            }
        });
    }



    @Override
    protected void clickPositivo() {

        Item item = getArguments().getParcelable(ARGUMENTO_ITEM);
        String idQuestao = getArguments().getString(ARGUMENTO_ID_QUESTAO);
        int idAtividade = getArguments().getInt(ARGUMENTO_ID_ATIVIDADE);
        int idRegisto = getArguments().getInt(ARGUMENTO_ID_REGISTO);

        String tipo = getArguments().getString(ARGUMENTO_TIPO_QUESTAO);
        int numeroUt = getArguments().getInt(ARGUMENTO_NUMERO_UT);

        QuestionarioChecklistResultado resultado = null;


        switch (tipo){

            case Identificadores.Checklist.TIPO_QUESTAO:

                Tipo resposta = (Tipo) binding.spnrResposta.getItems().get(binding.spnrResposta.getSelectedIndex());
                Tipo subResposta = (Tipo) binding.spnrSubResposta.getItems().get(binding.spnrSubResposta.getSelectedIndex());


                if(resposta.id != TiposConstantes.Checklist.NAO.id){
                    subResposta.descricao = null;
                }

                resultado = new QuestionarioChecklistResultado(item, idQuestao, resposta.obterDescricao(), subResposta.obterDescricao());

                break;

            case Identificadores.Checklist.TIPO_OBSERVACOES:

                String observacao = binding.txtInpObservacao.getText().toString();
                resultado = new QuestionarioChecklistResultado(item, idQuestao, observacao);

                break;

            case Identificadores.Checklist.TIPO_UTS:

                Tipo ut = (Tipo) binding.spnrUt.getItems().get(binding.spnrUt.getSelectedIndex());
                Tipo categoria = (Tipo) binding.spnrCategoriasRisco.getItems().get(binding.spnrCategoriasRisco.getSelectedIndex());

                boolean a = binding.chkBoxLocaisRiscoA.isChecked();
                boolean b = binding.chkBoxLocaisRiscoB.isChecked();
                boolean c = binding.chkBoxLocaisRiscoC.isChecked();
                boolean d = binding.chkBoxLocaisRiscoD.isChecked();
                boolean e = binding.chkBoxLocaisRiscoE.isChecked();
                boolean f = binding.chkBoxLocaisRiscoF.isChecked();


                if(idRegisto == 0) {
                    resultado = new QuestionarioChecklistResultado(item, idQuestao, numeroUt, ut.id, categoria.id, a, b, c, d, e, f);
                }
                else{
                    resultado = viewModel.resposta;
                    resultado.fixarUt( numeroUt, ut.id, categoria.id, a, b, c, d, e, f);
                }

                binding.lnrLytUts.setVisibility(View.VISIBLE);
                break;

            default:
                break;

        }


        if(resultado != null) {
            viewModel.inserir(PreferenciasUtil.obterIdTarefa(getContext()), idAtividade, idRegisto, resultado);
        }
    }



    //------------------------
    //Metodos locais
    //------------------------

    /**
     * Metodo que permite formatar o dialogo
     * @param tipo o tipo de formatacao
     */
    private void formatarDialogo(String tipo) {

        switch (tipo){

            case Identificadores.Checklist.TIPO_QUESTAO:

                binding.lnrLytPergunta.setVisibility(View.VISIBLE);
                break;

            case Identificadores.Checklist.TIPO_OBSERVACOES:

                binding.lnrLytObservacao.setVisibility(View.VISIBLE);
                break;

            case Identificadores.Checklist.TIPO_UTS:

                binding.lnrLytUts.setVisibility(View.VISIBLE);
                break;

            default:
                break;

        }
    }





    //-------------------
    //EVENTOS
    //--------------------


    @Override
    public void onItemSelected(MaterialSpinner view, int position, long id, Object item) {

        Tipo resposta = (Tipo) binding.spnrResposta.getItems().get(binding.spnrResposta.getSelectedIndex());

        if(resposta.id == TiposConstantes.Checklist.NAO.id){
            binding.lnrLytSubResposta.setVisibility(View.VISIBLE);
        }
        else{
            binding.lnrLytSubResposta.setVisibility(View.GONE);
        }


    }
}
