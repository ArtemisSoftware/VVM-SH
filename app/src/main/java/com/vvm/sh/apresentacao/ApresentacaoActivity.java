package com.vvm.sh.apresentacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.vvm.sh.R;
import com.vvm.sh.apresentacao.modelos.Slider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ApresentacaoActivity extends AppCompatActivity {

    @BindView(R.id.view_pager_conteudo)
    ViewPager view_pager_conteudo;

    @BindView(R.id.lnr_lyt_progresso)
    LinearLayout lnr_lyt_progresso;



    @BindView(R.id.btn_prosseguir)
    Button btn_prosseguir;

    @BindView(R.id.btn_saltar)
    Button btn_saltar;

    private TextView[] barrasProgresso;

    private ApresentacaoPagerAdapter apresentacaoViewPagerAdapter;

    private Slider[] paginas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        ButterKnife.bind(this);

        iniciarAtividade();

        ColoredBars(0);
    }

    private void iniciarAtividade() {


/*
        int[] colorsInactive = getResources().getIntArray(R.array.dot_on_page_not_active);
        int[] colorsActive = getResources().getIntArray(R.array.dot_on_page_active);
*/
        paginas = new Slider[]{

                new Slider("Screen 0", R.color.blackTextColor, R.color.colorPrimary,  R.layout.content_apresentacao, R.color.colorPrimary),
                new Slider("Screen 1", R.color.blackTextColor, R.color.colorPrimary,  R.layout.content_apresentacao, R.color.colorPrimaryDark),
                new Slider("Screen 2", R.color.blackTextColor, R.color.colorPrimary,  R.layout.content_apresentacao, R.color.colorAccent)
        };

        apresentacaoViewPagerAdapter = new ApresentacaoPagerAdapter(this, paginas);
        view_pager_conteudo.setAdapter(apresentacaoViewPagerAdapter);
        view_pager_conteudo.addOnPageChangeListener(viewPagerPageChangeListener);

    }

    private void ColoredBars(int thisScreen) {


        //int[] colorsInactive = getResources().getIntArray(R.array.dot_on_page_not_active);
        //int[] colorsActive = getResources().getIntArray(R.array.dot_on_page_active);
        barrasProgresso = new TextView[paginas.length];

        lnr_lyt_progresso.removeAllViews();

        for (int i = 0; i < barrasProgresso.length; i++) {

            barrasProgresso[i] = new TextView(this);
            barrasProgresso[i].setWidth(35);
            barrasProgresso[i].setHeight(5);
            barrasProgresso[i].setBackground(this.getDrawable(R.drawable.onboarding_dotunselected));
            lnr_lyt_progresso.addView(barrasProgresso[i]);
            //barrasProgresso[i].setTextColor(Color.RED);
        }
        if (barrasProgresso.length > 0)
            barrasProgresso[thisScreen].setBackground(this.getDrawable(R.drawable.onboarding_dot_selected));

        /*
        lnr_lyt_progresso.removeAllViews();

        for (int i = 0; i < barrasProgresso.length; i++) {

            barrasProgresso[i] = new TextView(this);
            barrasProgresso[i].setTextSize(100);
            barrasProgresso[i].setText(Html.fromHtml("¯"));
            lnr_lyt_progresso.addView(barrasProgresso[i]);
            barrasProgresso[i].setTextColor(Color.RED);
        }
        if (barrasProgresso.length > 0)
            barrasProgresso[thisScreen].setTextColor(Color.BLUE);
*/
    }

    private int getItem(int i) {
        return view_pager_conteudo.getCurrentItem() + i;
    }

    private void iniciarApp() {
        //preferenceManager.setFirstTimeLaunch(false);
        //startActivity(new Intent(MainScreen.this, MainActivity.class));
        finish();
    }



    //-------------------
    //Eventos
    //-------------------


    @OnClick(R.id.btn_prosseguir)
    public void onProsseguirButtonClick(View view) {

        int i = getItem(+1);

        if (i < paginas.length) {
            view_pager_conteudo.setCurrentItem(i);
        }
        else {
            iniciarApp();
        }
    }


    @OnClick(R.id.btn_saltar)
    public void onSaltarButtonClick(View view) {
        iniciarApp();
    }


    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            ColoredBars(position);


            if (position == paginas.length - 1) {
                btn_prosseguir.setText(getString(R.string.iniciar));
                btn_saltar.setVisibility(View.GONE);
            }
            else {
                btn_prosseguir.setText(getString(R.string.avancar));
                btn_saltar.setVisibility(View.VISIBLE);
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
