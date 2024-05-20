package com.minhasafra360.di

import com.minhasafra360.addexercises.di.addExercisesModule
import com.minhasafra360.main.di.mainModule
import com.minhasafra360.principal.di.principalModule

val sharedKoinModule = listOf(
    networkModule,
    principalModule,
    mainModule,
    addExercisesModule
)