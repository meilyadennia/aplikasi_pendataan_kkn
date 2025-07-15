package com.example.pendataankkn;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class LogbookListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    LogbookAdapter adapter;
    ArrayList<LogbookModel> logbookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook_list);

        recyclerView = findViewById(R.id.recyclerViewLogbook);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Button btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(LogbookListActivity.this, LogbookActivity.class);
            startActivity(intent);
            finish();
        });

        // Simulasi data
        logbookList = new ArrayList<>();

        LogbookDAO dao = new LogbookDAO(this);
        logbookList = dao.getAll();
        adapter = new LogbookAdapter(logbookList, this, dao);
        recyclerView.setAdapter(adapter);
    }
}
