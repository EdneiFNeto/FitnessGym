package com.minhasafra360.addexercises.di

import com.minhasafra360.addexercises.AddExercisesRepository
import com.minhasafra360.addexercises.AddExercisesRepositoryImpl
import com.minhasafra360.addexercises.AddExercisesUseCase
import com.minhasafra360.addexercises.AddExercisesViewModel
import com.minhasafra360.db.source.ExercisesDataSource
import org.koin.dsl.module

val addExercisesModule = module {
    single<AddExercisesViewModel> { AddExercisesViewModel(get()) }
    single<AddExercisesUseCase> { AddExercisesUseCase(get()) }
    single<AddExercisesRepository> { AddExercisesRepositoryImpl(get()) }
    single<ExercisesDataSource> { ExercisesDataSource(get()) }
}