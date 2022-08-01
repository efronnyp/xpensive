package com.efronnypardede.xpensive.dashboard

import androidx.lifecycle.*
import com.efronnypardede.xpensive.data.repository.XpenseHistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val repository: XpenseHistoryRepository,
) : ViewModel() {
    private val fetchTrigger = MutableLiveData<Unit>()

    val xpenseHistoryList = fetchTrigger.switchMap {
        liveData {
            emit(repository.fetchAll())
        }
    }

    val isNoHistory: LiveData<Boolean> = xpenseHistoryList.map { it.isEmpty() }

    fun fetchData() {
        fetchTrigger.value = Unit
    }
}
