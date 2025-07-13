package com.example.pendataankkn;

import android.net.Uri;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.View;
import android.graphics.Color;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class LogbookListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);
        scrollView.setBackgroundColor(Color.parseColor("#FFF9C4")); // soft yellow

        LinearLayout container = new LinearLayout(this);
        container.setOrientation(LinearLayout.VERTICAL);
        container.setPadding(24, 96, 24, 24);

        for (LogbookEntry entry : SessionManager.logbookList) {
            LinearLayout card = new LinearLayout(this);
            card.setOrientation(LinearLayout.VERTICAL);
            card.setPadding(24, 24, 24, 24);
            card.setBackgroundColor(Color.WHITE);
            card.setElevation(8f);
            card.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT));
            card.setBackgroundResource(android.R.drawable.dialog_holo_light_frame);

            // Judul
            TextView title = new TextView(this);
            title.setText(entry.title);
            title.setTextSize(25f);
            title.setTextColor(Color.BLACK);
            title.setPadding(24, 24, 24, 8);
            title.setTypeface(null, android.graphics.Typeface.BOLD);

            // Detail
            TextView detail = new TextView(this);
            detail.setText(
                    "📅 " + entry.date + "\n" +
                            "📍 " + entry.location + "\n\n" +
                            entry.description
            );
            detail.setTextSize(16f);
            detail.setPadding(24,24,24,8);
            detail.setTextColor(Color.DKGRAY);

            card.addView(title);
            card.addView(detail);

            // Gambar jika ada
            if (entry.photoUri != null) {
                ImageView img = new ImageView(this);
                img.setImageURI(entry.photoUri);
                img.setAdjustViewBounds(true);
                img.setScaleType(ImageView.ScaleType.CENTER_CROP);
                LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 500);
                lp.setMargins(0, 16, 0, 0);
                img.setLayoutParams(lp);
                card.addView(img);
            }

            // Spacer antar logbook
            View spacer = new View(this);
            spacer.setLayoutParams(new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 32));

            container.addView(card);
            container.addView(spacer);
        }

        scrollView.addView(container);
        setContentView(scrollView);
    }
}
