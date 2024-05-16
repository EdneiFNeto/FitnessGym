package com.minhasafra360.principal

import com.minhasafra360.BaseViewModel
import com.minhasafra360.db.entity.ExercisesEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class PrincipalViewModel(
    private val useCase: PrincipalUseCase
): BaseViewModel() {

    private val _principalState: MutableStateFlow<PrincipalState> =
        MutableStateFlow(PrincipalState())

    val principalState: StateFlow<PrincipalState>
        get() = _principalState

    init {
        scope.launch {
            _principalState.update {
                PrincipalState(entity = useCase.getExercises())
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