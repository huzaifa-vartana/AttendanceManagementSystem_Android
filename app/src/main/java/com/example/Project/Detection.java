package com.example.Project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.firsttry.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.ml.common.FirebaseMLException;
import com.google.firebase.ml.vision.FirebaseVision;
import com.google.firebase.ml.vision.automl.FirebaseAutoMLLocalModel;
import com.google.firebase.ml.vision.common.FirebaseVisionImage;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabel;
import com.google.firebase.ml.vision.label.FirebaseVisionImageLabeler;
import com.google.firebase.ml.vision.label.FirebaseVisionOnDeviceAutoMLImageLabelerOptions;

import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import in.championswimmer.sfg.lib.SimpleFingerGestures;

public class Detection extends AppCompatActivity implements View.OnClickListener {
    private SimpleFingerGestures mySfg = new SimpleFingerGestures();

    public static final String TAG = "mytag";
    public static final String status = "present";
    public String alertbox;
    ImageView bitmap_view;
    Bitmap bitmap;
    ScrollView scrollView;
    public Button upload, home;
    EditText e1, e2, e3;
    TextView textView;
    String currentpath = null, e11, e22, e33, text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detection);
        //Toast.makeText(this,"Second ACT",Toast.LENGTH_SHORT).show();
        bitmap_view = findViewById(R.id.bitmap_v);
        textView = findViewById(R.id.detecetd_name);
        home = findViewById(R.id.home);
        home.setOnClickListener(this);
        e1 = findViewById(R.id.input1);
        scrollView = findViewById(R.id.scoll_user);
        e2 = findViewById(R.id.input2);
        e3 = findViewById(R.id.input3);
        Button button = findViewById(R.id.upload);
        upload = findViewById(R.id.up);
        button.setOnClickListener(this);
        final AlertDialog.Builder builder1 = new AlertDialog.Builder(Detection.this);
        bitmap = BitmapFactory.decodeFile(getIntent().getStringExtra("image_path"));
        currentpath = getIntent().getStringExtra("image_path");
        rotateimage();
        FirebaseAutoMLLocalModel localModel = new FirebaseAutoMLLocalModel.Builder().setAssetFilePath("manifest.json").build();
        FirebaseVisionImageLabeler labeler = null;
        try {
            FirebaseVisionOnDeviceAutoMLImageLabelerOptions options =
                    new FirebaseVisionOnDeviceAutoMLImageLabelerOptions.Builder(localModel).setConfidenceThreshold(0.5f).build();
            labeler = FirebaseVision.getInstance().getOnDeviceAutoMLImageLabeler(options);
        } catch (FirebaseMLException e) {

        }
        FirebaseVisionImage bitmap_image;
        bitmap_image = FirebaseVisionImage.fromBitmap(bitmap);
        labeler.processImage(bitmap_image)
                .addOnSuccessListener(new OnSuccessListener<List<FirebaseVisionImageLabel>>() {
                    @Override
                    public void onSuccess(List<FirebaseVisionImageLabel> labels) {
                        // Task completed successfully
                        // ...
                        for (FirebaseVisionImageLabel label : labels) {
                            text = label.getText();
                            float confidence = label.getConfidence();
                            String t = String.valueOf(confidence);
                            Log.d(TAG, text);
                            Log.d(TAG, t);
                            textView.setText(text);
                            alertbox = "Are you " + text + "?";
                            Log.d(TAG, alertbox);
                            builder1.setMessage(alertbox);
                            builder1.setCancelable(true);
                            builder1.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d(TAG, "Now perform database entry");
                                    dialog.cancel();
                                }
                            });
                            builder1.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(getApplicationContext(), "Perform Facial Recognition Again!", 1).show();
                                    Intent intent1 = new Intent(getApplicationContext(), drawer_act.class);
                                    startActivity(intent1);
                                    dialog.cancel();

                                }
                            });
                            AlertDialog alertDialog = builder1.create();
                            alertDialog.show();

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
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
        scrollView.setOnTouchListener(mySfg);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.upload) {

            //Intent intent = new Intent(getApplicationContext(), drawer_act.class);
            //startActivity(intent);
            Log.d(TAG, "onClick: ");
            add1();


        } else if (v.getId() == R.id.home) {
            Log.d(TAG, "onClick: ");
            Intent intent22 = new Intent(getApplicationContext(), drawer_act.class);
            startActivity(intent22);

        }

    }

    public void add1() {

        DatabaseReference databaseReference;
        databaseReference = FirebaseDatabase.getInstance().getReference();
        Log.d(TAG, "before");
        e11 = e1.getText().toString().trim();
        e22 = e2.getText().toString().trim();
        e33 = e3.getText().toString().trim();
        Log.d(TAG, "1");
        Calendar calendar = Calendar.getInstance();
        long year = calendar.get(Calendar.YEAR);
        long month = calendar.get(Calendar.MONTH) + 1;
        long day = calendar.get(Calendar.DAY_OF_MONTH);
        long calcDate = year * 100 + month;
        calcDate = calcDate * 100 + day;
        database_course c = new database_course(e11, e22, text, "present", e33, calcDate);
        Log.d(TAG, "2");
        databaseReference.child("Courses").child(e11).child(e33).setValue(c);
        Toast.makeText(this, "Attendance Marked", 0).show();


    }

    private void rotateimage() {

        ExifInterface exifInterface = null;
        try {
            exifInterface = new ExifInterface(currentpath);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int ort = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_UNDEFINED);
        Matrix matrix = new Matrix();
        switch (ort) {
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.setRotate(90);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.setRotate(180);
                break;
            default:
        }
        Bitmap bitmaport = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        bitmap_view.setImageBitmap(bitmaport);


    }
}
