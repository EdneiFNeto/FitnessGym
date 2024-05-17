package com.minhasafra360.main.di

import com.minhasafra360.main.MainViewModel
import org.koin.dsl.module

val mainModule = module {
    single<MainViewModel> { MainViewModel() }
}