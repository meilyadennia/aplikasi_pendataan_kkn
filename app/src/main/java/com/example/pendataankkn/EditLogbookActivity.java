package com.example.pendataankkn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditLogbookActivity extends AppCompatActivity {

    EditText etDate, etTitle, etLocation, etDescription;
    Button btnUpdate;
    LogbookDAO dao;
    LogbookModel logbook;
    int logbookId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_logbook);

        dao = new LogbookDAO(this);

        etDate = findViewById(R.id.etDate);
        etTitle = findViewById(R.id.etTitle);
        etLocation = findViewById(R.id.etLocation);
        etDescription = findViewById(R.id.etDescription);
        btnUpdate = findViewById(R.id.btnUpdate);

        logbookId = getIntent().getIntExtra("logbook_id", -1);

        if (logbookId != -1) {
            for (LogbookModel item : dao.getAll()) {
                if (item.getId() == logbookId) {
                    logbook = item;
                    break;
                }
            }

            if (logbook != null) {
                etTitle.setText(logbook.getTitle());
                etDate.setText(logbook.getDate());
                etLocation.setText(logbook.getLocation());
                etDescription.setText(logbook.getDescription());
            }
        }

        btnUpdate.setOnClickListener(v -> {
            String title = etTitle.getText().toString();
            String date = etDate.getText().toString();
            String location = etLocation.getText().toString();
            String description = etDescription.getText().toString();

            if (title.isEmpty() || date.isEmpty()) {
                Toast.makeText(this, "Judul dan Tanggal wajib diisi", Toast.LENGTH_SHORT).show();
                return;
            }

            LogbookModel updated = new LogbookModel(logbookId, title, date, location, description);
            dao.update(updated);

            Toast.makeText(this, "Logbook berhasil diubah", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, LogbookListActivity.class));
            finish();
        });
    }
}
