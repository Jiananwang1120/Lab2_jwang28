package com.cs60333.jwang28.lab2_jwang28;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static android.support.v4.app.ActivityCompat.startActivityForResult;
import static java.security.AccessController.getContext;

/**
 * Created by Ivy Wang on 2/15/2017.
 */

public class DetailActivity extends AppCompatActivity {
    Button camera_button;
    final static int CAMERA_REQUEST = 1;
    Team team;
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_detail);
      //  team = (Team) getIntent().getSerializableExtra("Team");
        final ImageView llogo = (ImageView) findViewById(R.id.florida);
        final ImageView rlogo = (ImageView) findViewById(R.id.nd);
        final TextView date = (TextView) findViewById(R.id.date);
        final TextView lteam = (TextView) findViewById(R.id.info);
        final TextView rteam = (TextView) findViewById(R.id.textView7);
        final TextView score = (TextView) findViewById(R.id.score);

        camera_button = (Button) findViewById(R.id.camera_button);
       camera_button.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent i = new Intent(DetailActivity.this, GalleryActivity.class);
               i.putExtra("id", team.getID());
               startActivity(i);
           }
       });

        // create click listener
        final View.OnClickListener c_clicked = new View.OnClickListener() {
            //@Override
            public void onClick(View v) {
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivity(cameraIntent);
                //intent.putExtra(MediaStore.EXTRA_OUTPUT, "");
                //startActivityForResult(intent, Integer.parseInt(CAMERA_SERVICE));
                //  Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File PictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
                String pictureName = getPictureName();
                File imageFile = new File(PictureDirectory, pictureName);
                Uri pictureUri = Uri.fromFile(imageFile);
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, pictureUri);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }


            private String getPictureName() {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss");
                String timestamp = sdf.format(new Date());
                return "BestMoments" + timestamp + ".jpg";
            }


        };

    //    camera_button.setOnClickListener(c_clicked);
        team = (Team) getIntent().getSerializableExtra("team");
        String mDrawableName = team.getLeftTeamLogo();
        String mDrawableName2 = team.getRightTeamLogo();
        date.setText(team.getDate() + "\n" + team.getPlace());
        lteam.setText(team.getLteam() + "\n" + team.getLnick() + "\n" + team.getLscore());
        rteam.setText(team.getRteam() + "\n" + team.getRnick() + "\n" + team.getRscore());
        score.setText(team.getScore() + "\nFinal");
        int resID = getApplicationContext().getResources().getIdentifier(mDrawableName ,"drawable", getPackageName());
        llogo.setImageResource(resID );
        int resID2 = getApplicationContext().getResources().getIdentifier(mDrawableName2 , "drawable", getPackageName());
        rlogo.setImageResource(resID2 );

    }

    @Override
            protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
                if(resultCode == RESULT_OK) {
                    if (requestCode == CAMERA_REQUEST) {
                        Intent photoGalleryIntent = new Intent(Intent.ACTION_PICK);
                        File pictureDirectory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);

                        String pictureDirectoryPath = pictureDirectory.getPath();
                        Uri imageUri = Uri.parse(pictureDirectoryPath);
                        InputStream inputStream;
                        try {
                            inputStream = getContentResolver().openInputStream(imageUri);

                            Bitmap image = BitmapFactory.decodeStream(inputStream);
                            ImageView imgView = (ImageView) findViewById(R.id.photo_taken);
                            imgView.setImageBitmap(image);

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }


    }
    public String getPackageName() {
        return "com.cs60333.jwang28.lab2_jwang28";
    }
}
