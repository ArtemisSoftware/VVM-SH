package com.vvm.sh.ui.atividadesPendentes.relatorios.checklist;

import androidx.annotation.Nullable;
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
import com.vvm.sh.baseDados.entidades.QuestionarioChecklistResultado;
import com.vvm.sh.databinding.ActivityQuestoesChecklistBinding;
import com.vvm.sh.di.ViewModelProviderFactory;
import com.vvm.sh.ui.BaseDaggerActivity;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.adaptadores.OnChecklistListener;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Item;
import com.vvm.sh.ui.atividadesPendentes.relatorios.checklist.modelos.Questao;
import com.vvm.sh.ui.imagens.GaleriaActivity;
import com.vvm.sh.ui.imagens.modelos.Galeria;
import com.vvm.sh.util.Recurso;
import com.vvm.sh.util.constantes.Identificadores;
import com.vvm.sh.util.constantes.ImagemConstantes;
import com.vvm.sh.util.constantes.Sintaxe;
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


    private Questao questaoFoto;


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

            QuestionarioChecklistResultado registo = new QuestionarioChecklistResultado(item, questaoFoto.registo.uid);
            ImagemResultado resultado = new ImagemResultado(PreferenciasUtil.obterIdTarefa(this), item.id, Identificadores.Imagens.IMAGEM_CHECKLIST, ImagemUtil.converter(bitmap));

            viewModel.gravar(PreferenciasUtil.obterIdTarefa(this), idAtividade, questaoFoto.id, registo, resultado);
        }
        catch (IOException e) {
            dialogo.alerta(Sintaxe.Palavras.IMAGEM , Sintaxe.Alertas.ERRO_GERAR_IMAGEM + e.getMessage());
        }

    }


    //--------------------
    //EVENTOS
    //--------------------


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
    public void OnGaleriaClick(Questao questao) {
        Galeria galeria = new Galeria(Identificadores.Imagens.IMAGEM_CHECKLIST, questao.id);

        Intent intent = new Intent(this, GaleriaActivity.class);
        intent.putExtra(getString(R.string.argumento_galeria), galeria);
        startActivity(intent);
    }

    @Override
    public void OnRegistarFoto(Questao questao) {

        questaoFoto = questao;
        ImagemUtil.apresentarOpcoesCaptura(this);
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
