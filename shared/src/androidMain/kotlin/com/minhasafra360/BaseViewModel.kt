package com.minhasafra360

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope

actual open class BaseViewModel: ViewModel() {

    actual val scope = viewModelScope
    protected actual override fun onCleared() {
        super.onCleared()
    }
}