package com.vvm.sh.ui.imagens;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import com.vvm.sh.R;
import com.vvm.sh.databinding.ActivityGaleriaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.imagens.adaptadores.OnImagemListener;
import com.vvm.sh.ui.imagens.modelos.Galeria;
import com.vvm.sh.ui.imagens.modelos.ImagemRegisto;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class GaleriaActivity extends BaseDaggerActivity implements OnImagemListener {


    private ActivityGaleriaBinding activityGaleriaBinding;


    @Inject
    ViewModelProviderFactory providerFactory;


    private ImagemViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ImagemViewModel.class);

        activityGaleriaBinding = (ActivityGaleriaBinding) activityBinding;
        activityGaleriaBinding.setLifecycleOwner(this);
        activityGaleriaBinding.setListener(this);
        activityGaleriaBinding.setViewmodel(viewModel);


        subscreverObservadores();

        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            Galeria galeria = bundle.getParcelable(getString(R.string.argumento_galeria));
            viewModel.obterGaleria(galeria);
        }
        else{
            finish();
        }
    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_galeria;
    }

    @Override
    protected BaseViewModel obterBaseViewModel() {
        return viewModel;
    }

    @Override
    protected void subscreverObservadores() {

        viewModel.observarMessagem().observe(this, new Observer<Recurso>() {
            @Override
            public void onChanged(Recurso recurso) {

                switch (recurso.status){

                    case SUCESSO:

                        dialogo.sucesso(recurso.messagem);
                        break;

                    case ERRO:

                        dialogo.erro(recurso.messagem);
                        break;

                }
            }
        });
    }

    @Override
    public void OnImagemClick(ImagemRegisto imagem) {

        Bundle bundle = getIntent().getExtras();
        Galeria galeria = bundle.getParcelable(getString(R.string.argumento_galeria));
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        if(galeria.idGaleria == Galeria.GALERIA_CAPA_RELATORIO){
            viewModel.gravarCapaRelatorio(PreferenciasUtil.obterIdTarefa(this), idAtividade, imagem.imagem.idImagem);
        }
    }

    @Override
    public void OnImagemLongClick(ImagemRegisto imagem) {

        Bundle bundle = getIntent().getExtras();
        Galeria galeria = bundle.getParcelable(getString(R.string.argumento_galeria));
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        if(galeria.idGaleria == Galeria.GALERIA_CAPA_RELATORIO){
            viewModel.removerCapaRelatorio(PreferenciasUtil.obterIdTarefa(this), idAtividade);
        }
        else{
            viewModel.removerImagem(PreferenciasUtil.obterIdTarefa(this), idAtividade, imagem.imagem);
        }
    }
}