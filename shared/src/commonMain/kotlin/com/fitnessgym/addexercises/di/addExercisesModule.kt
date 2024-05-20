package com.fitnessgym.addexercises.di

import com.fitnessgym.addexercises.AddExercisesRepository
import com.fitnessgym.addexercises.AddExercisesRepositoryImpl
import com.fitnessgym.addexercises.AddExercisesUseCase
import com.fitnessgym.addexercises.AddExercisesViewModel
import com.fitnessgym.db.source.ExercisesDataSource
import org.koin.dsl.module

val addExercisesModule = module {
    single<AddExercisesViewModel> { AddExercisesViewModel(get()) }
    single<AddExercisesUseCase> { AddExercisesUseCase(get()) }
    single<AddExercisesRepository> { AddExercisesRepositoryImpl(get()) }
    single<ExercisesDataSource> { ExercisesDataSource(get()) }
}