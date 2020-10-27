package com.vvm.sh.ui.atividadesPendentes;

import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityAtividadesPendentesBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.OnAtividadePendenteListener;
import com.vvm.sh.ui.atividadesPendentes.modelos.AtividadePendenteRegisto;
import com.vvm.sh.ui.atividadesPendentes.relatorios.DialogoProcessoProdutivo;
import com.vvm.sh.ui.atividadesPendentes.relatorios.FormacaoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.avaliacaoAmbiental.RelatorioAvaliacaoAmbientalActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.averiguacao.AveriguacaoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.ChecklistActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.equipamentos.EquipamentosActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos.LevantamentosActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.propostaPlanoAccao.PropostaPlanoAccaoActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.trabalhadoresVulneraveis.TrabalhadoresVulneraveisActivity;
import com.vvm.sh.ui.imagens.GaleriaActivity;
import com.vvm.sh.ui.imagens.modelos.Galeria;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class AtividadesPendentesActivity extends BaseDaggerActivity
        implements OnAtividadePendenteListener
        /*implements OnItemListener,
        DialogoAtividadePendente.DialogoListener, DialogoAtividadePendenteExecutada.DialogListener, DialogoAtividadePendenteNaoExecutada.DialogoListener*/ {


    private ActivityAtividadesPendentesBinding activityAtividadesPendentesBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private AtividadesPendentesViewModel viewModel;



    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(AtividadesPendentesViewModel.class);

        activityAtividadesPendentesBinding = (ActivityAtividadesPendentesBinding) activityBinding;
        activityAtividadesPendentesBinding.setLifecycleOwner(this);
        activityAtividadesPendentesBinding.setListener(this);
        activityAtividadesPendentesBinding.setViewmodel(viewModel);


        subscreverObservadores();

        viewModel.obterAtividades(PreferenciasUtil.obterIdTarefa(this));
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_atividades_pendentes;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

    }


     //---------------------
    //Eventos
    //---------------------


    @Override
    public void OnAtividadeClick(AtividadePendenteRegisto atividade) {

        DialogoAtividadePendente dialogo = DialogoAtividadePendente.newInstance(atividade.atividade.id, atividade.atividade.idRelatorio, atividade.relatorioCompleto);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnConcluirAtividadeExecutada(int idAtividade) {

        DialogoAtividadePendenteExecutada dialogo = DialogoAtividadePendenteExecutada.newInstance(idAtividade);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnConcluirAtividadeNaoExecutada(int idAtividade) {

        DialogoAtividadePendenteNaoExecutada dialogo = DialogoAtividadePendenteNaoExecutada.newInstance(idAtividade);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }


    @Override
    public void OnDetalhe(int idAtividade) {

        for(AtividadePendenteRegisto item: viewModel.atividades.getValue()){

            if(item.atividade.id == idAtividade){

                DialogoDetalheAtividadePendente dialogo = DialogoDetalheAtividadePendente.newInstance(item.resultado);
                dialogo.show(getSupportFragmentManager(), "example dialog");
                break;
            }
        }
    }



    @Override
    public void OnIniciarRelatorio(int idAtividade, int idRelatorio) {

        Intent intent = null;

        switch (idRelatorio){

            case Identificadores.Relatorios.ID_RELATORIO_FORMACAO:
                intent = new Intent(this, FormacaoActivity.class);
                break;


            case Identificadores.Relatorios.ID_RELATORIO_ILUMINACAO:
            case Identificadores.Relatorios.ID_RELATORIO_TEMPERATURA_HUMIDADE:
                intent = new Intent(this, RelatorioAvaliacaoAmbientalActivity.class);
                intent.putExtra(getString(R.string.argumento_tipo_relatorio), idRelatorio);
                break;


            case Identificadores.Relatorios.ID_RELATORIO_AVERIGUACAO_AVALIACAO_RISCO:
            case Identificadores.Relatorios.ID_RELATORIO_AVERIGUACAO_AUDITORIA:
                intent = new Intent(this, AveriguacaoActivity.class);
                intent.putExtra(getString(R.string.argumento_tipo_relatorio), idRelatorio);
                break;


            default:
                break;

        }


        if(intent != null){

            intent.putExtra(getString(R.string.argumento_id_atividade), idAtividade);
            startActivity(intent);
        }
    }



    @Override
    public void OnIniciarChecklist(int idAtividade) {

        Intent intent = new Intent(this, ChecklistActivity.class);
        intent.putExtra(getString(R.string.argumento_id_atividade), idAtividade);
        startActivity(intent);
    }

    @Override
    public void OnIniciarProcessoProdutivo(int idAtividade) {

        DialogoProcessoProdutivo dialogo = DialogoProcessoProdutivo.newInstance(idAtividade);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnIniciarVulnerabilidades(int idAtividade) {
        Intent intent = new Intent(this, TrabalhadoresVulneraveisActivity.class);
        intent.putExtra(getString(R.string.argumento_id_atividade), idAtividade);
        startActivity(intent);
    }

    @Override
    public void OnIniciarPlanoAcao(int idAtividade) {
        Intent intent = new Intent(this, PropostaPlanoAccaoActivity.class);
        intent.putExtra(getString(R.string.argumento_id_atividade), idAtividade);
        startActivity(intent);
    }

    @Override
    public void OnIniciarAvaliacaoRiscos(int idAtividade) {
        Intent intent = new Intent(this, LevantamentosActivity.class);
        intent.putExtra(getString(R.string.argumento_id_atividade), idAtividade);
        startActivity(intent);
    }

    @Override
    public void OnIniciarEquipamentos(int idAtividade) {
        Intent intent = new Intent(this, EquipamentosActivity.class);
        intent.putExtra(getString(R.string.argumento_id_atividade), idAtividade);
        startActivity(intent);
    }

    @Override
    public void OnCapaRelatorio(int idAtividade) {

        Galeria galeria = new Galeria(Galeria.GALERIA_CAPA_RELATORIO, PreferenciasUtil.obterIdTarefa(this));

        Intent intent = new Intent(this, GaleriaActivity.class);
        intent.putExtra(getString(R.string.argumento_galeria), galeria);
        startActivity(intent);
    }


}
