package com.fitnessgym.principal.di

import com.fitnessgym.db.source.ExercisesDataSource
import com.fitnessgym.principal.PrincipalRepository
import com.fitnessgym.principal.PrincipalRepositoryImpl
import com.fitnessgym.principal.PrincipalUseCase
import com.fitnessgym.principal.PrincipalViewModel
import org.koin.dsl.module

val principalModule = module {
    single<PrincipalViewModel> { PrincipalViewModel(get()) }
    single<PrincipalUseCase> { PrincipalUseCase(get()) }
    single<PrincipalRepository> { PrincipalRepositoryImpl(get()) }
    single<ExercisesDataSource> { ExercisesDataSource(get()) }
}