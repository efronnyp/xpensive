package com.efronnypardede.xpensive.manage

import android.util.Log
import androidx.lifecycle.*
import com.efronnypardede.xpensive.data.entity.XpenseSource
import com.efronnypardede.xpensive.data.repository.XpenseSourceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddSourceViewModel @Inject constructor(
    private val repository: XpenseSourceRepository
) : ViewModel() {
    val sourceName = MutableLiveData<String>()

    val currentBalance = MutableLiveData<String>()

    val description = MutableLiveData<String>()

    private val isSuccessMutable = MutableLiveData<Boolean>()
    val isSuccess: LiveData<Boolean?> = isSuccessMutable

    private val errorMessageMutable = MutableLiveData<String>()
    val errorMessage: LiveData<String?> = errorMessageMutable

    val isNameValid: LiveData<Boolean> = sourceName.map { !it.isNullOrBlank() }
    val isDescriptionValid: LiveData<Boolean> = description.map { !it.isNullOrBlank() }

    val isFormValid: LiveData<Boolean> = sourceName.switchMap { inputName ->
        description.map { inputDescription ->
            !inputName.isNullOrBlank() && !inputDescription.isNullOrBlank()
        }
    }

    fun save() {
        if (!sourceName.value.isNullOrBlank() && !description.value.isNullOrBlank()) {
            val newData = XpenseSource(
                name = sourceName.value!!,
                balance = currentBalance.value?.toDouble(),
                description = description.value!!,
            )

            viewModelScope.launch {
                runCatching {
                    repository.addNew(newData)
                }.also { result ->
                    isSuccessMutable.value = result.isSuccess
                    errorMessageMutable.value = result.exceptionOrNull()?.let {
                        Log.e("AddSource", it.message, it)
                        it.message
                    }
                }
            }
        }
    }
}
