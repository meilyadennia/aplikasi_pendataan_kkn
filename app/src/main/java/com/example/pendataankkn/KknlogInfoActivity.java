package com.example.pendataankkn;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class KknlogInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kknlog_info);

        TextView tvInfo = findViewById(R.id.tvKknlogInfo);
        tvInfo.setText("KKNLog adalah aplikasi pendataan kegiatan Kuliah Kerja Nyata (KKN) berbasis Android. " +
                "Aplikasi ini membantu mahasiswa untuk mencatat aktivitas harian mereka secara terstruktur, " +
                "meliputi tanggal, lokasi, deskripsi kegiatan, dan foto dokumentasi. " +
                "Fitur yang tersedia mencakup logbook harian, upload foto, serta manajemen data KKN oleh ketua dan anggota.");
    }
}
