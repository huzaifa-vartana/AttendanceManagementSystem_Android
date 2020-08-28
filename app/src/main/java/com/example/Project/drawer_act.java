package com.example.Project;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.firsttry.R;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;

import in.championswimmer.sfg.lib.SimpleFingerGestures;

import static maes.tech.intentanim.CustomIntent.customType;

public class drawer_act extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    public DrawerLayout drawerLayout;
    public NavigationView navigationView;
    public Toolbar toolbar;
    FirebaseAuth firebaseAuth;
    private SimpleFingerGestures mySfg = new SimpleFingerGestures();

    private ImageView imageView, imageView2, imageView5, imageView_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drawer_act);
        drawerLayout = findViewById(R.id.drawer);
        imageView = findViewById(R.id.attent);
        imageView_user = findViewById(R.id.train);
        imageView2 = findViewById(R.id.imageView3);
        navigationView = findViewById(R.id.nav_view);
        firebaseAuth = FirebaseAuth.getInstance();
        imageView5 = findViewById(R.id.bus1);
        toolbar = findViewById(R.id.toolbar);
        navigationView.bringToFront();
        imageView_user.setOnClickListener(this);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.setCheckedItem(R.id.nav_home);
        imageView.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView5.setOnClickListener(this);
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
        drawerLayout.setOnTouchListener(mySfg);

    }

    @Override
    public void onBackPressed() {
        moveTaskToBack(true);

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

            drawerLayout.closeDrawer(GravityCompat.START);

        } else
            super.onBackPressed();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_home:
                break;
            case R.id.face_detect:
                Intent intent = new Intent(getApplicationContext(), Image_capturing.class);
                startActivity(intent);
                customType(drawer_act.this, "bottom-to-up");

                break;
            case R.id.nav_profile:
                Intent intent2 = new Intent(getApplicationContext(), Profiles.class);
                startActivity(intent2);
                customType(drawer_act.this, "bottom-to-up");


                break;
            case R.id.record:
                Intent intent1 = new Intent(getApplicationContext(), ShowRecords.class);
                startActivity(intent1);

                break;
            case R.id.log:
                FirebaseAuth.getInstance().signOut();
                finish();
                Intent intent4 = new Intent(getApplicationContext(), login.class);
                startActivity(intent4);
                customType(drawer_act.this, "bottom-to-up");

                break;
            case R.id.nav_snotes:
                Intent intent3 = new Intent(getApplicationContext(), shownotes.class);
                startActivity(intent3);
                customType(drawer_act.this, "bottom-to-up");

                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }


    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.attent) {

            Intent intent = new Intent(getApplicationContext(), Image_capturing.class);
            startActivity(intent);
            customType(drawer_act.this, "bottom-to-up");


        } else if (v.getId() == R.id.imageView3) {
            //Log.d("mytag", "show records pressed");
            Intent intent22 = new Intent(getApplicationContext(), ShowRecords.class);
            startActivity(intent22);
            customType(drawer_act.this, "bottom-to-up");

            //Log.d("mytag", "show records pressed");
        } else if (v.getId() == R.id.bus1) {

            Intent intent = new Intent(getApplicationContext(), todolist.class);
            startActivity(intent);
            customType(drawer_act.this, "bottom-to-up");


        } else if (v.getId() == R.id.train) {

            Intent intent = new Intent(getApplicationContext(), User_database.class);
            startActivity(intent);
            customType(drawer_act.this, "bottom-to-up");


        }
    }
}
