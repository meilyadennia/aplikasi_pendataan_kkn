package com.example.pendataankkn;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.List;

public class LogbookAdapter extends RecyclerView.Adapter<LogbookAdapter.ViewHolder> {

    private List<LogbookModel> list;
    private Context context;
    private LogbookDAO dao;

    public LogbookAdapter(List<LogbookModel> list, Context context, LogbookDAO dao) {
        this.list = list;
        this.context = context;
        this.dao = dao;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_logbook, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LogbookModel item = list.get(position);

        holder.tvTitle.setText(item.getTitle());
        holder.tvDate.setText(item.getDate());
        holder.tvLocation.setText(item.getLocation());
        holder.tvDescription.setText(item.getDescription());


        // Tampilkan gambar jika ada
        if (item.getImageUri() != null && !item.getImageUri().isEmpty()) {
            File imageFile = new File(item.getImageUri());
            if (imageFile.exists()) {
                holder.imgPreview.setImageURI(Uri.fromFile(imageFile));
            } else {
                holder.imgPreview.setImageResource(android.R.drawable.ic_menu_gallery); // opsional default
            }
        } else {
            holder.imgPreview.setImageResource(android.R.drawable.ic_menu_gallery); // jika tidak ada gambar
        }

        // Delete: hapus dari database dan list
        holder.btnDelete.setOnClickListener(v -> {
            new AlertDialog.Builder(context)
                    .setTitle("Konfirmasi Hapus")
                    .setMessage("Apakah kamu yakin ingin menghapus logbook ini?")
                    .setPositiveButton("Hapus", (dialog, which) -> {
                        dao.delete(item.getId());
                        list.remove(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, list.size());
                        Toast.makeText(context, "Data dihapus", Toast.LENGTH_SHORT).show();
                    })
                    .setNegativeButton("Batal", (dialog, which) -> dialog.dismiss())
                    .show();
        });


        // Edit: buka EditLogbookActivity
        holder.btnEdit.setOnClickListener(v -> {
            Intent intent = new Intent(context, EditLogbookActivity.class);
            intent.putExtra("logbook_id", item.getId());
            context.startActivity(intent);
        });

        holder.btnView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailLogbookActivity.class);
            intent.putExtra("title", item.getTitle());
            intent.putExtra("date", item.getDate());
            intent.putExtra("location", item.getLocation());
            intent.putExtra("description", item.getDescription());
            intent.putExtra("imageUri", item.getImageUri());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDate, tvLocation, tvDescription;
        Button btnEdit, btnDelete, btnView;
        ImageView imgPreview;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnView = itemView.findViewById(R.id.btnView);
            imgPreview = itemView.findViewById(R.id.imgPreview);
        }
    }
}
