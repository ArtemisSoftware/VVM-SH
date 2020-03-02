package com.vvm.sh.ui.atividadesPendentes;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.RadioButton;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDialogFragment;

import butterknife.OnClick;

public class DialogoAtividadePendente extends BaseDialogFragment {

    private DialogoListener listener;

    @Override
    protected void criarDialogo(AlertDialog.Builder builder) {

        builder.setNegativeButton(getString(R.string.cancelar), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });

    }

    @Override
    protected String obterTitulo() {
        return getString(R.string.atividade_pendente);
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_atividade_pendente;
    }


    @OnClick({R.id.rd_btn_actividade_executada, R.id.rd_btn_actividade_nao_executada, R.id.rd_btn_relatorio})
    public void onRadioButtonClicked(RadioButton radioButton) {
        // Is the button now checked?
        boolean checked = radioButton.isChecked();

        // Check which radio button was clicked
        switch (radioButton.getId()) {
            case R.id.rd_btn_actividade_executada:
                if (checked) {
                    // 1 clicked
                    listener.selecionarOpcao(123);
                }
                break;
            case R.id.rd_btn_actividade_nao_executada:
                if (checked) {
                    // 2 clicked
                }
                break;

            case R.id.rd_btn_relatorio:
                if (checked) {
                    // 2 clicked
                }
                break;

            default:
                break;
        }
    }



    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            listener = (DialogoListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() +
                    "must implement ExampleDialogListener");
        }
    }

    public interface DialogoListener {
        void selecionarOpcao(int idOpcao);
    }
}
