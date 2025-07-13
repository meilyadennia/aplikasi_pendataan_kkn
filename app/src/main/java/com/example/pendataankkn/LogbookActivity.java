package com.example.pendataankkn;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.*;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class LogbookActivity extends AppCompatActivity {

    private EditText etDate, etTitle, etLocation, etDescription;
    private Button btnSubmit, btnLogbook;
    private ImageView imgPreview, btnProfile;
    private Uri imageUri = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        etDate = findViewById(R.id.etDate);
        etTitle = findViewById(R.id.etTitle);
        etLocation = findViewById(R.id.etLocation);
        etDescription = findViewById(R.id.etDescription);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnLogbook = findViewById(R.id.btnLogbook);
        imgPreview = findViewById(R.id.imgPreview);
        btnProfile = findViewById(R.id.btnProfile);

        imgPreview.setOnClickListener(v -> openGallery());

        btnSubmit.setOnClickListener(v -> submitLogbook());

        btnLogbook.setOnClickListener(v ->
                startActivity(new Intent(LogbookActivity.this, LogbookListActivity.class)));

        btnProfile.setOnClickListener(v -> {
            if (SessionManager.userRole.equals("leader")) {
                startActivity(new Intent(this, ProfileLeaderActivity.class));
            } else {
                startActivity(new Intent(this, ProfileMemberActivity.class));
            }
        });

        etDate.setFocusable(false); // agar tidak muncul keyboard
        etDate.setOnClickListener(v -> showDatePicker(etDate));

    }

    private void showDatePicker(EditText targetEditText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    // Format: dd/MM/yyyy
                    String selectedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                    targetEditText.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickImageLauncher.launch(intent);
    }

    private final ActivityResultLauncher<Intent> pickImageLauncher =
            registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
                if (result.getResultCode() == RESULT_OK && result.getData() != null) {
                    imageUri = result.getData().getData();
                    imgPreview.setImageURI(imageUri);
                }
            });

    private void submitLogbook() {
        String date = etDate.getText().toString().trim();
        String title = etTitle.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (date.isEmpty() || title.isEmpty() || location.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Tambahkan ke list logbook
        LogbookEntry entry = new LogbookEntry(date, title, location, description, imageUri);
        SessionManager.logbookList.add(entry);

        showSuccessDialog();

        // Kosongkan form
        etDate.setText("");
        etTitle.setText("");
        etLocation.setText("");
        etDescription.setText("");
        imgPreview.setImageResource(R.drawable.ic_upload); // icon default
        imageUri = null;
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(this)
                .setTitle("Success")
                .setMessage("Your logbook has been submitted successfully.")
                .setPositiveButton("OK", (dialog, which) -> dialog.dismiss())
                .show();
    }
}
