package com.example.pendataankkn;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailLogbookActivity extends AppCompatActivity {

    TextView tvTitle, tvDate, tvLocation, tvDescription;
    ImageView imgDetailPreview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_logbook);

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvDate = findViewById(R.id.tvDetailDate);
        tvLocation = findViewById(R.id.tvDetailLocation);
        tvDescription = findViewById(R.id.tvDetailDescription);
        imgDetailPreview = findViewById(R.id.imgDetailPreview);


        Intent intent = getIntent();
        tvTitle.setText(intent.getStringExtra("title"));
        tvDate.setText(intent.getStringExtra("date"));
        tvLocation.setText(intent.getStringExtra("location"));
        tvDescription.setText(intent.getStringExtra("description"));

        String imageUriStr = intent.getStringExtra("imageUri");
        if (imageUriStr != null && !imageUriStr.isEmpty()) {
            imgDetailPreview.setImageURI(Uri.parse(imageUriStr));
        } else {
            imgDetailPreview.setImageResource(android.R.drawable.ic_menu_gallery);
        }
    }
}
