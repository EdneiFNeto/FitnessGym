package com.fitnessgym.di

import com.fitnessgym.exercises.di.addExercisesModule
import com.fitnessgym.main.di.mainModule
import com.fitnessgym.principal.di.principalModule

val sharedKoinModule = listOf(
    networkModule,
    principalModule,
    mainModule,
    addExercisesModule
)