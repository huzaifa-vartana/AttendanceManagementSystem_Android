package com.example.Project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.firsttry.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class shownotes extends AppCompatActivity {

    ListView listView;
    public static final String TAG = "mytag";
    DatabaseReference databaseReference;
    ArrayList<String> arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shownotes);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, arrayList) {

            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text size 25 dip for ListView each item
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 15);
                //tv.setTextColor(0);

                // Return the view
                return view;
            }
        };
        listView = findViewById(R.id.listview12);
        listView.setAdapter(arrayAdapter);
        LayoutInflater inflater = getLayoutInflater();
        ViewGroup header = (ViewGroup) inflater.inflate(R.layout.headernotes, listView, false);
        listView.addHeaderView(header);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Notes");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String d = String.valueOf(dataSnapshot1.getKey());
                    arrayList.add(d);
                    arrayAdapter.notifyDataSetChanged();
                    Log.d(TAG, d);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
      /*  listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position == 1) {
                    Intent intent = new Intent(getApplicationContext(), CS312.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(getApplicationContext(), CS313.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(getApplicationContext(), CS326.class);
                    startActivity(intent);
                } else if (position == 4) {
                    Intent intent = new Intent(getApplicationContext(), CS351.class);
                    startActivity(intent);
                }
            }
        });*/

    }
}
