package com.minhasafra360.android.di

import app.cash.sqldelight.db.SqlDriver
import com.minhasafra360.db.AppDatabase
import com.minhasafra360.db.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory(androidContext()).createDriver() }
    single<AppDatabase> { AppDatabase(get()) }
}