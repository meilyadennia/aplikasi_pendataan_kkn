package com.example.pendataankkn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class RoleSelectionActivity extends AppCompatActivity {

    private Button btnLeader, btnMember;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_role_selection);

        btnLeader = findViewById(R.id.btnLeader);
        btnMember = findViewById(R.id.btnMember);

        btnLeader.setOnClickListener(v -> goToNextActivity("leader"));
        btnMember.setOnClickListener(v -> goToNextActivity("member"));
    }

    private void goToNextActivity(String role) {
        SessionManager.userRole = role;
        if (OpenActivity.actionType.equals("login")) {
            startActivity(new Intent(this, LoginActivity.class));
        } else {
            startActivity(new Intent(this, RegisterActivity.class));
        }
    }
}

