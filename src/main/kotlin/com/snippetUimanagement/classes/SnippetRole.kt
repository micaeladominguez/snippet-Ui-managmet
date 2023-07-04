package com.snippetUimanagement.classes

import java.util.UUID


enum class Role {
    READ,
    WRITE
}
data class SnippetRole(
    val id: UUID,
    val role: Role?
)

data class SnippetInfo(
    val id: UUID,
    val userId: String,
    val snippetUuid: UUID,
    val role: RoleN
){
    constructor(): this(UUID.randomUUID(),"",UUID.randomUUID(),RoleN())
}

data class RoleN(
    val id: String,
    val name: String
){
    constructor(): this("", "")
}