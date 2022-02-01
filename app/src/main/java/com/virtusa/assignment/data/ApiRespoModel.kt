package com.virtusa.assignment.data

data class ApiRespoModel(
    val data: Data? = Data(),
    val support: Support? = Support()
)

data class Data(
    val id: Int? = null,
    val email: String? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    val avatar: String? = null
)

data class Support(
    val url  : String? = null,
    val text : String? = null
)
