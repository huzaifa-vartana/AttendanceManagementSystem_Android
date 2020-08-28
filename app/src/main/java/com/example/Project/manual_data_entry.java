package com.example.Project;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttry.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class manual_data_entry extends AppCompatActivity {

    private EditText e1, e2, e3, e4, e5;
    DatabaseReference databaseReference;
    private Button b1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_data_entry);
        e1 = findViewById(R.id.input_c);
        e2 = findViewById(R.id.input_coursename);
        e3 = findViewById(R.id.input_sname);
        e4 = findViewById(R.id.input_sid);
        e5 = findViewById(R.id.input_status);
        b1 = findViewById(R.id.btn_data);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add();
            }
        });
    }

    protected void add() {


        String t1 = e1.getText().toString().trim();
        String t2 = e2.getText().toString().trim();
        String t3 = e3.getText().toString().trim();
        String t4 = e4.getText().toString().trim();
        String t5 = e5.getText().toString().trim();
        //String id=databaseReference.push().getKey();
        Calendar calendar = Calendar.getInstance();
        long year = calendar.get(Calendar.YEAR);
        long month = calendar.get(Calendar.MONTH) + 1;
        long day = calendar.get(Calendar.DAY_OF_MONTH);
        long calcDate = year * 100 + month;
        calcDate = calcDate * 100 + day;
        String d1 = String.valueOf(calcDate);
        Log.d("mytag", d1);

        database_course courses = new database_course(t1, t2, t3, t4, t5, calcDate);
        databaseReference.child("Courses").child(t1).child(t4).setValue(courses);
        //Log.d("mytag", p);
        Toast.makeText(this, "Attendance Marked", 0).show();


    }
}
