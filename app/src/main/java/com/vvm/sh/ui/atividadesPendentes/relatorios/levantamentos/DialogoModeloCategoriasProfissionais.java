package com.vvm.sh.ui.atividadesPendentes.relatorios.levantamentos;

import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.CategoriaProfissionalResultado;
import com.vvm.sh.baseDados.entidades.Tipo;
import com.vvm.sh.databinding.DialogoModeloCategoriasProfissionaisBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerDialogoPersistenteFragment;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class DialogoModeloCategoriasProfissionais extends BaseDaggerDialogoPersistenteFragment {


    @BindView(R.id.linearLayout)
    LinearLayout linearLayout;


    private DialogoModeloCategoriasProfissionaisBinding binding;

    @Inject
    ViewModelProviderFactory providerFactory;

    private LevantamentosViewModel viewModel;



    private static final String ARGUMENTO_ID_ATIVIDADE = "idAtividade";
    private static final String ARGUMENTO_ID_MODELO = "idModelo";
    private static final String ARGUMENTO_CATEGORIAS = "categorias";


    public DialogoModeloCategoriasProfissionais() {
        // Empty constructor required for DialogFragment
    }


    public static DialogoModeloCategoriasProfissionais newInstance(int idAtividade, int idModelo, ArrayList<Integer> categorias) {
        DialogoModeloCategoriasProfissionais fragmento = new DialogoModeloCategoriasProfissionais();

        Bundle args = new Bundle();
        args.putIntegerArrayList(ARGUMENTO_CATEGORIAS, categorias);
        args.putInt(ARGUMENTO_ID_ATIVIDADE, idAtividade);
        args.putInt(ARGUMENTO_ID_MODELO, idModelo);
        fragmento.setArguments(args);
        return fragmento;
    }




    @Override
    protected void iniciarDialogo() {

        viewModel = ViewModelProviders.of(this, providerFactory).get(LevantamentosViewModel.class);
        binding = (DialogoModeloCategoriasProfissionaisBinding) activityBaseBinding;
        binding.setViewmodel(viewModel);

        if(verificarArgumentos(ARGUMENTO_CATEGORIAS) == true){

            viewModel.obterTiposCategoriasProfissionais(getArguments().getIntegerArrayList(ARGUMENTO_CATEGORIAS));
        }
        else{
            terminarDialogo();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.dialogo_modelo_categorias_profissionais;
    }

    @Override
    protected int obterTitulo() {
        return R.string.categorias_profissionais;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        dialogo.sucesso(recurso.messagem, listener);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });


        viewModel.observarTiposCategorias().observe(this, new Observer<List<Tipo>>() {
            @Override
            public void onChanged(List<Tipo> tipos) {
                criarFormulario(tipos);
            }
        });
    }

    @Override
    protected void clickPositivo() {

        List<CategoriaProfissionalResultado> resultado = new ArrayList<>();

        int index = 0;

        for (int idCategoria : getArguments().getIntegerArrayList(ARGUMENTO_CATEGORIAS)) {

            int homens = Integer.parseInt(((EditText) linearLayout.getChildAt(index).findViewWithTag("h" + idCategoria)).getText().toString());
            int mulheres = Integer.parseInt(((EditText) linearLayout.getChildAt(index).findViewWithTag("m" + idCategoria)).getText().toString());

            resultado.add(new CategoriaProfissionalResultado(idCategoria, Identificadores.Origens.LEVANTAMENTO_CATEGORIAS_PROFISSIONAIS, homens, mulheres));
            ++index;
        }


        viewModel.inserirModeloCategoriasProfissionais(PreferenciasUtil.obterIdTarefa(getContext()), getArguments().getInt(ARGUMENTO_ID_ATIVIDADE), getArguments().getInt(ARGUMENTO_ID_MODELO), resultado);
    }


    /**
     * Metodo que permite criar o formulário
     * @param categorias
     */
    private void criarFormulario(List<Tipo> categorias){

        InputMethodManager im = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);

        for (Tipo categoria : categorias) {

            LinearLayout lnr_lyt_categoria = new LinearLayout(getContext());
            lnr_lyt_categoria.setOrientation(LinearLayout.HORIZONTAL);
            lnr_lyt_categoria.setPadding(0, 7, 0, 7);

            TextView txt_categoria = new TextView(getContext());
            txt_categoria.setLayoutParams(new LinearLayout.LayoutParams(350, LinearLayout.LayoutParams.MATCH_PARENT));
            txt_categoria.setTextSize(13);
            txt_categoria.setPadding(20, 0, 0, 0);
            txt_categoria.setText(categoria.descricao);

            EditText edt_homens = new EditText(getContext());
            edt_homens.setLayoutParams(new LinearLayout.LayoutParams(60, LinearLayout.LayoutParams.WRAP_CONTENT));
            edt_homens.setTag("h" + categoria.id);
            edt_homens.setText("0");
            edt_homens.setRawInputType(InputType.TYPE_CLASS_NUMBER);
            im.showSoftInput(edt_homens, 0);

            TextView txt_homens = new TextView(getContext());
            txt_homens.setLayoutParams(new LinearLayout.LayoutParams(170, LinearLayout.LayoutParams.WRAP_CONTENT));
            txt_homens.setPadding(20, 0, 0, 0);
            txt_homens.setTextSize(13);
            txt_homens.setText("homem(ns)");



            EditText edt_mulheres = new EditText(getContext());
            edt_mulheres.setLayoutParams(new LinearLayout.LayoutParams(60, LinearLayout.LayoutParams.WRAP_CONTENT));
            edt_mulheres.setTag("m" + categoria.id);
            edt_mulheres.setText("0");
            edt_mulheres.setRawInputType(InputType.TYPE_CLASS_NUMBER);
            im.showSoftInput(edt_mulheres, 0);

            TextView txt_mulheres = new TextView(getContext());
            txt_mulheres.setLayoutParams(new LinearLayout.LayoutParams(170, LinearLayout.LayoutParams.WRAP_CONTENT));
            txt_mulheres.setPadding(20, 0, 0, 0);
            txt_mulheres.setTextSize(13);
            txt_mulheres.setText("mulher(es)");


            lnr_lyt_categoria.addView(txt_categoria, 0);
            lnr_lyt_categoria.addView(edt_homens, 1);
            lnr_lyt_categoria.addView(txt_homens, 2);
            lnr_lyt_categoria.addView(edt_mulheres, 3);
            lnr_lyt_categoria.addView(txt_mulheres, 4);

            linearLayout.addView(lnr_lyt_categoria);
        }

    }



}
