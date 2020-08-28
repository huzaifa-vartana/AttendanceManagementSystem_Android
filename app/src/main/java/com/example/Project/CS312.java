package com.example.Project;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class CS312 extends AppCompatActivity {


    ListView listView;
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cs312);
        listView = findViewById(R.id.list);
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
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.header3, listView, false);
        listView.addHeaderView(header);
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Courses").child("cs312");
        Query myTopPostsQuery = databaseReference.orderByChild("date");
        //databaseReference = FirebaseDatabase.getInstance().getReference().child("Courses");
        myTopPostsQuery.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String d = String.valueOf(dataSnapshot1.child("sid").getValue());
                    String d1 = String.valueOf(dataSnapshot1.child("status").getValue());
                    String n = d + ": " + d1.toUpperCase();
                    Toast.makeText(getApplicationContext(), "Sorted According to Date", 0).show();

                    arrayList.add(n);
                    arrayAdapter.notifyDataSetChanged();
                    Log.d("mytag", d);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
