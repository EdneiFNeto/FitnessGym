package com.fitnessgym

data class Resource< out T>(
    val status: Status,
    val data: T?,
    val errorPair: Pair<String?, DataError?>? = null
)

enum class Status {
    SUCCESS,
    FAIL
}