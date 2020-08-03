package com.vvm.sh.databinding;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.vvm.sh.R;
import com.vvm.sh.util.metodos.ImagemUtil;

public class GeralBinding {

    @BindingAdapter({"imagem"})
    public static void setImagem(ImageView view, byte[] imagem) {

        if(imagem == null){
            return;
        }

        view.setImageBitmap(Bitmap.createScaledBitmap(ImagemUtil.converter(imagem), 900, 300, false));

    }

    @BindingAdapter({"imagem", "omissao"})
    public static void setImagem(ImageView view, byte[] imagem, int omissao) {

        if(imagem == null){
            view.setBackgroundResource(omissao);
            return;
        }

        view.setImageBitmap(Bitmap.createScaledBitmap(ImagemUtil.converter(imagem), 900, 300, false));

    }


    @BindingAdapter({"imagem"})
    public static void setImagem(ImageView view, int imagem) {

        Context context = view.getContext();

        RequestOptions options = new RequestOptions()
                //.placeholder(defaultImageUrl)
                .error(R.drawable.ic_launcher_foreground);

        Glide.with(context)
                .setDefaultRequestOptions(options)
                .load(imagem)
                .into(view);

    }



}
