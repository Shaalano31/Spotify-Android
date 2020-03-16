package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;

public class signUp3 extends AppCompatActivity {


    Button done ;
    Button dateofBirth;
    Calendar cal ;
    DatePickerDialog dp ;
    int day ;
    int month ;
    int year;
    private DatePickerDialog.OnDateSetListener Mydatasetlistener ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up7);
        done = (Button) findViewById(R.id.doneButton);
        dateofBirth = (Button) findViewById(R.id.dateofbirthButton);

        dateofBirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal = Calendar.getInstance();
                int day = cal.get(Calendar.DAY_OF_MONTH);
                int month = cal.get(Calendar.MONTH);
                int year = cal.get(Calendar.YEAR);

                dp = new DatePickerDialog(signUp3.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, Mydatasetlistener
                        , year, month, day);
                dp.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dp.show();
            }
        });


        Mydatasetlistener = new DatePickerDialog.OnDateSetListener() {


            @Override
            public void onDateSet(DatePicker view, int selectedYear,
                                  int selectedMonth, int selectedDay) {

                year = selectedYear;
                month = selectedMonth;
                day = selectedDay;

                // Show selected date
                dateofBirth.setText(new StringBuilder().append(month + 1)
                        .append("-").append(day).append("-").append(year)
                        .append(" "));
            }
        };
    }
}
