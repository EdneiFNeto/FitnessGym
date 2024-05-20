package com.fitnessgym.main.di

import com.fitnessgym.main.MainViewModel
import org.koin.dsl.module

val mainModule = module {
    single<MainViewModel> { MainViewModel() }
}