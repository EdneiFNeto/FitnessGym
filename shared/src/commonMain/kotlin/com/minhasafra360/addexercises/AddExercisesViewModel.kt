package com.minhasafra360.addexercises

import com.minhasafra360.BaseViewModel
import com.minhasafra360.FetchStatus
import com.minhasafra360.db.entity.ExercisesEntity
import com.minhasafra360.principal.PrincipalState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class AddExercisesViewModel(
    private val useCase: AddExercisesUseCase
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<AddExercisesUIState> =
        MutableStateFlow(AddExercisesUIState())

    val uiState: StateFlow<AddExercisesUIState>
        get() = _uiState

    fun handleEvent(event: AddExercisesEvent) {
        when (event) {
            is AddExercisesEvent.OnSaveEntity -> {
                scope.launch {
                    addExercises(event.entity)
                }
            }
        }
    }

    private suspend fun addExercises(entity: ExercisesEntity) {
        println("addExercises = $entity")
        _uiState.update {
            try {
                if (
                    entity.name.isNullOrBlank() ||
                    entity.repeat!! <= 0L ||
                    entity.interval!! <= 0L ||
                    entity.peso!! <= 0L
                ) throw Exception("Invalid input")

                if(useCase.addExercises(entity) == 0L)
                    throw Exception("Fail save exercises")

                AddExercisesUIState(status = FetchStatus.DONE, error = null)
            } catch (e: Exception) {
                e.printStackTrace()
                AddExercisesUIState(error = e.message, status = FetchStatus.FAIL)
            }
        }
    }
}