package com.snippetUimanagement.classes

import java.util.UUID

data class CompleteSnippet(
    val id: UUID,
    val code: String,
    val role: Role?,
    val staticCodeCorrect: Boolean?,
    val linesErrors: String
)