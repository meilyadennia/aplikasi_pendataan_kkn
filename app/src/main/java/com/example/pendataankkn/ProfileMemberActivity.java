package com.example.pendataankkn;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class ProfileMemberActivity extends AppCompatActivity {

    private EditText etFullName, etEmail, etStudentId, etPhone, etGroupCode, etLocation, etStartDate, etEndDate;
    private Button btnSave;
    private ImageView btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_member);

        etFullName = findViewById(R.id.etFullName);
        etEmail = findViewById(R.id.etEmail);
        etStudentId = findViewById(R.id.etStudentId);
        etPhone = findViewById(R.id.etPhone);
        etGroupCode = findViewById(R.id.etGroupCode);
        etLocation = findViewById(R.id.etLocation);
        etStartDate = findViewById(R.id.etStartDate);
        etEndDate = findViewById(R.id.etEndDate);
        btnSave = findViewById(R.id.btnSave);
        btnBack = findViewById(R.id.btnBack);

        btnSave.setOnClickListener(v -> {
            setHintAndClear(etFullName);
            setHintAndClear(etEmail);
            setHintAndClear(etStudentId);
            setHintAndClear(etPhone);
            setHintAndClear(etGroupCode);
            setHintAndClear(etLocation);
            setHintAndClear(etStartDate);
            setHintAndClear(etEndDate);
        });

        btnBack.setOnClickListener(v -> finish());

        etStartDate.setFocusable(false);
        etEndDate.setFocusable(false);

        etStartDate.setOnClickListener(v -> showDatePicker(etStartDate));
        etEndDate.setOnClickListener(v -> showDatePicker(etEndDate));

    }

    private void showDatePicker(EditText targetEditText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(this,
                (view, y, m, d) -> {
                    String selectedDate = String.format("%02d/%02d/%04d", d, m + 1, y);
                    targetEditText.setText(selectedDate);
                }, year, month, day);

        dialog.show();
    }

    private void setHintAndClear(EditText et) {
        String val = et.getText().toString().trim();
        if (!val.isEmpty()) {
            et.setHint(val);
            et.setText("");
        }
    }
}
