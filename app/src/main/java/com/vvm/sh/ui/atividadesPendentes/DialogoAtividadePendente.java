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

import javax.inject.Inject;

import butterknife.OnClick;

public class DialogoAtividadePendente extends BaseDaggerDialogFragment {


    private DialogoAtividadePendenteBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private AtividadesPendentesViewModel viewModel;


    private OnAtividadePendenteListener listenerAtividade;



    private static final String ARGUMENTO_RELATORIO = "relatorio";
    private static final String ARGUMENTO_ID_ATIVIDADE = "id";

    public DialogoAtividadePendente() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoAtividadePendente newInstance(int id, int relatorio) {
        DialogoAtividadePendente frag = new DialogoAtividadePendente();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_RELATORIO, relatorio);
        args.putInt(ARGUMENTO_ID_ATIVIDADE, id);
        frag.setArguments(args);
        return frag;
    }




    @Override
    protected void initDialogo(AlertDialog.Builder builder) {

        listenerAtividade = (OnAtividadePendenteListener) getContext();

        viewModel = ViewModelProviders.of(this, providerFactory).get(AtividadesPendentesViewModel.class);
        binding = (DialogoAtividadePendenteBinding) activityBaseBinding;


        formatarDialogo();

        builder.setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                terminarDialogo();
            }
        });

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
    protected void clickPositivo() {

    }


    /**
     * Metodo que permite formatar as opcoes do dialogo
     */
    private void formatarDialogo() {

        if(getArguments().getInt(ARGUMENTO_RELATORIO) == Identificadores.Estados.SEM_RELATORIO){

            binding.rdBtnRelatorio.setVisibility(View.GONE);
        }
    }



    @OnClick({R.id.rd_btn_actividade_executada, R.id.rd_btn_actividade_nao_executada, R.id.rd_btn_relatorio})
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

            default:
                break;
        }
    }




}
