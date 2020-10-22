package com.vvm.sh.util.metodos;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Base64;

import androidx.core.content.FileProvider;

import com.vvm.sh.util.constantes.ImagemConstantes;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

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
     * Metodo que converte um byte[] para string
     * @param imagem a imagem a converter
     * @return uma string representativa do byte[]
     */
    public static String converterByteArray(byte[] imagem){

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        converter(imagem).compress(Bitmap.CompressFormat.PNG,100, byteArrayOutputStream);
        byte [] dados = byteArrayOutputStream.toByteArray();
        String resultado = Base64.encodeToString(dados, Base64.DEFAULT);
        return resultado;
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


    public static void abrirCamera(Activity contexto){
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        cameraIntent.putExtra(ImagemConstantes.INTENT_IMAGE_PICKER_OPTION, ImagemConstantes.REQUEST_IMAGE_CAPTURE);

        // setting aspect ratio
        cameraIntent.putExtra(ImagemConstantes.INTENT_LOCK_ASPECT_RATIO, true);
        cameraIntent.putExtra(ImagemConstantes.INTENT_ASPECT_RATIO_X, 1); // 16x9, 1x1, 3:4, 3:2
        cameraIntent.putExtra(ImagemConstantes.INTENT_ASPECT_RATIO_Y, 1);

        // setting maximum bitmap width and height
        cameraIntent.putExtra(ImagemConstantes.INTENT_SET_BITMAP_MAX_WIDTH_HEIGHT, true);
        cameraIntent.putExtra(ImagemConstantes.INTENT_BITMAP_MAX_WIDTH, 1000);
        cameraIntent.putExtra(ImagemConstantes.INTENT_BITMAP_MAX_HEIGHT, 1000);




        Uri mOutputUri = FileProvider.getUriForFile(
                contexto,
                "com.vvm.sh.provider",
                getOutputMediaFile());


        //mOutputUri = Uri.fromFile(getOutputMediaFile());
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, mOutputUri);
        contexto.startActivityForResult(cameraIntent, 1);
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = DiretoriasUtil.obterDiretoria(DiretoriasUtil.DIRETORIA_IMAGENS);

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                return null;
            }
        }

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ timeStamp + ".jpg");
    }
}
