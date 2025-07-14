package com.example.pendataankkn;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        // Delete: hapus dari database dan list
        holder.btnDelete.setOnClickListener(v -> {
            dao.delete(item.getId()); // hapus dari SQLite
            list.remove(position);   // hapus dari list lokal
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, list.size());
            Toast.makeText(context, "Data dihapus", Toast.LENGTH_SHORT).show();
        });

        // Edit: buka EditLogbookActivity dan kirim ID logbook
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

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvLocation = itemView.findViewById(R.id.tvLocation);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
            btnView = itemView.findViewById(R.id.btnView);
        }
    }
}
