package com.fitnessgym

import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ErrorApi(
    @SerialName("Data") val data: DataError? = null,
    @SerialName("Code") val code: Int?,
    @SerialName("Message") val message: String?,
    @SerialName("Total") val total: Int?
)

@Serializable
data class DataError(
    @SerialName("Status") val status: Int?
)

suspend fun HttpResponse.errorApi(): Resource<Nothing> {
    val errorApi = this.body<ErrorApi>()
    println("errorApi: $errorApi")
    return Resource(data = null, errorPair = Pair(errorApi.message, errorApi.data), status = Status.FAIL)
}