package com.vvm.sh.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.github.gcacace.signaturepad.views.SignaturePad;
import com.vvm.sh.R;
import com.vvm.sh.util.metodos.ImagemUtil;

import java.io.ByteArrayOutputStream;

import butterknife.BindView;
import butterknife.OnClick;

public class AssinaturaActivity extends BaseActivity implements SignaturePad.OnSignedListener {

    @BindView(R.id.sgn_pad_assinatura)
    SignaturePad sgn_pad_assinatura;

    @BindView(R.id.btn_limpar)
    Button btn_limpar;

    @BindView(R.id.btn_gravar)
    Button btn_gravar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assinatura);

        //--verifyStoragePermissions(this);
        sgn_pad_assinatura.setOnSignedListener(this);

    }



    @Override
    public void onStartSigning() {

    }

    @Override
    public void onSigned() {

        btn_gravar.setEnabled(true);
        btn_limpar.setEnabled(true);
    }

    @Override
    public void onClear() {

        btn_gravar.setEnabled(false);
        btn_limpar.setEnabled(false);
    }




    @OnClick(R.id.btn_limpar)
    public void btn_limpar_OnClickListener(View view) {
        sgn_pad_assinatura.clear();
    }



    @OnClick(R.id.btn_gravar)
    public void btn_gravar_OnClickListener(View view) {

        Bitmap signatureBitmap = sgn_pad_assinatura.getSignatureBitmap();

        Intent returnIntent = new Intent();
        returnIntent.putExtra(getString(R.string.resultado_imagem), ImagemUtil.converter(signatureBitmap));
        setResult(RESULT_OK,returnIntent);
        finish();
    }


    /*


    public File getAlbumStorageDir(String albumName) {
        // Get the directory for the user's public pictures directory.
        File file = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), albumName);
        if (!file.mkdirs()) {
            Log.e("SignaturePad", "Directory not created");
        }
        return file;
    }

    public void saveBitmapToJPG(Bitmap bitmap, File photo) throws IOException {
        Bitmap newBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(newBitmap);
        canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        OutputStream stream = new FileOutputStream(photo);
        newBitmap.compress(Bitmap.CompressFormat.JPEG, 80, stream);
        stream.close();
    }

    public boolean addJpgSignatureToGallery(Bitmap signature) {
        boolean result = false;
        try {
            File photo = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.jpg", System.currentTimeMillis()));
            saveBitmapToJPG(signature, photo);
            scanMediaFile(photo);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanMediaFile(File photo) {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        Uri contentUri = Uri.fromFile(photo);
        mediaScanIntent.setData(contentUri);
        MainActivity.this.sendBroadcast(mediaScanIntent);
    }

    public boolean addSvgSignatureToGallery(String signatureSvg) {
        boolean result = false;
        try {
            File svgFile = new File(getAlbumStorageDir("SignaturePad"), String.format("Signature_%d.svg", System.currentTimeMillis()));
            OutputStream stream = new FileOutputStream(svgFile);
            OutputStreamWriter writer = new OutputStreamWriter(stream);
            writer.write(signatureSvg);
            writer.close();
            stream.flush();
            stream.close();
            scanMediaFile(svgFile);
            result = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        int permission = ActivityCompat.checkSelfPermission(activity, Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }

     */
}
