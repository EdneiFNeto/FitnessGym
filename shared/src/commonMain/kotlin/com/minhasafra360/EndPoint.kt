package com.minhasafra360

import com.minhasafra360.utils.BaseURl

enum class EndPoint(val description: String) {
    CREATE_ACCOUNT("${BaseURl.BASE_URL}/Api/Usuario/Cadastro"),
    SEARCH_USER("${BaseURl.BASE_URL}/Api/Usuario/Buscar"),
}