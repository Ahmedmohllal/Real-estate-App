package com.example.datakotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class RealStateViewModel : ViewModel() {
    val mutableRealState : MutableLiveData<ArrayList<RealState>> = MutableLiveData()

    public fun setMutableRealState(arrayList: ArrayList<RealState>){
        mutableRealState.value = arrayList
    }
}