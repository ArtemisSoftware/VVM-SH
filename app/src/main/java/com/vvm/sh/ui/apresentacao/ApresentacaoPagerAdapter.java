package com.vvm.sh.ui.apresentacao;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.viewpager.widget.PagerAdapter;

import com.vvm.sh.BuildConfig;
import com.vvm.sh.R;
import com.vvm.sh.ui.apresentacao.modelos.Introducao;

public class ApresentacaoPagerAdapter extends PagerAdapter {


    private LayoutInflater inflater;
    private Introducao [] paginas;
    private Activity contexto;

    public ApresentacaoPagerAdapter(Activity contexto, Introducao[] paginas) {

        this.contexto = contexto;
        this.paginas = paginas;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {

        inflater = (LayoutInflater) contexto.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(R.layout.content_apresentacao, container, false);
        container.addView(view);

        //((RelativeLayout) view.findViewById(R.id.rlt_lyt_content)).setBackgroundColor(contexto.getResources().getColor(paginas[position].obterCorFundo()));

        //TODO: nao gosto de como aparece o conteudo do txt_sub_titulo

        ((TextView) view.findViewById(R.id.txt_sub_titulo)).setText(BuildConfig.VERSION_NAME);
        ((TextView) view.findViewById(R.id.txt_titulo)).setText(paginas[position].titulo);
        ((ImageView) view.findViewById(R.id.img_logo)).setImageDrawable(contexto.getDrawable(paginas[position].imagem));
        ((TextView) view.findViewById(R.id.txt_texto)).setText(paginas[position].texto);

        return view;
    }

    @Override
    public int getCount() {
        return paginas.length;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View v = (View) object;
        container.removeView(v);
    }

    @Override
    public boolean isViewFromObject(View v, Object object) {
        return v == object;
    }
}
