package com.example.Project;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttry.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class todolist extends AppCompatActivity implements View.OnClickListener {

    DatabaseReference databaseReference;
    EditText editText, editText1;
    FloatingActionButton floatingActionButton;
    String name, data;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todolist);
        floatingActionButton = findViewById(R.id.fb);

        //floatingActionButton.setBackgroundColor(getResources().getColor(R.color.color2));
        floatingActionButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fb) {

            store();
        }
    }

    void store() {
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notes");
        editText = findViewById(R.id.edittext);
        editText1 = findViewById(R.id.edittext1);
        name = editText.getText().toString();
        data = editText1.getText().toString();
        if (data.isEmpty()) {

            Toast.makeText(this, "Enter the Name", 0).show();

        }
        notes notes = new notes(name, data);
        databaseReference.child(data).setValue(notes);
        //Log.d("mytag", p);
        Toast.makeText(this, "Note Saved", 0).show();

    }
}
