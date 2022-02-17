package com.example.lumenex.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.example.lumenex.GraphRequestListener;
import com.example.lumenex.R;
import com.example.lumenex.databinding.ActivityMainBinding;
import com.example.lumenex.model.Breath;
import com.example.lumenex.viewmodel.BreathViewModel;

public class MainActivity extends AppCompatActivity implements GraphRequestListener {

    // region Consts

    private final int PERMISSIONS_REQUEST_CODE = 201;
    private final String TAG = "Activity.TAG";

    // endregion

    // region Members

    private ActivityMainBinding binding;

    private BreathViewModel breathsViewModel;

    // endregion

    // region Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(LayoutInflater.from(this));
        breathsViewModel = new ViewModelProvider(this).get(BreathViewModel.class);

        // Check permissions.
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions();
        }
        else {
            // Permission are already granted.
            load();
        }

        setContentView(binding.getRoot());
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSIONS_REQUEST_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permissions granted, load.
                    load();
                }

                break;
            default:
                Log.e(TAG, "Read permissions not granted, don't load anything");

                break;
        }
    }

    // endregion

    // region Private Methods

    /**
     * Method loads app data, assuming permissions granted.
     */
    private void load() {
        BreathsAdapter adapter = new BreathsAdapter(this);

        binding.breathGraph.setLayoutManager(new LinearLayoutManager(this));
        binding.breathGraph.setAdapter(adapter);

        breathsViewModel.get_breaths().observe(this, breaths -> {

            if (breaths != null && breaths.size() > 0) {
                binding.noData.setVisibility(View.GONE);
                binding.breathGraph.setVisibility(View.VISIBLE);

                adapter.submitList(breaths);
            }
        });

       breathsViewModel.loadJson();
    }

    /**
     * Request permissions on runtime.
     */
    private void requestPermissions() {
        // Note granted.
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                PERMISSIONS_REQUEST_CODE);
    }

    // endregion

    // region GraphRequestListener

    @Override
    public void showGraph(Breath breath) {
        GraphFragment graphFragment = GraphFragment.newInstance(breath);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.graph_container, graphFragment, "findThisFragment")
                .addToBackStack(null)
                .commit();
    }

    // endregion

}