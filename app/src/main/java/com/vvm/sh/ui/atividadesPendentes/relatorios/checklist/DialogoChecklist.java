package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;

import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.databinding.DialogoAtividadePendenteBinding;
import com.vvm.sh.databinding.DialogoChecklistBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogFragment;
import com.vvm.sh.ui.atividadesPendentes.AtividadesPendentesViewModel;
import com.vvm.sh.ui.atividadesPendentes.adaptadores.OnAtividadePendenteListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores.OnChecklistListener;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Inject;

import butterknife.OnClick;

public class DialogoChecklist extends BaseDaggerDialogFragment {


    private DialogoChecklistBinding binding;


    private OnChecklistListener.OnAlterarChecklistListener listenerChecklist;



    public DialogoChecklist() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoChecklist newInstance() {
        DialogoChecklist frag = new DialogoChecklist();

        return frag;
    }




    @Override
    protected void initDialogo(AlertDialog.Builder builder) {

        listenerChecklist = (OnChecklistListener.OnAlterarChecklistListener) getContext();
        binding = (DialogoChecklistBinding) activityBaseBinding;

    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_checklist;
    }

    @Override
    protected int obterTitulo() {
        return R.string.alterar_checklist;
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


    //-------------------------
    //EVENTOS
    //-------------------------



    @Override
    protected void clickPositivo() {

    }




    @OnClick({R.id.rd_btn_industria, R.id.rd_btn_comercio, R.id.rd_btn_restauracao})
    public void onRadioButtonClicked(RadioButton radioButton) {

        boolean checked = radioButton.isChecked();

        // Check which radio button was clicked
        switch (radioButton.getId()) {

            case R.id.rd_btn_industria:
                if (checked) {
                    // 1 clicked
                    listenerChecklist.OnAlterarCheckClick(5);
                    terminarDialogo();
                }
                break;
            case R.id.rd_btn_comercio:
                if (checked) {
                    // 2 clicked
                    listenerChecklist.OnAlterarCheckClick(4);
                    terminarDialogo();
                }
                break;

            case R.id.rd_btn_restauracao:
                if (checked) {
                    // 2 clicked
                    listenerChecklist.OnAlterarCheckClick(6);
                    terminarDialogo();
                }
                break;



            default:
                break;
        }
    }




}
