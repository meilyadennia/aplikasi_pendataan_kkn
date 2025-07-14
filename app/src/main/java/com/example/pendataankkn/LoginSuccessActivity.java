package com.example.pendataankkn;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;

public class LoginSuccessActivity extends AppCompatActivity {

    private static final int SPLASH_DELAY = 2000; // 2 detik

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_success);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(LoginSuccessActivity.this, LogbookActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);
    }
}
