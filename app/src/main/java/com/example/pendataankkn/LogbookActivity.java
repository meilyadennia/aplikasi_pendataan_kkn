package com.example.pendataankkn;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.content.ActivityNotFoundException;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import androidx.annotation.Nullable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Calendar;

public class LogbookActivity extends AppCompatActivity {

    private EditText etDate, etTitle, etLocation, etDescription;
    private Button btnSubmit, btnLogbook, btnUploadPhoto;
    private ImageView btnProfile, imgPreview;
    private Uri imageUri = null;
    private static final int REQUEST_IMAGE_PICK = 1;
    LogbookDAO dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        dao = new LogbookDAO(this);

        etDate = findViewById(R.id.etDate);
        etTitle = findViewById(R.id.etTitle);
        etLocation = findViewById(R.id.etLocation);
        etDescription = findViewById(R.id.etDescription);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnLogbook = findViewById(R.id.btnLogbook);
        btnProfile = findViewById(R.id.btnProfile);
        btnUploadPhoto = findViewById(R.id.btnUploadPhoto);
        imgPreview = findViewById(R.id.imgPreview);

        btnSubmit.setOnClickListener(v -> {
            String date = etDate.getText().toString();
            String title = etTitle.getText().toString();
            String location = etLocation.getText().toString();
            String description = etDescription.getText().toString();
            String selectedImageUri = (imageUri != null) ? imageUri.toString() : null;

            if (title.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Isi minimal Title dan Date", Toast.LENGTH_SHORT).show();
                return;
            }

            LogbookModel logbook = new LogbookModel(title, date, location, description, selectedImageUri);
            long result = dao.insert(logbook);

            if (result != -1) {
                Toast.makeText(this, "Logbook disimpan", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(this, LogbookListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Gagal menyimpan", Toast.LENGTH_SHORT).show();
            }
        });

        btnLogbook.setOnClickListener(v ->
                startActivity(new Intent(LogbookActivity.this, LogbookListActivity.class)));

        btnProfile.setOnClickListener(v -> {
            if (SessionManager.userRole.equals("leader")) {
                startActivity(new Intent(this, ProfileLeaderActivity.class));
            } else {
                startActivity(new Intent(this, ProfileMemberActivity.class));
            }
        });

        imgPreview = findViewById(R.id.imgPreview);
        btnUploadPhoto = findViewById(R.id.btnUploadPhoto);

        btnUploadPhoto.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/*");
            try {
                startActivityForResult(intent, REQUEST_IMAGE_PICK);
            } catch (ActivityNotFoundException e) {
                Toast.makeText(this, "Tidak bisa membuka galeri", Toast.LENGTH_SHORT).show();
            }
        });

        etDate.setFocusable(false);
        etDate.setOnClickListener(v -> showDatePicker(etDate));

    }

    private void showDatePicker(EditText targetEditText) {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, selectedYear, selectedMonth, selectedDay) -> {

                    String selectedDate = String.format("%02d/%02d/%04d", selectedDay, selectedMonth + 1, selectedYear);
                    targetEditText.setText(selectedDate);
                }, year, month, day);

        datePickerDialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_PICK && resultCode == RESULT_OK && data != null) {
            Uri selectedUri = data.getData();
            if (selectedUri != null) {
                // Copy gambar ke internal storage dan simpan path-nya
                String path = copyImageToInternalStorage(selectedUri);
                if (path != null) {
                    imageUri = Uri.fromFile(new File(path)); // ubah imageUri agar aman
                    imgPreview.setImageURI(imageUri); // tampilkan preview
                } else {
                    Toast.makeText(this, "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private String copyImageToInternalStorage(Uri uri) {
        try {
            InputStream inputStream = getContentResolver().openInputStream(uri);
            File imageDir = new File(getFilesDir(), "images");
            if (!imageDir.exists()) imageDir.mkdir();

            String fileName = "img_" + System.currentTimeMillis() + ".jpg";
            File destFile = new File(imageDir, fileName);

            OutputStream outputStream = new FileOutputStream(destFile);
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, bytesRead);
            }

            inputStream.close();
            outputStream.close();

            return destFile.getAbsolutePath();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    private void submitLogbook() {
        String date = etDate.getText().toString().trim();
        String title = etTitle.getText().toString().trim();
        String location = etLocation.getText().toString().trim();
        String description = etDescription.getText().toString().trim();

        if (date.isEmpty() || title.isEmpty() || location.isEmpty() || description.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        LogbookEntry entry = new LogbookEntry(date, title, location, description, imageUri);
        SessionManager.logbookList.add(entry);

        showSuccessDialog();

        etDate.setText("");
        etTitle.setText("");
        etLocation.setText("");
        etDescription.setText("");
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
