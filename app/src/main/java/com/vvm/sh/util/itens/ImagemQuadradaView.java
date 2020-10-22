package com.vvm.sh.util.itens;

import android.content.Context;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatImageView;

public class ImagemQuadradaView extends AppCompatImageView {

    public ImagemQuadradaView(Context context) {
        super(context);
    }

    public ImagemQuadradaView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ImagemQuadradaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }
}