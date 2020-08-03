package com.vvm.sh.util.metodos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class ImagemUtil {

    /**
     * Metodo que permite converter um byte array para um bitmap
     * @param imagem o array
     * @return um bitmap
     */
    public static Bitmap converter(byte[] imagem){

        return BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
    }
}
