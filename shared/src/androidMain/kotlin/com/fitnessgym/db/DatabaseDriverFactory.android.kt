package com.fitnessgym.db

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.fitnessgym.FitnessGymDatabase

actual class DatabaseDriverFactory(private val context: Context) {
    actual fun createDriver(): SqlDriver =
        AndroidSqliteDriver(
            schema = FitnessGymDatabase.Schema,
            context = context,
            name = "fitnessGymDatabase"
        )
}