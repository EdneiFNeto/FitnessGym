package com.fitnessgym.principal

import com.fitnessgym.BaseViewModel
import com.fitnessgym.db.entity.ExercisesEntity
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.time.Duration.Companion.seconds

class PrincipalViewModel(
    private val useCase: PrincipalUseCase
) : BaseViewModel() {

    private val _principalState: MutableStateFlow<PrincipalState> =
        MutableStateFlow(PrincipalState())

    val principalState: StateFlow<PrincipalState>
        get() = _principalState

    private var second = 10L
    private var totalRepeatExecuted = 0

    init {
        scope.launch {
            _principalState.update {
                PrincipalState(entity = useCase.getExercises())
            }
        }
    }

    fun handleEvent(event: PrincipalHandleEvent) {
        when (event) {
            PrincipalHandleEvent.OnStartTime -> {
                println("OnStartTime  called")
                second = 10L
                scope.launch {
                    do {
                        delay(1.seconds)
                        --second
                        _principalState.update { PrincipalState(
                            second = second,
                            totalRepeatExecuted = totalRepeatExecuted
                        ) }
                    } while (second > 0)

                    if (second == 0L) {
                        second = 0
                        ++totalRepeatExecuted
                        _principalState.update {
                            PrincipalState(
                                totalRepeatExecuted = totalRepeatExecuted,
                                second = 0
                            )
                        }
                    }
                }
            }
        }
    }

    fun saveExercises(exercisesEntity: List<ExercisesEntity>) {
        scope.launch {
            exercisesEntity.forEach {
                useCase.saveExercises(it)
            }
        }
    }
}