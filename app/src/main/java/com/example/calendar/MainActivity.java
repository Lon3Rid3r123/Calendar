package com.example.calendar;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.time.LocalDate;
import java.time.Year;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CalenderViewAdapter.OnItemListener{
    private TextView monthYearText;
        private RecyclerView calendarRecyclerView;
    private LocalDate selectedDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initWidgets();
        selectedDate=LocalDate.now();
        setMonthView();
    }

    private void setMonthView() {
        monthYearText.setText(monthYearFromDate(selectedDate));
        ArrayList<String> daysInMonth=daysInMonth(selectedDate);

        CalenderViewAdapter calendarViewAdapter=new CalenderViewAdapter(daysInMonth,this);
        RecyclerView.LayoutManager layoutManager=new GridLayoutManager(getApplicationContext(),7);
        calendarRecyclerView.setLayoutManager(layoutManager);
        calendarRecyclerView.setAdapter(calendarViewAdapter);
    }

    private ArrayList<String> daysInMonth(LocalDate date) {
        ArrayList<String> daysInMonthArray=new ArrayList<>();
        YearMonth yearMonth=YearMonth.from(date);
        int daysInMonth=yearMonth.lengthOfMonth();
        LocalDate firstOfMonth=selectedDate.withDayOfMonth(1);
        int dayOfWeek=firstOfMonth.getDayOfWeek().getValue();
        for(int i=1;i<=42;i++){
            if(i<dayOfWeek||i>dayOfWeek+daysInMonth){
                daysInMonthArray.add(" ");
            }
            else{
                daysInMonthArray.add(String.valueOf(i-dayOfWeek+1));
            }
        }
        return daysInMonthArray;
    }

    private void initWidgets() {
        calendarRecyclerView=findViewById(R.id.recyclerView);
        monthYearText=findViewById(R.id.monthYearTV);
    }

    private String monthYearFromDate(LocalDate date){
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MMMM yyyy");
        return date.format(formatter);
    }
    public void previousMonthAction(View view) {
        selectedDate=selectedDate.minusMonths(1);
        setMonthView();
    }

    public void nextMonthAction(View view) {
        selectedDate=selectedDate.plusMonths(1);
        setMonthView();
    }

    @Override
    public void onItemClick(int position, String dayText) {
        if(dayText!=" ") {
            String message = "Selected date" + " " + dayText + " " + monthYearFromDate(selectedDate);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        }
    }
}