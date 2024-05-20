package com.fitnessgym.addexercises

import com.fitnessgym.BaseViewModel
import com.fitnessgym.FetchStatus
import com.fitnessgym.db.entity.ExercisesEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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

            AddExercisesEvent.OnDoneFetch -> scope.launch {
                _uiState.emit(AddExercisesUIState())
            }
        }
    }

    private suspend fun addExercises(entity: ExercisesEntity) {
        println("AddExercisesViewModel: addExercises = $entity")
        try {
            if (
                entity.name.isNullOrBlank() ||
                entity.repeat!! <= 0L ||
                entity.interval!! <= 0L ||
                entity.peso!! <= 0L
            ) throw Exception("AddExercisesViewModel: Invalid input")

            if (useCase.addExercises(entity) == 0L) throw Exception("AddExercisesViewModel: Fail save exercises")
            _uiState.emit(AddExercisesUIState(status = FetchStatus.DONE))
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.emit(AddExercisesUIState(error = e.message, status = FetchStatus.FAIL))
        }
    }
}