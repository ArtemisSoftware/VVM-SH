package com.vvm.sh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toolbar;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.vvm.sh.apresentacao.ApresentacaoActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener{

    private int mYear, mMonth, mDay, mHour, mMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //Intent intent = new Intent(this, ApresentacaoActivity.class);
        //startActivity(intent);


        ((FloatingActionButton) findViewById(R.id.fab_calendario)).setOnClickListener(fab_adicionar_acao_formacao_OnClickListener);
        //((FloatingActionButton) findViewById(R.id.fab_adicionar_formando)).setOnClickListener(fab_adicionar_formando_OnClickListener);
    }


    private Button.OnClickListener fab_adicionar_acao_formacao_OnClickListener  = new Button.OnClickListener(){
        @Override
        public void onClick(View arg0) {

            ((FloatingActionMenu) findViewById(R.id.fab_menu_agenda)).close(true);

/*
            Calendar now = Calendar.getInstance();
            DatePickerDialog dpd = DatePickerDialog.newInstance(
                    MainActivity.this,
                    now.get(Calendar.YEAR), // Initial year selection
                    now.get(Calendar.MONTH), // Initial month selection
                    now.get(Calendar.DAY_OF_MONTH) // Inital day selection
            );
             dpd.show(getSupportFragmentManager(), "Datepickerdialog");
*/
            /*
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(MainActivity.this,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {



                        }
                    }, mYear, mMonth, mDay);


            datePickerDialog.show();
            */
        }
    };

    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {

    }
}
