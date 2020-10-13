package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import android.os.Bundle;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.AreaChecklist;
import com.vvm.sh.baseDados.entidades.AreaChecklistResultado;
import com.vvm.sh.databinding.DialogoAreaChecklistBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import javax.inject.Inject;

public class DialogoArea extends BaseDaggerDialogoPersistenteFragment {

    private DialogoAreaChecklistBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private ChecklistViewModel viewModel;

    private static final String ARGUMENTO_ID_ATIVIDADE = "idAtividade";
    private static final String ARGUMENTO_ID_CHECKLIST = "idChecklist";
    private static final String ARGUMENTO_ITEM = "item";



    public DialogoArea() {
        // Empty constructor required for DialogFragment
    }

    public static DialogoArea newInstance(int idAtividade, int idChecklist) {
        DialogoArea fragmento = new DialogoArea();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID_ATIVIDADE, idAtividade);
        args.putInt(ARGUMENTO_ID_CHECKLIST, idChecklist);
        fragmento.setArguments(args);
        return fragmento;
    }



    public static DialogoArea newInstance(int idAtividade, int idChecklist, Item item) {
        DialogoArea fragmento = new DialogoArea();

        Bundle args = new Bundle();
        args.putInt(ARGUMENTO_ID_ATIVIDADE, idAtividade);
        args.putInt(ARGUMENTO_ID_CHECKLIST, idChecklist);
        args.putParcelable(ARGUMENTO_ITEM, item);
        fragmento.setArguments(args);
        return fragmento;
    }




    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ChecklistViewModel.class);
        binding = (DialogoAreaChecklistBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);


        if(verificarArgumentos(ARGUMENTO_ITEM) == true){
            binding.spnrAreas.setEnabled(false);

            binding.setIdArea(((Item) getArguments().getParcelable(ARGUMENTO_ITEM)).idArea);
            binding.txtInpDescricaoArea.setText(((Item) getArguments().getParcelable(ARGUMENTO_ITEM)).subDescricao);
            viewModel.obterAreasChecklist(getArguments().getInt(ARGUMENTO_ID_CHECKLIST));
        }
        else if(verificarArgumentos(ARGUMENTO_ID_CHECKLIST) == true){

            viewModel.obterAreasChecklist(getArguments().getInt(ARGUMENTO_ID_CHECKLIST));
        }
        else{
            terminarDialogo();
        }

    }



    @Override
    protected int obterLayout() {
        return R.layout.dialogo_area_checklist;
    }

    @Override
    protected int obterTitulo() {
        return R.string.area;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        if((boolean)recurso.dados == true) {

                            dialogo.sucesso(recurso.messagem);
                            binding.txtInpDescricaoArea.setText("");
                        }
                        else{
                            dialogo.sucesso(recurso.messagem, listener);
                        }
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

        int idAtividade = getArguments().getInt(ARGUMENTO_ID_ATIVIDADE);
        int idChecklist = getArguments().getInt(ARGUMENTO_ID_CHECKLIST);

        String descricao = binding.txtInpDescricaoArea.getText().toString();
        AreaChecklist area = (AreaChecklist) binding.spnrAreas.getItems().get(binding.spnrAreas.getSelectedIndex());


        AreaChecklistResultado resultado = new AreaChecklistResultado(idAtividade, idChecklist, area.idArea);

        if(descricao.equals("") == false){
            resultado.subDescricao = descricao;
        }

        if(verificarArgumentos(ARGUMENTO_ITEM) == true) {

            Item item = getArguments().getParcelable(ARGUMENTO_ITEM);

            resultado.id = item.id;
            viewModel.editarArea(PreferenciasUtil.obterIdTarefa(getContext()), resultado);
        }
        else {
            viewModel.inserNovaArea(PreferenciasUtil.obterIdTarefa(getContext()), resultado);
        }
    }



    //---------------------
    //Metodos locais
    //---------------------

}
