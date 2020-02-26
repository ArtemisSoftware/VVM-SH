package com.vvm.sh.ui.ocorrencias;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnCheckedChanged;
import butterknife.OnClick;

public class RegistarOcorrenciaActivity extends BaseActivity {


    @BindView(R.id.chk_box_fiscalizado)
    CheckBox chk_box_fiscalizado;

    @BindView(R.id.lnr_lyt_dias)
    LinearLayout lnr_lyt_dias;

    @BindView(R.id.spnr_dias)
    Spinner spnr_dias;

    @BindView(R.id.txt_inp_lyt_dias)
    TextInputLayout txt_inp_lyt_dias;

    @BindView(R.id.txt_inp_observacao)
    TextInputEditText txt_inp_observacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registar_ocorrencia);
    }


    //-----------------
    //Eventos
    //-----------------


    @OnCheckedChanged(R.id.chk_box_fiscalizado)
    void chk_box_fiscalizado_Selected(CompoundButton button, boolean checked) {

        if(checked == true){
            lnr_lyt_dias.setVisibility(View.GONE);
            txt_inp_lyt_dias.setVisibility(View.VISIBLE);
        }
        else{
            lnr_lyt_dias.setVisibility(View.VISIBLE);
            txt_inp_lyt_dias.setVisibility(View.GONE);
        }
    }


    @OnClick(R.id.fab_gravar)
    public void fab_calendario_OnClickListener(View view) {
        //gravar()
    }

}
