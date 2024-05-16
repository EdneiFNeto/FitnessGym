package com.minhasafra360.di

import com.minhasafra360.principal.di.principalModule

val sharedKoinModule = listOf(
    networkModule,
    principalModule
)