package com.example.Project;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttry.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import in.championswimmer.sfg.lib.SimpleFingerGestures;

public class User_database extends AppCompatActivity {
    private SimpleFingerGestures mySfg = new SimpleFingerGestures();
LinearLayout linearLayout;

    ListView listView;
    public static final String TAG = "mytag";
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userdatabase);
        listView = findViewById(R.id.listview_user);
        linearLayout=findViewById(R.id.linearlayout_user);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList) {


            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 23);

                // Return the view
                return view;
            }


        };
        listView.setAdapter(arrayAdapter);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header_user, listView, false);
        listView.addHeaderView(header);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("User");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String d = String.valueOf(dataSnapshot1.getKey());
                    Toast.makeText(getApplicationContext(), "Users which are trained", 0).show();

                    arrayList.add(d);
                    arrayAdapter.notifyDataSetChanged();
                    Log.d("mytag", d);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        mySfg.setOnFingerGestureListener(new SimpleFingerGestures.OnFingerGestureListener() {
            @Override
            public boolean onSwipeUp(int i, long l, double v) {
                return false;
            }

            @Override
            public boolean onSwipeDown(int i, long l, double v) {
                return false;
            }

            @Override
            public boolean onSwipeLeft(int i, long l, double v) {
                Intent intent = new Intent(getApplicationContext(), drawer_act.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Back to Home", 0).show();
                return true;
            }

            @Override
            public boolean onSwipeRight(int i, long l, double v) {
                return false;
            }

            @Override
            public boolean onPinch(int i, long l, double v) {
                return false;
            }

            @Override
            public boolean onUnpinch(int i, long l, double v) {
                return false;
            }

            @Override
            public boolean onDoubleTap(int i) {
                return false;
            }
        });
        linearLayout.setOnTouchListener(mySfg);
        listView.setOnTouchListener(mySfg);
    }
}
