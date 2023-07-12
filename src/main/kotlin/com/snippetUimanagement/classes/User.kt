package com.snippetUimanagement.classes

data class User(
    val email : String,
    val name: String,
    val nickName: String,
    val userId: String
){
    constructor(): this("", "", "", "")
}


data class ErrorResponse(override val message: String) : Throwable()
