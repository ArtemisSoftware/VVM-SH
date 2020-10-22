package com.vvm.sh.ui.atividadesPendentes;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoAtividadePendenteBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.OnAtividadePendenteListener;
import com.vvm.sh.ui.BaseDaggerDialogFragment;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Inject;

import butterknife.OnClick;

public class DialogoAtividadePendente extends BaseDaggerDialogFragment {


    private DialogoAtividadePendenteBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private AtividadesPendentesViewModel viewModel;


    private OnAtividadePendenteListener listenerAtividade;



    private static final String ARGUMENTO_RELATORIO = "relatorio";
    private static final String ARGUMENTO_RELATORIO_COMPLETO = "relatorio_completo";
    private static final String ARGUMENTO_ID_ATIVIDADE = "id";

    public DialogoAtividadePendente() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoAtividadePendente newInstance(int id, int relatorio, boolean relatorioCompleto) {
        DialogoAtividadePendente frag = new DialogoAtividadePendente();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_RELATORIO, relatorio);
        args.putInt(ARGUMENTO_ID_ATIVIDADE, id);
        args.putBoolean(ARGUMENTO_RELATORIO_COMPLETO, relatorioCompleto);
        frag.setArguments(args);
        return frag;
    }




    @Override
    protected void initDialogo(AlertDialog.Builder builder) {

        listenerAtividade = (OnAtividadePendenteListener) getContext();

        viewModel = ViewModelProviders.of(this, providerFactory).get(AtividadesPendentesViewModel.class);
        binding = (DialogoAtividadePendenteBinding) activityBaseBinding;

        formatarDialogo();
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_atividade_pendente;
    }

    @Override
    protected int obterTitulo() {
        return R.string.atividade_pendente;
    }

    @Override
    protected void subscreverObservadores() {

    }

    @Override
    public void onStart() {
        super.onStart();
        AlertDialog d = (AlertDialog) getDialog();
        if (d != null) {
            d.getButton(DialogInterface.BUTTON_POSITIVE).setVisibility(View.GONE);

        }
    }

    //-------------------------
    //Metodos locais
    //-------------------------


    /**
     * Metodo que permite formatar as opcoes do dialogo
     */
    private void formatarDialogo() {

        if(getArguments().getInt(ARGUMENTO_RELATORIO) == Identificadores.Estados.SEM_RELATORIO){
            binding.rdBtnRelatorio.setVisibility(View.GONE);
        }
        else{

            if(getArguments().getBoolean(ARGUMENTO_RELATORIO_COMPLETO) == false){
                binding.rdBtnActividadeExecutada.setVisibility(View.GONE);
            }
        }

        if(getArguments().getInt(ARGUMENTO_RELATORIO) == Identificadores.Relatorios.ID_RELATORIO_AVALIACAO_RISCO){

            binding.rdBtnRelatorio.setVisibility(View.GONE);
            binding.rdGpAvaliacaoRiscos.setVisibility(View.VISIBLE);
        }


        if(PreferenciasUtil.agendaEditavel(getContext()) == false){
            binding.rdBtnActividadeExecutada.setVisibility(View.GONE);
            binding.rdBtnActividadeNaoExecutada.setVisibility(View.GONE);
        }
        else{
            binding.rdBtnDetalhe.setVisibility(View.GONE);
        }

    }


    //-------------------------
    //EVENTOS
    //-------------------------



    @Override
    protected void clickPositivo() {

    }




    @OnClick({R.id.rd_btn_actividade_executada, R.id.rd_btn_actividade_nao_executada, R.id.rd_btn_relatorio,
            R.id.rd_btn_risco_checklist, R.id.rd_btn_risco_processo_produtivo, R.id.rd_btn_risco_vulnerabilidades, R.id.rd_btn_risco_levantamento, R.id.rd_btn_av_plano_acao,
            R.id.rd_btn_risco_verificacao_equipamentos, R.id.rd_btn_capa_relatorio})
    public void onRadioButtonClicked(RadioButton radioButton) {

        boolean checked = radioButton.isChecked();

        // Check which radio button was clicked
        switch (radioButton.getId()) {

            case R.id.rd_btn_actividade_executada:
                if (checked) {
                    // 1 clicked
                    listenerAtividade.OnConcluirAtividadeExecutada(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
                    terminarDialogo();
                }
                break;
            case R.id.rd_btn_actividade_nao_executada:
                if (checked) {
                    // 2 clicked
                    listenerAtividade.OnConcluirAtividadeNaoExecutada(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
                    terminarDialogo();
                }
                break;

            case R.id.rd_btn_relatorio:
                if (checked) {
                    // 2 clicked
                    listenerAtividade.OnIniciarRelatorio(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE), getArguments().getInt(ARGUMENTO_RELATORIO));
                    terminarDialogo();
                }
                break;


            case R.id.rd_btn_detalhe:
                if (checked) {
                    // 2 clicked
                    listenerAtividade.OnDetalhe(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
                    terminarDialogo();
                }
                break;



            case R.id.rd_btn_risco_checklist:
                if (checked) {
                    listenerAtividade.OnIniciarChecklist(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
                    terminarDialogo();
                }
                break;

            case R.id.rd_btn_risco_processo_produtivo:
                if (checked) {
                    listenerAtividade.OnIniciarProcessoProdutivo(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
                    terminarDialogo();
                }
                break;


            case R.id.rd_btn_risco_vulnerabilidades:
                if (checked) {
                    listenerAtividade.OnIniciarVulnerabilidades(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
                    terminarDialogo();
                }
                break;


            case R.id.rd_btn_risco_levantamento:
                if (checked) {
                    listenerAtividade.OnIniciarAvaliacaoRiscos(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
                    terminarDialogo();
                }
                break;

            case R.id.rd_btn_av_plano_acao:
                if (checked) {
                    listenerAtividade.OnIniciarPlanoAcao(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
                    terminarDialogo();
                }
                break;


            case R.id.rd_btn_risco_verificacao_equipamentos:
                if (checked) {
                    listenerAtividade.OnIniciarEquipamentos(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
                    terminarDialogo();
                }
                break;


            case R.id.rd_btn_capa_relatorio:
                if (checked) {
                    listenerAtividade.OnCapaRelatorio(getArguments().getInt(ARGUMENTO_ID_ATIVIDADE));
                    terminarDialogo();
                }
                break;

            default:
                break;
        }
    }




}
