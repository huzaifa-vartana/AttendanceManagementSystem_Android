package com.example.Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttry.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Profiles extends AppCompatActivity implements View.OnClickListener {


    Button button;
    TextView t1, t2;
    FirebaseUser firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profiles);
        button = findViewById(R.id.back11);
        t1 = findViewById(R.id.input1);
        t2 = findViewById(R.id.input12);

        firebaseAuth = FirebaseAuth.getInstance().getCurrentUser();
        String email = firebaseAuth.getEmail();
        t1.setText(email);
        button.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.back11){

            Intent intent=new Intent(getApplicationContext(),drawer_act.class);
            startActivity(intent);

        }
    }
}
