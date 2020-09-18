package com.vvm.sh.ui.quadroPessoal;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoOpcoesColaboradorBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogFragment;
import com.vvm.sh.ui.quadroPessoal.adaptadores.OnOpcoesColaboradorListener;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Inject;

import butterknife.OnClick;

public class DialogoOpcoesColaborador extends BaseDaggerDialogFragment {


    private DialogoOpcoesColaboradorBinding binding;


    private static final String ARGUMENTO_ORIGEM = "origem";
    private static final String ARGUMENTO_ID = "id";
    private static final String ARGUMENTO_ID_RESULTADO = "idResultado";

    private OnOpcoesColaboradorListener listenerOpcoes;


    @Inject
    ViewModelProviderFactory providerFactory;

    private QuadroPessoalViewModel viewModel;


    public DialogoOpcoesColaborador() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoOpcoesColaborador newInstance(int id, int origem) {
        DialogoOpcoesColaborador fragmento = new DialogoOpcoesColaborador();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ORIGEM, origem);
        args.putInt(ARGUMENTO_ID, id);
        fragmento.setArguments(args);
        return fragmento;
    }


    @Override
    protected void initDialogo(AlertDialog.Builder builder) {


        listenerOpcoes = (OnOpcoesColaboradorListener) getContext();

        viewModel = ViewModelProviders.of(this, providerFactory).get(QuadroPessoalViewModel.class);

        binding = (DialogoOpcoesColaboradorBinding) activityBaseBinding;


        viewModel.obterColaborador(PreferenciasUtil.obterIdTarefa(getContext()), getArguments().getInt(ARGUMENTO_ID));

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
        return R.layout.dialogo_opcoes_colaborador;
    }

    @Override
    protected int obterTitulo() {
        return R.string.colaborador;
    }

    @Override
    protected void subscreverObservadores() {

    }


    //-------------------------
    //Metodos locais
    //-------------------------

    /**
     * Metodo que permite formatar as opcoes do dialogo
     */
    private void formatarDialogo() {

        if(getArguments().getInt(ARGUMENTO_ORIGEM) == Identificadores.Origens.ORIGEM_WS){
            binding.rdBtnRemover.setVisibility(View.GONE);
            binding.rdBtnDetalhe.setVisibility(View.VISIBLE);
        }
        else{

            binding.rdBtnDemitir.setVisibility(View.GONE);
            binding.rdBtnReadmitir.setVisibility(View.GONE);
        }

        if(PreferenciasUtil.agendaEditavel(getContext()) == false){
            binding.rdBtnEditar.setVisibility(View.GONE);
            binding.rdBtnDemitir.setVisibility(View.GONE);
            binding.rdBtnReadmitir.setVisibility(View.GONE);
            binding.rdBtnRemover.setVisibility(View.GONE);
            binding.rdBtnDetalhe.setVisibility(View.VISIBLE);
        }

    }


    //-------------------------
    //EVENTOS
    //-------------------------


    @OnClick({R.id.rd_btn_editar, R.id.rd_btn_demitir, R.id.rd_btn_readmitir, R.id.rd_btn_remover, R.id.rd_btn_detalhe})
    public void onRadioButtonClicked(RadioButton radioButton) {

        boolean checked = radioButton.isChecked();

        // Check which radio button was clicked
        switch (radioButton.getId()) {

            case R.id.rd_btn_editar:
                if (checked) {

                    listenerOpcoes.OnEditarColaborador(getArguments().getInt(ARGUMENTO_ID), getArguments().getInt(ARGUMENTO_ORIGEM));
                    terminarDialogo();
                }
                break;

            case R.id.rd_btn_demitir:
                if (checked) {

                    listenerOpcoes.OnDemitirColaborador(viewModel.colaborador.getValue());
                    terminarDialogo();
                }
                break;

            case R.id.rd_btn_readmitir:
                if (checked) {

                    listenerOpcoes.OnReademitirColaborador(viewModel.colaborador.getValue());
                    terminarDialogo();
                }
                break;


            case R.id.rd_btn_remover:
                if (checked) {

                    listenerOpcoes.OnRemoverColaborador(getArguments().getInt(ARGUMENTO_ID));
                    terminarDialogo();
                }
                break;


            case R.id.rd_btn_detalhe:
                if (checked) {

                    listenerOpcoes.OnDetalheColaborador(getArguments().getInt(ARGUMENTO_ID));
                    terminarDialogo();
                }
                break;

            default:
                break;
        }
    }




    @Override
    protected void clickPositivo() {

    }
}
