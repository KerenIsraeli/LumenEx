package com.example.lumenex.views;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lumenex.R;
import com.example.lumenex.model.Breath;

public class GraphFragment extends Fragment {

    public static final String TAG = "GraphFragment.TAG";

    private Breath _breath;

    public GraphFragment() {
        // Required empty public constructor
    }

    public void setBreath(Breath breath) {
        _breath = breath;
    }

    public static GraphFragment newInstance(Breath breath) {
        GraphFragment fragment = new GraphFragment();
        fragment.setBreath(breath);

        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_graph, container, false);
    }
}