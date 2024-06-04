package com.fitnessgym.principal

import com.fitnessgym.BaseViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class PrincipalViewModel(
    private val useCase: PrincipalUseCase
) : BaseViewModel() {

    private val _state: MutableStateFlow<PrincipalState> =
        MutableStateFlow(PrincipalState())

    val uiState: StateFlow<PrincipalState>
        get() = _state

    private var second: Long = 0
    private var totalRepeatExecuted = 0
    private var inProgress = false
    private var totalItemsRemoved = 0
    private var percent = 0f

    init {
        scope.launch {
            _state.update {
                PrincipalState(entity = useCase.getExercises())
            }
        }
    }

    fun handleEvent(event: PrincipalHandleEvent) {
        when (event) {
            PrincipalHandleEvent.OnStartTime -> startTimer()
        }
    }

    private fun startTimer() {
        if (inProgress) return
        inProgress = true
        second = 30
        scope.launch {
            do {
                delay(1.seconds)
                --second
                _state.update {
                    PrincipalState(
                        second = second,
                        totalRepeatExecuted = totalRepeatExecuted,
                        percent = percent
                    )
                }
            } while (second > 0)

            if (second == 0L) {
                second = 0
                ++totalRepeatExecuted
                _state.update {
                    if (totalRepeatExecuted >= 3) {
                        ++totalItemsRemoved
                        totalRepeatExecuted = 0
                        uiState.value.entity.removeFirst()
                    }

                    val size = uiState.value.entity.size
                    percent = (totalItemsRemoved / size.toFloat()) * 100

                    println("percent: $percent%")
                    println("percent: $totalItemsRemoved")
                    println("size: $size")
                    println("entity: ${uiState.value.entity.first()}")

                    PrincipalState(
                        totalRepeatExecuted = totalRepeatExecuted,
                        second = 0,
                        entity = uiState.value.entity,
                        percent = percent
                    )
                }
                inProgress = false
            }
        }
    }
}