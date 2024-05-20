package com.fitnessgym.android.di

import app.cash.sqldelight.db.SqlDriver
import com.fitnessgym.FitnessGymDatabase
import com.fitnessgym.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    single<FitnessGymDatabase> { FitnessGymDatabase(get()) }
}