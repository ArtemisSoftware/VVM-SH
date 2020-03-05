package com.vvm.sh.autenticacao;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.vvm.sh.MainActivity;
import com.vvm.sh.R;
import com.vvm.sh.ui.BaseActivity;

import butterknife.OnClick;

public class AutenticacaoActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_autenticacao);
    }


    private void iniciarApp(){

        /*
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE); // 0 - for private mode
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("token", "string value");
        editor.commit();
        */
    }


    //--------------------
    //Eventos
    //--------------------

    @OnClick(R.id.crl_btn_autenticacao)
    public void crl_btn_autenticacao_OnClickListener(View view) {

        Intent intent = new Intent(this, MainActivity.class);
        //intent.putExtra(AppConstants.PICTURE, pictureRecyclerAdapter.getSelectedPicture(position).getId());
        startActivity(intent);
    }

    /*

        @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
       int id = item.getItemId();
        switch (id){
            case R.id.item1:
                Toast.makeText(getApplicationContext(),"Item 1 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item2:
                Toast.makeText(getApplicationContext(),"Item 2 Selected",Toast.LENGTH_LONG).show();
                return true;
            case R.id.item3:
                Toast.makeText(getApplicationContext(),"Item 3 Selected",Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    */

}
