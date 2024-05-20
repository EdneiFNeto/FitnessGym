package com.fitnessgym.main

import com.fitnessgym.BaseViewModel
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