package com.snippetUimanagement.snippet.runAllTests

import com.snippetUimanagement.repos.SnippetTestingScripts
import com.snippetUimanagement.repos.dto.TestResultDto
import java.util.UUID

class RunAllTests {
    companion object {
        fun runAllTests (token: String, snippetId: UUID ): List<TestResultDto> {
            return SnippetTestingScripts.runTestsBySnippetUuid(token, snippetId).toList()
        }
    }

}