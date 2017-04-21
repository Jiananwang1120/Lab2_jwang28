package com.cs60333.jwang28.lab2_jwang28;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class ImageActivity extends AppCompatActivity {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        Intent i = getIntent();

        int position = i.getExtras().getInt("id");

        ImageView imageView = (ImageView) findViewById(R.id.image_view);
    }
}
