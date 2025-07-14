package com.example.pendataankkn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetailLogbookActivity extends AppCompatActivity {

    TextView tvTitle, tvDate, tvLocation, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_logbook);

        tvTitle = findViewById(R.id.tvDetailTitle);
        tvDate = findViewById(R.id.tvDetailDate);
        tvLocation = findViewById(R.id.tvDetailLocation);
        tvDescription = findViewById(R.id.tvDetailDescription);

        Intent intent = getIntent();
        tvTitle.setText(intent.getStringExtra("title"));
        tvDate.setText(intent.getStringExtra("date"));
        tvLocation.setText(intent.getStringExtra("location"));
        tvDescription.setText(intent.getStringExtra("description"));
    }
}
