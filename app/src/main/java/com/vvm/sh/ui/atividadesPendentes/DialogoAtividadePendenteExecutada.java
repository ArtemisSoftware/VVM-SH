package com.vvm.sh.ui.atividadesPendentes;

import android.app.AlertDialog;
import android.content.Context;

import com.google.android.material.textfield.TextInputEditText;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDialogFragment;

import butterknife.BindView;

public class DialogoAtividadePendenteExecutada extends BaseDialogFragment {

    @BindView(R.id.txt_inp_minutos)
    TextInputEditText txt_inp_minutos;

    @BindView(R.id.txt_inp_data_execucao)
    TextInputEditText txt_inp_data_execucao;


    private DialogListener listener;

    @Override
    protected void criarDialogo(AlertDialog.Builder builder) {

    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_atividade_pendente_executada;
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }


    public interface DialogListener {
        void gravarAnomalia(String minutos, String dataExecucao);
    }
}
