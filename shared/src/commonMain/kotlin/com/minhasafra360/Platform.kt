package com.minhasafra360

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform