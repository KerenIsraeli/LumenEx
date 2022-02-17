package com.example.lumenex.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lumenex.model.Breath
import com.example.lumenex.model.BreathRepository

class BreathViewModel : ViewModel(){

    // region Members

    val _breaths: MutableLiveData<List<Breath>?> = BreathRepository.getInstance().breaths

    // endregion

    // region Public Methods

    /**
     * Load json file
     */
    fun loadJson() {
       BreathRepository.getInstance().loadJson()
    }

    fun getBreathByIndedx(index: Int): Breath? {
        return _breaths.value?.let {
            it[index]
        }
    }

    // endregion

}