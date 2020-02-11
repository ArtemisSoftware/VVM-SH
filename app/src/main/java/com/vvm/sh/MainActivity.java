package com.vvm.sh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.github.clans.fab.FloatingActionButton;
import com.github.clans.fab.FloatingActionMenu;
import com.vvm.sh.apresentacao.ApresentacaoActivity;
import com.vvm.sh.ui.BaseActivity;
import com.wdullaer.materialdatetimepicker.date.DatePickerDialog;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.OnClick;

public class MainActivity extends BaseActivity implements DatePickerDialog.OnDateSetListener{



    @BindView(R.id.fab_menu_agenda)
    FloatingActionMenu fab_menu_agenda;



    private int Year, Month, Day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        //Intent intent = new Intent(this, ApresentacaoActivity.class);
        //startActivity(intent);

    }



    //---------------------
    //Eventos
    //---------------------


    @OnClick(R.id.fab_calendario)
    public void fab_calendario_OnClickListener(View view) {

        fab_menu_agenda.close(true);

        Calendar calendar = Calendar.getInstance();
        Year = calendar.get(Calendar.YEAR) ;
        Month = calendar.get(Calendar.MONTH);
        Day = calendar.get(Calendar.DAY_OF_MONTH);



        DatePickerDialog dpd = DatePickerDialog.newInstance(
                MainActivity.this, Year, Month, Day);

        dpd.setTitle("Date Picker");

        // Setting Min Date to today date
        Calendar min_date_c = Calendar.getInstance();
        dpd.setMinDate(min_date_c);



        // Setting Max Date to next 2 years
        Calendar max_date_c = Calendar.getInstance();
        max_date_c.set(Calendar.YEAR, Year+2);
        dpd.setMaxDate(max_date_c);


        //Disable all SUNDAYS and SATURDAYS between Min and Max Dates
        for (Calendar loopdate = min_date_c; min_date_c.before(max_date_c); min_date_c.add(Calendar.DATE, 1), loopdate = min_date_c) {
            int dayOfWeek = loopdate.get(Calendar.DAY_OF_WEEK);
            if (dayOfWeek == Calendar.SUNDAY || dayOfWeek == Calendar.SATURDAY) {
                Calendar[] disabledDays =  new Calendar[1];
                disabledDays[0] = loopdate;
                dpd.setDisabledDays(disabledDays);
            }
        }


        Calendar[] high = new Calendar[1];
        Calendar calendarff = Calendar.getInstance();
        calendarff.get(Calendar.YEAR) ;
        calendarff.get(Calendar.MONTH);
        calendarff.set(Calendar.DAY_OF_WEEK, 13);

        high [0] = calendarff;

        dpd.setHighlightedDays(high);

        dpd.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                String date = "Date: "+Day+"/"+(Month+1)+"/"+Year;

                Toast.makeText(MainActivity.this, date, Toast.LENGTH_LONG).show();
            }
        });


        dpd.show(getSupportFragmentManager(), "Datepickerdialog");

    }



    @Override
    public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
        Toast.makeText(MainActivity.this, "OverrideOverrideOverrideOverrideOverride", Toast.LENGTH_LONG).show();
    }
}
