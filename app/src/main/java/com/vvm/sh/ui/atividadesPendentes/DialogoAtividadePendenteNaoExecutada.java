package com.vvm.sh.ui.atividadesPendentes;

import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.vvm.sh.R;
import com.vvm.sh.util.BaseDialogoPersistenteFragment;

import java.util.List;

public class DialogoAtividadePendenteNaoExecutada extends BaseDialogoPersistenteFragment implements Validator.ValidationListener{


    @Override
    protected void iniciarDialogo() {

    }

    @Override
    protected void clickPositivo() {

    }

    @Override
    protected String obterTitulo() {
        return getString(R.string.concluir_atividade);
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_atividade_pendente_nao_executada;
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {

    }
}
