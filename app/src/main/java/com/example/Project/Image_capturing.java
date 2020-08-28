package com.example.Project;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import com.camerakit.CameraKitView;
import com.example.firsttry.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Image_capturing extends AppCompatActivity {

    public static final String TAG = "test";
    private CameraKitView k;
    private static int RESULT_LOAD_IMAGE = 1;
    private Button b1, b2;
    String currentimagepath = null;
    private Button next;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1 = findViewById(R.id.btn);
        b2 = findViewById(R.id.btn_manual);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                //startActivityForResult(intent, 1);
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (intent.resolveActivity(getPackageManager()) != null) {

                    File imagefile = null;
                    try {
                        imagefile = getImage();


                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    if (imagefile != null) {
                        Uri imageuri = FileProvider.getUriForFile(getApplicationContext(), "com.example.firsttry.fileprovider", imagefile);
                        intent.putExtra(MediaStore.EXTRA_OUTPUT, imageuri);
                        startActivityForResult(intent, 1);


                    }


                }


            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), manual_data_entry.class);
                startActivity(intent);


            }
        });
    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {

            Intent intent=new Intent(getApplicationContext(),Detection.class);
            intent.putExtra("image_path",currentimagepath);
            startActivity(intent);


        }
    }

    /* private Uri getImageUri(Context applicationContext, Bitmap selectedImage) {

         ByteArrayOutputStream bytes = new ByteArrayOutputStream();
         //selectedImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
         String path = MediaStore.Images.Media.insertImage(Image_capturing.this.getContentResolver(), selectedImage, "Title", null);
         return Uri.parse(path);

     }*/
    private File getImage() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd").format(new Date());
        String imagename = "jpg_" + timeStamp + "_";
        File storage = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        File Imagefile = File.createTempFile(imagename, ".jpg", storage);
        currentimagepath = Imagefile.getAbsolutePath();
        return Imagefile;


    }

}