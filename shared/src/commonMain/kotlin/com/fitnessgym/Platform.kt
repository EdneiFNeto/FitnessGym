package com.fitnessgym

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform