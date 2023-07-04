package com.snippetUimanagement.repos.dto

import java.util.*

data class SnippetRoleUpdateDTO(
    val userToShareId: String,
    val snippetUUID: UUID
)