package com.fitnessgym.exercises.di

import com.fitnessgym.exercises.ExercisesRepository
import com.fitnessgym.exercises.ExercisesRepositoryImpl
import com.fitnessgym.exercises.ExercisesUseCase
import com.fitnessgym.exercises.ExercisesViewModel
import com.fitnessgym.db.source.ExercisesDataSource
import org.koin.dsl.module

val addExercisesModule = module {
    single<ExercisesViewModel> { ExercisesViewModel(get()) }
    single<ExercisesUseCase> { ExercisesUseCase(get()) }
    single<ExercisesRepository> { ExercisesRepositoryImpl(get()) }
    single<ExercisesDataSource> { ExercisesDataSource(get()) }
}