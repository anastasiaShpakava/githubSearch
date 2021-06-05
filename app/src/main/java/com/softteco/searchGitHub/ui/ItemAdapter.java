package com.softteco.searchGitHub.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.softteco.searchGitHub.R;
import com.softteco.searchGitHub.model.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {
    private List<Item> items = new ArrayList<>();
    private Context context;

    @NonNull
    @Override
    public ItemAdapter.ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_items, parent, false);
        return new ItemViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemAdapter.ItemViewHolder holder, int position) {
        Item item = items.get(position);
        if (item.getFull_name() != null && !TextUtils.isEmpty(item.getFull_name())) {
            holder.name.setText(item.getFull_name());
            holder.description.setText(item.getDescription());
            holder.watchers.setText(String.valueOf(item.getWatchers_count()));
            holder.stars.setText(String.valueOf(item.getStargazers_count()));
            holder.forks.setText(String.valueOf(item.getForks_count()));

        }
    }

    public void addItems(List<Item> newItem ) {
        items.addAll(newItem);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setItems(List<Item> items) {
        this.items = items;
        notifyDataSetChanged();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView description;
        private ChipGroup chipGroup;
        private Chip watchers;
        private Chip stars;
        private Chip forks;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.name);
            description = itemView.findViewById(R.id.description);
            chipGroup = itemView.findViewById(R.id.chip_group);
            watchers = chipGroup.findViewById(R.id.watchers);
            stars = chipGroup.findViewById(R.id.stars);
            forks = chipGroup.findViewById(R.id.forks);

            itemView.setOnClickListener(view -> {
                String url = items.get(getAdapterPosition()).getUrl();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                context.startActivity(browserIntent);
            });
        }
    }
}

