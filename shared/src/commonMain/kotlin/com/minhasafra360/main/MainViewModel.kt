package com.minhasafra360.main

import com.minhasafra360.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class MainViewModel: BaseViewModel() {
    private val _principalState: MutableStateFlow<MainState> =
        MutableStateFlow(MainState())

    val uiState = _principalState.asStateFlow()

    fun handleEvent(event: MainEvent) {

        when(event) {
            MainEvent.OnDoneSplash -> {
                _principalState.update {
                    MainState(status = MainStatus.DONE)
                }
            }
        }
    }
}