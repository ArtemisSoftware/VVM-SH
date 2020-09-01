package com.vvm.sh.ui.apresentacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.ui.apresentacao.modelos.Introducao;
import com.vvm.sh.ui.apresentacao.modelos.IntroducaoFactory;
import com.vvm.sh.util.constantes.AppConfig;
import com.vvm.sh.util.constantes.Apresentacoes;
import com.vvm.sh.util.metodos.PreferenciasUtil;

import at.markushi.ui.CircleButton;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApresentacaoActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    @BindView(R.id.view_pager_conteudo)
    ViewPager view_pager_conteudo;

    @BindView(R.id.lnr_lyt_apresentacao)
    LinearLayout lnr_lyt_apresentacao;

    @BindView(R.id.lnr_lyt_progresso)
    LinearLayout lnr_lyt_progresso;


    @BindView(R.id.crl_saltar)
    CircleButton crl_saltar;





    private ApresentacaoPagerAdapter apresentacaoViewPagerAdapter;

    private Introducao[] paginas;

    private Handler handler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        ButterKnife.bind(this);

        obterApresentacao();
    }


    //------------------------
    //Metodos locais
    //------------------------


    /**
     * Metodo que permite obter as paginas necessárias para realizar a apresentacao
     */
    private void obterApresentacao(){

        if(PreferenciasUtil.obterPrimeiraUtilizacao(this) == true){

            PreferenciasUtil.fixarPrimeiraUtilizacao(this, false);

            iniciarApresentacao(Apresentacoes.PAGINAS_BOAS_VINDAS);

            handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {

                    terminarApresentacao();
                }
            }, AppConfig.TEMPO_SPLASH);

        }
        else{

            lnr_lyt_apresentacao.setVisibility(View.VISIBLE);
            iniciarApresentacao(Apresentacoes.PAGINAS_ATUALIZACAO);
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
        view_pager_conteudo.addOnPageChangeListener(this);
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


    /**
     * Metodo que permite terminar a apresentacao
     */
    private void terminarApresentacao() {
        finish();
    }



    //-------------------
    //Eventos
    //-------------------


    @OnClick({R.id.crl_saltar})
    public void crl_saltar_ButtonClick(View view) {
        terminarApresentacao();
    }



    @OnClick({R.id.crl_prosseguir})
    public void crl_prosseguir_ButtonClick(View view) {

        int index = view_pager_conteudo.getCurrentItem() + 1;

        if (index < paginas.length) {
            view_pager_conteudo.setCurrentItem(index);
        }
        else {
            terminarApresentacao();
        }
    }


    @Override
    public void onPageSelected(int position) {
        registarProgresso(position);

        if (position == paginas.length - 1) {
            crl_saltar.setVisibility(View.INVISIBLE);
        }
        else {
            crl_saltar.setVisibility(View.VISIBLE);
        }
    }



    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }



    @Override
    public void onPageScrollStateChanged(int state) {

    }
}
