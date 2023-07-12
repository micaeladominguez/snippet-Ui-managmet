package com.snippetUimanagement.classes

import java.util.UUID

data class SnippetById(
    val code: String,
    val id: UUID,
    val staticCodeCorrect: Boolean?,
    val linesErrors: String
)