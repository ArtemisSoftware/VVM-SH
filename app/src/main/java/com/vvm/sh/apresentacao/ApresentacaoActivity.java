package com.vvm.sh.apresentacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.apresentacao.modelos.Apresentacao;
import com.vvm.sh.apresentacao.modelos.Atualizacao;
import com.vvm.sh.apresentacao.modelos.Correcao;
import com.vvm.sh.apresentacao.modelos.Funcionalidade;
import com.vvm.sh.util.Introducao;
import com.vvm.sh.util.IntroducaoFactory;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApresentacaoActivity extends AppCompatActivity {

    @BindView(R.id.view_pager_conteudo)
    ViewPager view_pager_conteudo;

    @BindView(R.id.lnr_lyt_apresentacao)
    LinearLayout lnr_lyt_apresentacao;

    @BindView(R.id.lnr_lyt_iniciar)
    LinearLayout lnr_lyt_iniciar;

    @BindView(R.id.lnr_lyt_progresso)
    LinearLayout lnr_lyt_progresso;



    @BindView(R.id.img_saltar)
    ImageView img_saltar;





    private ApresentacaoPagerAdapter apresentacaoViewPagerAdapter;

    private Introducao[] paginas;

    boolean primeiraUtilizacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        ButterKnife.bind(this);

        subscreverObservadores();

        obterApresentacao();
    }


    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite subscrever observadores
     */
    private void subscreverObservadores(){


        //TODO: subscrever observadores do viewmodel

    }


    /**
     * Metodo que permite obter as paginas necessárias para realizar a apresentacao
     */
    private void obterApresentacao(){

        primeiraUtilizacao = true;//preferenceManager.setFirstTimeLaunch(false, versao atual);

        if(primeiraUtilizacao == true){

            lnr_lyt_iniciar.setVisibility(View.VISIBLE);

            Introducao[] paginas = new Introducao[]{
                    new Apresentacao(-1, "Empresa","Bem vindo a app vvm.sh."),
            };

            iniciarApresentacao(paginas);
        }
        else{

            lnr_lyt_apresentacao.setVisibility(View.VISIBLE);

            //TODO: chamar metodo do viewmodel para obter apresentacao

            Introducao[] paginas = new Introducao[]{

                    new Correcao("Correção da  funcionalidade abc"),
                    new Funcionalidade("Adicionada a funcionalidade 1"),
                    new Atualizacao("Atualizado o funcionamento da funcionalidade 0"),

            };

            iniciarApresentacao(paginas);
        }
    }


    /**
     * Metodo que permite iniciar a apresentacao
     * @param paginas paginas a apresentar
     */
    private void iniciarApresentacao(Introducao[] paginas) {

        IntroducaoFactory introducaoFactory = new IntroducaoFactory();

        this.paginas = paginas;
        apresentacaoViewPagerAdapter = new ApresentacaoPagerAdapter(this, this.paginas);
        view_pager_conteudo.setAdapter(apresentacaoViewPagerAdapter);
        view_pager_conteudo.addOnPageChangeListener(viewPagerPageChangeListener);
        registarProgresso(0);
    }


    /**
     * Metodo que permite registar o progresso da apresentacao
     * @param posicao a posicao da apresentacao a apresentada
     */
    private void registarProgresso(int posicao) {

        TextView[] barrasProgresso = new TextView[paginas.length];

        lnr_lyt_progresso.removeAllViews();

        for (int i = 0; i < barrasProgresso.length; i++) {

            barrasProgresso[i] = new TextView(this);
            barrasProgresso[i].setWidth(10);
            barrasProgresso[i].setHeight(10);
            barrasProgresso[i].setBackground(this.getDrawable(R.drawable.ic_appintro_indicator_unselected));
            lnr_lyt_progresso.addView(barrasProgresso[i]);
        }
        if (barrasProgresso.length > 0)
            barrasProgresso[posicao].setBackground(this.getDrawable(R.drawable.ic_appintro_indicator_selected));
    }




    private void iniciarApp() {

        //preferenceManager.setFirstTimeLaunch(false, versao atual);

        if(primeiraUtilizacao == true){

            //startActivity(new Intent(MainScreen.this, MainActivity.class));
        }

        finish();
    }



    //-------------------
    //Eventos
    //-------------------


    @OnClick(R.id.img_prosseguir)
    public void img_prosseguir_ButtonClick(View view) {

        int index = view_pager_conteudo.getCurrentItem() + 1;

        if (index < paginas.length) {
            view_pager_conteudo.setCurrentItem(index);
        }
        else {
            iniciarApp();
        }
    }


    @OnClick({R.id.img_saltar, R.id.btn_iniciar})
    public void iniciarButtonClick(View view) {
        iniciarApp();
    }


    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            registarProgresso(position);


            if (position == paginas.length - 1) {
                img_saltar.setVisibility(View.INVISIBLE);
            }
            else {
                img_saltar.setVisibility(View.VISIBLE);
            }
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

}
