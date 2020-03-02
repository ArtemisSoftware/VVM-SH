package com.vvm.sh.ui.atividadesPendentes;

import android.app.AlertDialog;

import com.vvm.sh.R;
import com.vvm.sh.ui.BaseDialogFragment;

public class DialogoAtividadePendente extends BaseDialogFragment {
    @Override
    protected void criarDialogo(AlertDialog.Builder builder) {

    }

    @Override
    protected String obterTitulo() {
        return null;
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_atividade_pendente;
    }
}
