package com.minhasafra360.principal.di

import com.minhasafra360.db.source.ExercisesDataSource
import com.minhasafra360.principal.PrincipalRepository
import com.minhasafra360.principal.PrincipalRepositoryImpl
import com.minhasafra360.principal.PrincipalUseCase
import com.minhasafra360.principal.PrincipalViewModel
import org.koin.dsl.module

val principalModule = module {
    single<PrincipalViewModel> { PrincipalViewModel(get()) }
    single<PrincipalUseCase> { PrincipalUseCase(get()) }
    single<PrincipalRepository> { PrincipalRepositoryImpl(get()) }
    single<ExercisesDataSource> { ExercisesDataSource(get()) }
}