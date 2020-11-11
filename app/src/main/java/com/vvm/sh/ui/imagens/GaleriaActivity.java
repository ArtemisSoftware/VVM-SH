package com.vvm.sh.ui.imagens;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.databinding.ActivityGaleriaBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.imagens.adaptadores.OnImagemListener;
import com.vvm.sh.ui.imagens.modelos.Galeria;
import com.vvm.sh.ui.imagens.modelos.ImagemRegisto;
import com.vvm.sh.ui.opcoes.TiposActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.ImagemConstantes;
import com.vvm.sh.util.constantes.Sintaxe;
import com.vvm.sh.util.metodos.ImagemUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.metodos.TiposUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.io.IOException;

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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ImagemConstantes.REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

                    Bundle bundle = getIntent().getExtras();
                    int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

                    ImagemResultado imagemResultado = new ImagemResultado(PreferenciasUtil.obterIdTarefa(this), idAtividade, Identificadores.Imagens.IMAGEM_CAPA_RELATORIO, ImagemUtil.converter(bitmap));

                    viewModel.gravarImagem(PreferenciasUtil.obterIdTarefa(this), idAtividade, imagemResultado);
                }
                catch (IOException e) {
                    dialogo.alerta(Sintaxe.Palavras.IMAGEM , Sintaxe.Alertas.ERRO_GERAR_IMAGEM + e.getMessage());
                }
            }
        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_imagens, menu);

        Bundle bundle = getIntent().getExtras();
        Galeria galeria = bundle.getParcelable(getString(R.string.argumento_galeria));

        if(galeria.idGaleria != Galeria.GALERIA_CAPA_RELATORIO){
            MenuItem item = menu.findItem(R.id.item_pesquisa);
            item.setVisible(false);
        }

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Bundle bundle = getIntent().getExtras();

        switch (item.getItemId()){


            case R.id.item_pesquisa:

                ImagemUtil.apresentarOpcoesCaptura(this);
                break;

            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }



}