package com.vvm.sh.util.metodos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class ImagemUtil {

    /**
     * Metodo que permite converter um byte array para um bitmap
     * @param imagem o array
     * @return um bitmap
     */
    public static Bitmap converter(byte[] imagem){

        return BitmapFactory.decodeByteArray(imagem, 0, imagem.length);
    }


    /**
     * Metodo que permite converter um bitmap para um byte array
     * @param imagem o bitmap
     * @return um byte array
     */
    public static byte[] converter(Bitmap imagem){

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        imagem.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        imagem.recycle();

        return byteArray;
    }
}
