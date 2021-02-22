package com.vvm.sh.ui.registoVisita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import com.vvm.sh.R;
import com.vvm.sh.ui.AssinaturaActivity;
import com.vvm.sh.util.metodos.ImagemUtil;

import butterknife.BindView;
import butterknife.OnClick;

public class AssinaturaRegistoVisitaActivity extends AssinaturaActivity {


    @BindView(R.id.chk_box_homologado)
    CheckBox chk_box_homologado;


    @Override
    protected int obterLayout(){
        return R.layout.activity_assinatura_registo_visita;
    }


    @Override
    protected void btn_gravar_OnClickListener(View view) {

        Bitmap signatureBitmap = sgn_pad_assinatura.getSignatureBitmap();

        Intent returnIntent = new Intent();
        returnIntent.putExtra(getString(R.string.resultado_imagem), ImagemUtil.converter(signatureBitmap));
        returnIntent.putExtra(getString(R.string.resultado_homologado), chk_box_homologado.isChecked());
        setResult(RESULT_OK,returnIntent);
        finish();
    }

}