package com.example.lumenex.views;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lumenex.GraphRequestListener;
import com.example.lumenex.R;
import com.example.lumenex.databinding.BreathItemBinding;
import com.example.lumenex.model.Breath;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class BreathsAdapter extends ListAdapter<Breath, BreathsAdapter.BreathViewHolder> {

    // region Consts

    private static final String pattern = "MM/dd/yyyy HH:mm:ss";

    // region Members

    /**
     * Collection of adapter items.
     */
    private List<Breath> _breaths = null;

    private GraphRequestListener _listener;
    private DateFormat format = new SimpleDateFormat(pattern);

    // endregion

    // region C'tors

    protected BreathsAdapter(GraphRequestListener listener) {
        super(new BreathDiffUtil());

        _listener = listener;
    }

    // endregion

    // region RecyclerView.Adapter

    @Override
    public BreathViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BreathViewHolder(BreathItemBinding.inflate(
                LayoutInflater.from(parent.getContext()), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BreathViewHolder holder, int position) {
        Breath breath = _breaths.get(position);
        holder._binding.isValidView.setText(breath.isValid() ? R.string.valid : R.string.aborted);
        holder._binding.dateView.setText(format.format(breath.getDate()));

    }

    public void submitList(List<Breath> breaths) {
        super.submitList(breaths);

        _breaths = breaths;
    }

    // endregion

    // region Inner Class

    protected class BreathViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private BreathItemBinding _binding;

        public BreathViewHolder(BreathItemBinding binding) {
            super(binding.getRoot());

            _binding = binding;

            _binding.getRoot().setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
           _listener.showGraph(_breaths.get(getAdapterPosition()));
        }
    }

    // endregion

}
