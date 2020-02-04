package com.vvm.sh.apresentacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

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

/*    TextView[] bottomBars;
    Button Skip, Next;
*/

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

        screens = new Slider[]{

                new Slider("Screen 0", colorsActive[0], colorsInactive[0],  R.layout.intro_screen, R.color.screen1),
                new Slider("Screen 1", colorsActive[1], colorsInactive[1],  R.layout.intro_screen, R.color.screen2),
                new Slider("Screen 2", colorsActive[2], colorsInactive[2],  R.layout.intro_screen, R.color.screen3)
        };
*/
        apresentacaoViewPagerAdapter = new ApresentacaoPagerAdapter(this, paginas);
        view_pager_conteudo.setAdapter(apresentacaoViewPagerAdapter);
        view_pager_conteudo.addOnPageChangeListener(viewPagerPageChangeListener);

    }

    /*


    public void skip(View view) {
        launchMain();
    }
*/
    private void ColoredBars(int thisScreen) {

        /*
        int[] colorsInactive = getResources().getIntArray(R.array.dot_on_page_not_active);
        int[] colorsActive = getResources().getIntArray(R.array.dot_on_page_active);
        bottomBars = new TextView[screens.length];

        Layout_bars.removeAllViews();

        for (int i = 0; i < bottomBars.length; i++) {

            bottomBars[i] = new TextView(this);
            bottomBars[i].setTextSize(100);
            bottomBars[i].setText(Html.fromHtml("¯"));
            Layout_bars.addView(bottomBars[i]);
            bottomBars[i].setTextColor(colorsInactive[thisScreen]);
        }
        if (bottomBars.length > 0)
            bottomBars[thisScreen].setTextColor(colorsActive[thisScreen]);
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
