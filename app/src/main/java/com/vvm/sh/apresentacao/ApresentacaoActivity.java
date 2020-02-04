package com.vvm.sh.apresentacao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.LinearLayout;

import com.vvm.sh.R;
import com.vvm.sh.apresentacao.modelos.Slider;

public class ApresentacaoActivity extends AppCompatActivity {

    ViewPager view_pager_conteudo;

    LinearLayout lnr_lyt_progresso;
/*    TextView[] bottomBars;
    Button Skip, Next;
*/

    private ApresentacaoPagerAdapter apresentacaoViewPagerAdapter;

    private Slider[] paginas;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_apresentacao);

        view_pager_conteudo = (ViewPager) findViewById(R.id.view_pager_conteudo);
        lnr_lyt_progresso = (LinearLayout) findViewById(R.id.lnr_lyt_progresso);
        /*Skip = (Button) findViewById(R.id.skip);
        Next = (Button) findViewById(R.id.next);
*/
        initIntroSlider();

        ColoredBars(0);
    }

    private void initIntroSlider() {

/*
        Skip.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                skip(v);
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {


                next(v);

            }
        });
*/

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
    public void next(View v) {
        int i = getItem(+1);
        if (i < screens.length) {
            vp.setCurrentItem(i);
        } else {
            launchMain();
        }
    }

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

    private void launchMain() {
        //preferenceManager.setFirstTimeLaunch(false);
        //startActivity(new Intent(MainScreen.this, MainActivity.class));
        finish();
    }





    ViewPager.OnPageChangeListener viewPagerPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageSelected(int position) {
            ColoredBars(position);

            /*
            if (position == screens.length - 1) {
                Next.setText("start");
                Skip.setVisibility(View.GONE);
            }
            else {
                Next.setText(getString(R.string.next));
                Skip.setVisibility(View.VISIBLE);
            }
            */
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {

        }

        @Override
        public void onPageScrollStateChanged(int arg0) {

        }
    };

}
