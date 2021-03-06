package com.vvm.sh.util.metodos;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;
import android.widget.Toast;

import androidx.coordinatorlayout.widget.CoordinatorLayout;

import com.andrognito.flashbar.Flashbar;
import com.google.android.material.snackbar.Snackbar;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.vvm.sh.R;
import com.vvm.sh.util.interfaces.OnPermissaoConcedidaListener;

import java.util.List;

public class PermissoesUtil {


    private static final String[] PERMISSOES_ESCRITA_LEITURA = new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    /**
     * Metodo que permite pedir as permissoes de escrita e leitura
     * @param contexto
     * @param listener interface a executar quando a permissao é concedida
     */
    public static void pedirPermissoesEscritaLeitura(final Activity contexto, OnPermissaoConcedidaListener listener) {
        pedirPermissoesApp(contexto, PERMISSOES_ESCRITA_LEITURA, listener);
    }



    private static final String[] PERMISSOES_IMAGEM = new String[]{
            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


    /**
     * Metodo que permite pedir as permissoes para imagens
     * @param contexto
     * @param listener interface a executar quando a permissao é concedida
     */
    public static void pedirPermissoesImagem(final Activity contexto, OnPermissaoConcedidaListener listener) {
        pedirPermissoesApp(contexto, PERMISSOES_IMAGEM, listener);
    }


    private static final String[] PERMISSOES_APP = new String[]{

            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA
            //Manifest.permission.ACCESS_FINE_LOCATION

    };


    /**
     * Metodo que permite pedir as permissões da api
     * @param contexto
     */
    public static void pedirPermissoesApp(final Activity contexto) {
        pedirPermissoesApp(contexto, PERMISSOES_APP, null);
    }












    /**
     * Metodo que permite pedir/verificar se uma permissão esta concedida
     * @param contexto
     * @param permissao o nome da permissao a pedir
     * @param listener interface a executar quando a permissao é concedida
     */
    private static void pedirPermissaoApp(final Activity contexto, String permissao, OnPermissaoConcedidaListener listener){

        Dexter.withActivity(contexto)
                .withPermission(permissao)
                .withListener(
                        new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                if(listener != null) {
                                    listener.executar();
                                }
                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                dialogoPermissoes(contexto);
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        }
                )
                .withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Flashbar flashbar = new Flashbar.Builder(contexto)
                                .gravity(Flashbar.Gravity.BOTTOM)
                                .title("Erro nas permissoes")
                                .duration(4000)
                                .message(error.name())
                                .backgroundColorRes(R.color.colorPrimaryDark)
                                .build();
                        flashbar.show();

                    }
                })
                .onSameThread()
                .check();
    }


    /**
     * Metodo que permite pedir/verificar se um conjunto de permissões foi concedida
     * @param contexto
     * @param permissoes a lista de permissoes
     * @param listener interface a executar quando a permissao é concedida
     */
    private static void pedirPermissoesApp(final Activity contexto, String[] permissoes, OnPermissaoConcedidaListener listener) {

        Dexter.withActivity(contexto)
                .withPermissions(permissoes)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {

                            if(listener != null) {
                                listener.executar();
                            }
                        }

                        // check for permanent denial of any permission
                        else if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            dialogoPermissoes(contexto);
                        }
                        else {
                            dialogoPermissoes(contexto);
                        }
                    }


                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {
                        token.continuePermissionRequest();
                    }
                }).
                withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError error) {
                        Flashbar flashbar = new Flashbar.Builder(contexto)
                                .gravity(Flashbar.Gravity.BOTTOM)
                                .title(contexto.getString(R.string.erro_permissoes))
                                .duration(4000)
                                .message(error.name())
                                .backgroundColorRes(R.color.colorPrimaryDark)
                                .build();
                        flashbar.show();

                    }
                })
                .onSameThread()
                .check();
    }





    /**
     * Metodo que permite abrir um dialogo que permite aceder às definiçoes de forma a garantir permissões
     * @param contexto
     */
    private static void dialogoPermissoes(final Activity contexto) {

        AlertDialog.Builder builder = new AlertDialog.Builder(contexto);
        builder.setTitle(contexto.getString(R.string.permissoes));
        builder.setMessage(contexto.getString(R.string.permissao_necessaria_aceder_definicoes));

        builder.setPositiveButton(contexto.getString(R.string.definicoes), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                abrirDefinicoes(contexto);
            }
        });
        builder.setNegativeButton(contexto.getString(R.string.cancelar), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }



    /**
     * Metodo que permite abrir as definições
     * @param contexto
     */
    private static void abrirDefinicoes(Activity contexto) {

        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", contexto.getPackageName(), null);
        intent.setData(uri);
        contexto.startActivityForResult(intent, 101);
    }
}
