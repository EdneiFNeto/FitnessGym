package com.minhasafra360.principal

import com.minhasafra360.db.entity.ExercisesEntity
import com.minhasafra360.db.source.ExercisesDataSource

class PrincipalRepositoryImpl(
    private val dataSource: ExercisesDataSource
) : PrincipalRepository {

    override suspend fun getExercises(): List<ExercisesEntity> = fakes
        //dataSource.selectExercises().ifEmpty { fakes }

    override fun saveExercises(entity: ExercisesEntity) {
        dataSource.insertExercises(entity)
    }
}

val fakes = arrayListOf(
    ExercisesEntity(id = 1, "Puxada Aberta", 3, 30, 7, 1),
    ExercisesEntity(id = 2, "Triceps Corda", 3, 30, 3, 1),
    ExercisesEntity(id = 4, "Crucifixo Invertido Maquina", 3, 30, 7, 1),
    ExercisesEntity(id = 5, "Face Pull Corda", 3, 30, 5, 1),
    ExercisesEntity(id = 6, "Remada Sentada Triangulo", 3, 30, 7, 1),
    ExercisesEntity(id = 7, "Encolhimento ombro HBC", 3, 30, 10, 1),

    ExercisesEntity(id = 7, "Supino Reto", 3, 30, 20, 2),
    ExercisesEntity(id = 7, "Supino Inclinado", 3, 30, 20, 2),
    ExercisesEntity(id = 7, "Biceps Banco Scot", 3, 30, 5, 2),
    ExercisesEntity(id = 7, "Elevação Lateral", 3, 30, 7, 2),
    ExercisesEntity(id = 7, "Rosca Martelo(HBC)", 3, 30, 12, 2),
    ExercisesEntity(id = 7, "Elevação Lateral", 3, 30, 7, 2),
    ExercisesEntity(id = 7, "Rosca W", 3, 30, 5, 2),
)