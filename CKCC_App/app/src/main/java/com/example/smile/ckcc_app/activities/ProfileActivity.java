package com.example.smile.ckcc_app.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.example.smile.ckcc_app.R;

import java.io.File;
import java.io.FileNotFoundException;

import de.hdodenhof.circleimageview.CircleImageView;

import static android.graphics.BitmapFactory.decodeStream;

public class ProfileActivity extends AppCompatActivity {

    private final int GALLERY_REQUEST_CODE = 1;
    private final int CAMERA_REQUEST_CODE = 2;

    private CircleImageView imgProfile;

    private Uri capturedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_profile);
        imgProfile = (CircleImageView) findViewById(R.id.img_profile);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("                 Edit Profile");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        //Back to homepage
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation_action_profile_save, menu);
        return true;
    }


    //change profile

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == RESULT_OK){
            if(requestCode == GALLERY_REQUEST_CODE){
                Uri selectedImageUri = data.getData();
                Bitmap bitmap  = loadFitImage(selectedImageUri, imgProfile.getWidth(), imgProfile.getHeight());
                imgProfile.setImageBitmap(bitmap);
            } else if (requestCode == CAMERA_REQUEST_CODE){
                Bitmap bitmap = loadFitImage(capturedImageUri, imgProfile.getWidth(), imgProfile.getHeight());
                imgProfile.setImageBitmap(bitmap);
            }
        }
    }

    public void onProfileImageClick(View view) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setMessage("Change profile image via");
        dialog.setPositiveButton("gallery", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent galleryIntent = new Intent(Intent.ACTION_GET_CONTENT);
                galleryIntent.setType("image/*");
                startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
            }
        });
        dialog.setNegativeButton("camera", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                // Define Uri for storing full image
                File directory = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
                String filename = System.currentTimeMillis() + "jpg";
                File imageFile = new File (directory, filename);
                capturedImageUri = FileProvider.getUriForFile(ProfileActivity.this, "com.example.smile.ckcc_app.FILE_PROVIDER", imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, capturedImageUri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
            }
        });
        dialog.show();
    }

    private Bitmap loadFitImage(Uri imageUri, int targetWidth, int targetHeight){
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        try {
            decodeStream(getContentResolver().openInputStream(imageUri), null, options );
            int imageWidth = options.outWidth;
            int imageHeight = options.outHeight;
            int scaleFactor = Math.min(imageWidth / targetWidth, imageHeight / targetHeight);

            options.inJustDecodeBounds = false;
            options.inSampleSize = scaleFactor;

            return BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri), null, options);
        }catch (FileNotFoundException e){
            e.printStackTrace();
            return null;
        }
    }

}
