package com.efronnypardede.xpensive.dashboard

import android.util.Log
import androidx.lifecycle.*
import com.efronnypardede.xpensive.data.entity.XpenseHistory
import com.efronnypardede.xpensive.data.entity.XpenseSource
import com.efronnypardede.xpensive.data.repository.XpenseHistoryRepository
import com.efronnypardede.xpensive.data.repository.XpenseSourceRepository
import com.efronnypardede.xpensive.di.FullDateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.DateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class AddHistoryViewModel @Inject constructor(
    private val xpenseSourceRepository: XpenseSourceRepository,
    private val xpenseHistoryRepository: XpenseHistoryRepository,
    @FullDateFormatter private val fullDateFormatter: DateFormat,
) : ViewModel() {
    val xpenseAmount = MutableLiveData<String>()
    val xpenseRemarks = MutableLiveData<String>()
    val selectedSource = MutableLiveData<Long>()
    val selectedCategory = MutableLiveData<Int>()
    val xpenseDate = MutableLiveData(Calendar.getInstance().time)
    val formattedXpenseDate: LiveData<String> = xpenseDate.map(fullDateFormatter::format)

    var selectedSourceId: Long = 1L
        set(value) {
            field = value
            selectedSource.value = value
        }

    var selectedCategoryId: Long = 1L
        set(value) {
            field = value
            selectedCategory.value = value.toInt()
        }

    private val showDatePickerMutable = MutableLiveData<Unit>()
    val showDatePicker: LiveData<Unit> = showDatePickerMutable

    val isFormValid: LiveData<Boolean> = xpenseAmount.switchMap { inputAmount ->
        xpenseRemarks.map { inputRemarks ->
            !inputAmount.isNullOrBlank() && !inputRemarks.isNullOrBlank()
        }
    }

    val xpenseSourceList: LiveData<List<XpenseSource>> = liveData {
        emit(xpenseSourceRepository.fetchAll())
    }

    private val isSuccessMutable = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean> = isSuccessMutable

    private val errorMessageMutable = MutableLiveData<String>()
    val errorMessage: LiveData<String> = errorMessageMutable

    fun showDatePicker() {
        showDatePickerMutable.value = Unit
    }

    fun save() {
        if (!xpenseAmount.value.isNullOrBlank() &&
            !xpenseRemarks.value.isNullOrBlank() &&
            selectedSource.value != null &&
            selectedCategory.value != null &&
            xpenseDate.value != null
        ) {
            val newData = XpenseHistory(
                amount = xpenseAmount.value!!.toDouble(),
                sourceId = selectedSource.value!!,
                typeId = selectedCategory.value!!,
                description = xpenseRemarks.value!!,
                date = xpenseDate.value!!
            )
            viewModelScope.launch {
                runCatching {
                    xpenseHistoryRepository.addNew(newData)
                }.also {
                    Log.d("AddHistory", "Data is: $newData")
                    isSuccessMutable.value = it.isSuccess
                    errorMessageMutable.value = it.exceptionOrNull()?.message
                }
            }
        }
    }
}
