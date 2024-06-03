package com.fitnessgym.exercises

import com.fitnessgym.BaseViewModel
import com.fitnessgym.FetchStatus
import com.fitnessgym.db.entity.ExercisesEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ExercisesViewModel(
    private val useCase: ExercisesUseCase
) : BaseViewModel() {

    private val _uiState: MutableStateFlow<ExercisesUIState> =
        MutableStateFlow(ExercisesUIState())

    val uiState: StateFlow<ExercisesUIState>
        get() = _uiState

    init {
        scope.launch {
            _uiState.emit(ExercisesUIState(exercises = useCase.selectExercises()))
        }
    }

    fun handleEvent(event: ExercisesEvent) {
        when (event) {
            is ExercisesEvent.OnAddExercises -> {
                scope.launch {
                    addExercises(event.entity)
                }
            }

            is ExercisesEvent.OnDeleteExercises -> scope.launch {
                val entity = exercisesEntity(event.data)
                deleteExercises(entity)
                _uiState.emit(ExercisesUIState(
                    exercises = useCase.selectExercises(),
                    fetchStatus = FetchStatus.DONE
                ))
            }

            is ExercisesEvent.OnUpdateExercises -> scope.launch {
                val entity = exercisesEntity(event.data)
                updateExercises(entity)
                _uiState.emit(ExercisesUIState(
                    exercises = useCase.selectExercises(),
                    fetchStatus = FetchStatus.DONE
                ))
            }

            is ExercisesEvent.OnDoneFetch -> scope.launch {
                _uiState.emit(ExercisesUIState())
            }
        }
    }

    private fun exercisesEntity(data: Map<String, String>) =
        ExercisesEntity(
            id = (data["id"] ?: "0").toLong(),
            name = (data["name"] ?: "0"),
            repeat = (data["repeat"] ?: "0").toLong(),
            interval = (data["interval"] ?: "0").toLong(),
            peso = (data["peso"] ?: "0").toLong(),
            type = (data["type"] ?: "0").toLong()
        )

    private suspend fun addExercises(entity: ExercisesEntity) {
        println("AddExercisesViewModel: addExercises = $entity")
        try {
            if (
                entity.name.isBlank() ||
                entity.repeat <= 0L ||
                entity.interval <= 0L ||
                entity.peso <= 0L
            ) throw Exception("AddExercisesViewModel: Invalid input")

            if (useCase.addExercises(entity) == 0L) throw Exception("AddExercisesViewModel: Fail save exercises")
            _uiState.emit(ExercisesUIState(fetchStatus = FetchStatus.DONE))
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.emit(ExercisesUIState(error = e.message, fetchStatus = FetchStatus.FAIL))
        }
    }

    private suspend fun updateExercises(entity: ExercisesEntity) {
        try {
            if (
                entity.name.isBlank() ||
                entity.repeat <= 0L ||
                entity.interval <= 0L ||
                entity.peso <= 0L
            ) throw Exception("AddExercisesViewModel: Invalid input")

            if (useCase.updateExercises(entity) == 0L) throw Exception("AddExercisesViewModel: Fail save exercises")
            _uiState.emit(ExercisesUIState(fetchStatus = FetchStatus.DONE))
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.emit(ExercisesUIState(error = e.message, fetchStatus = FetchStatus.FAIL))
        }
    }

    private suspend fun deleteExercises(entity: ExercisesEntity) {
        println("$TAG: addExercises = $entity")
        try {
            if (
                entity.id != 0L
            ) throw Exception("$TAG: Invalid input")

            if (useCase.deleteExercises(entity.id) == 0L) throw Exception("$TAG: Fail save exercises")
            _uiState.emit(ExercisesUIState(fetchStatus = FetchStatus.DONE))
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.emit(ExercisesUIState(error = e.message, fetchStatus = FetchStatus.FAIL))
        }
    }

    companion object {
        const val TAG = "ExercisesViewModel"
    }
}