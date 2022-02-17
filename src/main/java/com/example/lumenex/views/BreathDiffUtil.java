package com.example.lumenex.views;

import com.example.lumenex.model.Breath;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

public class BreathDiffUtil extends DiffUtil.ItemCallback<Breath> {
    @Override
    public boolean areItemsTheSame(@NonNull Breath oldItem, @NonNull Breath newItem) {
        return oldItem == newItem;
    }

    @Override
    public boolean areContentsTheSame(@NonNull Breath oldItem, @NonNull Breath newItem) {
        return oldItem.getDate().equals(newItem.getDate());
    }
}
