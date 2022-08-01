package com.efronnypardede.xpensive.manage

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.efronnypardede.xpensive.data.repository.XpenseSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ManageSourceViewModel @Inject constructor(
    private val repository: XpenseSourceRepository,
) : ViewModel() {
    private val fetchTrigger = MutableLiveData<Unit>()

    val xpenseSourceList = fetchTrigger.switchMap {
        liveData {
            emit(repository.fetchAll())
        }
    }

    fun fetchData() {
        fetchTrigger.value = Unit
    }
}
