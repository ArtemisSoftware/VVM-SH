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

public class Permissoes {

    private static final String[] PERMISSOES_APP = new String[]{
            /*
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_FINE_LOCATION
            */
    };

/*
    private static final String[] IMAGE_PERMISSIONS = new String[]{
            Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
*/


    /**
     * Metodo que permite pedir/verificar se uma permissão esta concedida
     * @param contexto
     * @param permissao o nome da permissao a pedir
     * @param listener interface a executar quando a permissao é concedida
     */
    public static void pedirPermissaoApp(final Activity contexto, String permissao, OnPermissaoConcedidaListener listener){

        Dexter.withActivity(contexto)
                .withPermission(permissao)
                .withListener(
                        new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                listener.executar();
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
     * Metodo que permite pedir as permissões da app
     * @param contexto
     */
    public static void pedirPermissoesApp(final Activity contexto) {

        Dexter.withActivity(contexto)
                .withPermissions(PERMISSOES_APP)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                        // check if all permissions are granted
                        if (report.areAllPermissionsGranted()) {
                            //--Snackbar.make(contexto.getCurrentFocus(), "Welcome to AndroidHive", Snackbar.LENGTH_LONG).show();
                            //--Toast.makeText(context, "All permissions are granted!", Toast.LENGTH_SHORT).show();
                        }

                        // check for permanent denial of any permission
                        else if (report.isAnyPermissionPermanentlyDenied()) {
                            // show alert dialog navigating to Settings
                            dialogoPermissoes(contexto);
                        }
                        else {

                            //--Snackbar.make(coordinatorLayout, "Welcome to AndroidHive", Snackbar.LENGTH_LONG).show();
                            //--Toast.makeText(context, "All permissions are not granted..", Toast.LENGTH_SHORT).show();
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
