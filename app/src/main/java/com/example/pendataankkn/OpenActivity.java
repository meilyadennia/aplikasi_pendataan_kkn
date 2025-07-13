package com.example.pendataankkn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class OpenActivity extends AppCompatActivity {

    private Button btnLogin, btnRegister;
    public static String actionType = ""; // "login" atau "register"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open);

        btnLogin = findViewById(R.id.btnLogin);
        btnRegister = findViewById(R.id.btnRegister);

        btnLogin.setOnClickListener(v -> {
            actionType = "login";
            startActivity(new Intent(OpenActivity.this, RoleSelectionActivity.class));
        });

        btnRegister.setOnClickListener(v -> {
            actionType = "register";
            startActivity(new Intent(OpenActivity.this, RoleSelectionActivity.class));
        });
    }
}
