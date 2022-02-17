package com.example.lumenex.model;

import android.os.Environment;

import com.example.lumenex.utilities.JsonUtils;

import java.util.List;

import androidx.lifecycle.MutableLiveData;

public class BreathRepository {

    // region Consts

    public static final String JSON_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() +
            "/breaths.json";

    // region Members

    private MutableLiveData<List<Breath>> _breaths;

    // endregion

    // region Singleton

    private static BreathRepository _instance = null;

    private BreathRepository() {
        _breaths = new MutableLiveData<>();
    }

    public static BreathRepository getInstance() {
        if (_instance == null) {
            _instance = new BreathRepository();
        }

        return _instance;
    }

    // endregion

    // region Properties

    public MutableLiveData<List<Breath>> getBreaths() {
        return _breaths;
    }

    public void setBreaths(List<Breath> breaths) {
        _breaths.setValue(breaths);
    }

    // endregion

    // region Public Methods

    /**
     * Load json file
     */
    public void loadJson() {
        // TODO backkground
        List<Breath> breaths = JsonUtils.readSubListFromJson(JSON_PATH, Breath.class);

        if (breaths != null) {
            setBreaths(breaths);
        }
    }

    // endregion
}
