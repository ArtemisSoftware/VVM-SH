package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

import com.vvm.sh.R;
import com.vvm.sh.baseDados.entidades.ImagemResultado;
import com.vvm.sh.databinding.ActivityQuestoesChecklistBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores.OnChecklistListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;
import com.vvm.sh.ui.imagens.BibliotecaImagensActivity;
import com.vvm.sh.ui.imagens.ImagemActivity;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.ImagemConstantes;
import com.vvm.sh.util.metodos.ImagemUtil;
import com.vvm.sh.util.metodos.PreferenciasUtil;
import com.vvm.sh.util.viewmodel.BaseViewModel;

import java.io.IOException;

import javax.inject.Inject;

import butterknife.OnClick;

public class QuestoesChecklistActivity extends BaseDaggerActivity
        implements OnChecklistListener.OnQuestaoListener {



    private ActivityQuestoesChecklistBinding activityQuestoesChecklistBinding;

    @Inject
    ViewModelProviderFactory providerFactory;


    private ChecklistViewModel viewModel;


    @Override
    protected void intActivity(Bundle savedInstanceState) {

        viewModel = ViewModelProviders.of(this, providerFactory).get(ChecklistViewModel.class);

        activityQuestoesChecklistBinding = (ActivityQuestoesChecklistBinding) activityBinding;
        activityQuestoesChecklistBinding.setLifecycleOwner(this);
        activityQuestoesChecklistBinding.setListener(this);
        activityQuestoesChecklistBinding.setViewmodel(viewModel);

        activityQuestoesChecklistBinding.setBloquear(PreferenciasUtil.agendaEditavel(this));

        subscreverObservadores();


        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {

            Item item = bundle.getParcelable(getString(R.string.argumento_registo_area));
            viewModel.obterQuestoes(item);
        }
        else{
            finish();
        }

    }

    @Override
    protected int obterLayout() {
        return R.layout.activity_questoes_checklist;
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


    //----------------------
    //Metodos locais
    //----------------------


    /**
     * Metodo que permite gravar uma imagem
     * @param uri os dados da imagem
     */
    private void gravarImagem(Uri uri) {

        try {
            // You can update this bitmap to your server
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);

            Bundle bundle = getIntent().getExtras();
            Item item = bundle.getParcelable(getString(R.string.argumento_registo_area));
            int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

            ImagemResultado resultado = new ImagemResultado(PreferenciasUtil.obterIdTarefa(this), item.id, Identificadores.Imagens.IMAGEM_CHECKLIST, ImagemUtil.converter(bitmap));

            viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), idAtividade, resultado);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }


    //--------------------
    //EVENTOS
    //--------------------


    @OnClick({R.id.fab_nao_aplicavel})
    public void fab_nao_aplicavel_OnClickListener(View view) {

//        Intent intent = new Intent(this, DadosClienteActivity.class);
//        startActivity(intent);
    }

    @Override
    public void OnPerguntaClick(Questao questao) {

        Bundle bundle = getIntent().getExtras();
        Item item = bundle.getParcelable(getString(R.string.argumento_registo_area));
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        DialogoPergunta dialogo = DialogoPergunta.newInstance(item, idAtividade, questao.registo.uid, questao.registo.tipo, questao.id);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnObservacaoClick(Questao questao) {

        Bundle bundle = getIntent().getExtras();
        Item item = bundle.getParcelable(getString(R.string.argumento_registo_area));
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        DialogoPergunta dialogo = DialogoPergunta.newInstance(item, idAtividade, questao.registo.uid, questao.registo.tipo, questao.id);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnUtClick(Questao questao, int numeroUt) {

        Bundle bundle = getIntent().getExtras();
        Item item = bundle.getParcelable(getString(R.string.argumento_registo_area));
        int idAtividade = bundle.getInt(getString(R.string.argumento_id_atividade));

        DialogoPergunta dialogo = DialogoPergunta.newInstance(item, idAtividade, questao.registo.uid, questao.registo.tipo, questao.id, numeroUt);
        dialogo.show(getSupportFragmentManager(), "example dialog");
    }

    @Override
    public void OnGaleriaClick() {
        Intent intent = new Intent(this, BibliotecaImagensActivity.class);
        startActivity(intent);
    }

    @Override
    public void OnRegistarFoto() {
        showImagePickerOptions();
    }



    private void showImagePickerOptions() {
        ImagemActivity.showImagePickerOptions(this, new ImagemActivity.PickerOptionListener() {
            @Override
            public void onTakeCameraSelected() {
                launchCameraIntent();
            }

            @Override
            public void onChooseGallerySelected() {
                launchGalleryIntent();
            }
        });
    }

    private void launchCameraIntent() {
        Intent intent = new Intent(this, ImagemActivity.class);
        intent.putExtra(ImagemActivity.INTENT_IMAGE_PICKER_OPTION, ImagemConstantes.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        intent.putExtra(ImagemActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagemActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagemActivity.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        intent.putExtra(ImagemActivity.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        intent.putExtra(ImagemActivity.INTENT_BITMAP_MAX_WIDTH, 1000);
        intent.putExtra(ImagemActivity.INTENT_BITMAP_MAX_HEIGHT, 1000);

        startActivityForResult(intent, ImagemConstantes.REQUEST_IMAGE);
    }

    private void launchGalleryIntent() {
        Intent intent = new Intent(this, ImagemActivity.class);
        intent.putExtra(ImagemActivity.INTENT_IMAGE_PICKER_OPTION, ImagemConstantes.REQUEST_GALLERY_IMAGE);

        // setting aspect ratio
        intent.putExtra(ImagemActivity.INTENT_LOCK_ASPECT_RATIO, true);
        intent.putExtra(ImagemActivity.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        intent.putExtra(ImagemActivity.INTENT_ASPECT_RATIO_Y, 1);
        startActivityForResult(intent, ImagemConstantes.REQUEST_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImagemConstantes.REQUEST_IMAGE) {
            if (resultCode == RESULT_OK) {
                Uri uri = data.getParcelableExtra("path");

                gravarImagem(uri);



            }
        }
    }




}
