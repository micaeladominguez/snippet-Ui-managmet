package com.snippetUimanagement.classes


enum class Role {
    READ,
    WRITE
}
data class SnippetRole(
    val snippetId: String,
    val role: Role
)