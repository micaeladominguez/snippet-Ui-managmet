package com.snippetUimanagement.entities

data class Snippet(
    val snippetId: String,
    val snippetCode: String,
    val role: Role,
    val staticCodeCorrect: Boolean
)