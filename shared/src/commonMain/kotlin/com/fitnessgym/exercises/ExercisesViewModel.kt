package com.fitnessgym.exercises

import com.fitnessgym.BaseViewModel
import com.fitnessgym.FetchStatus
import com.fitnessgym.db.entity.ExercisesEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
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
            is ExercisesEvent.OnAddExercises -> addExercises(event.entity)

            is ExercisesEvent.OnDeleteExercises ->
                deleteExercises(exercisesEntity(event.data))

            is ExercisesEvent.OnUpdateExercises -> scope.launch {
                updateExercises(exercisesEntity(event.data))
            }

            is ExercisesEvent.OnFetchNone ->
                _uiState.update { ExercisesUIState(fetchStatus = FetchStatus.NONE) }
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

    private fun addExercises(entity: ExercisesEntity) {
        println("$TAG: addExercises = $entity")
        try {
            if (
                entity.name.isBlank() ||
                entity.repeat <= 0L ||
                entity.interval <= 0L ||
                entity.peso <= 0L
            ) throw Exception("$TAG: Required all input")

            CoroutineScope(Dispatchers.Main).launch {
                if (useCase.addExercises(entity) == 0L)
                    throw Exception("$TAG: Fail save exercises")
            }

            println("$TAG success save exercises")
            _uiState.update {
                ExercisesUIState(fetchStatus = FetchStatus.DONE)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.update {
                ExercisesUIState(error = e.message, fetchStatus = FetchStatus.FAIL)
            }
        }
    }

    private fun updateExercises(entity: ExercisesEntity) {
        try {
            if (
                entity.name.isBlank() ||
                entity.repeat <= 0L ||
                entity.interval <= 0L ||
                entity.peso <= 0L
            ) throw Exception("$TAG: Invalid input")

            CoroutineScope(Dispatchers.IO).launch {
                if (useCase.updateExercises(entity) == 0L) throw Exception("$TAG: Fail save exercises")
            }

            _uiState.update {
                ExercisesUIState(fetchStatus = FetchStatus.DONE)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.update {
                ExercisesUIState(error = e.message, fetchStatus = FetchStatus.FAIL)
            }
        }
    }

    private fun deleteExercises(entity: ExercisesEntity) {
        println("$TAG: addExercises = $entity")

        try {
            if (
                entity.id != 0L
            ) throw Exception("$TAG: Invalid input")

            CoroutineScope(Dispatchers.IO).launch {
                if (useCase.deleteExercises(entity.id) == 0L) throw Exception("$TAG: Fail save exercises")
            }

            _uiState.update {
                ExercisesUIState(fetchStatus = FetchStatus.DONE)
            }
        } catch (e: Exception) {
            e.printStackTrace()
            _uiState.update {
                ExercisesUIState(error = e.message, fetchStatus = FetchStatus.FAIL)
            }
        }
    }

    companion object {
        const val TAG = "ExercisesViewModel"
    }
}