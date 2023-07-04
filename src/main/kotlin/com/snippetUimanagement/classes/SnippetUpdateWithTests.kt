package com.snippetUimanagement.classes

import com.snippetUimanagement.repos.dto.TestResultDto

data class SnippetUpdateWithTests(
    val snippet: Snippet?,
    val results: List<TestResultDto>
)