package com.vvm.sh.util.metodos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.Settings;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.vvm.sh.R;

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
                            //--Snackbar.make(coordinatorLayout, "Welcome to AndroidHive", Snackbar.LENGTH_LONG).show();
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
                        //--Snackbar.make(coordinatorLayout, "Welcome to AndroidHive", Snackbar.LENGTH_LONG).show();
                        //--Toast.makeText(context, "Error occurred! ", Toast.LENGTH_SHORT).show();
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
