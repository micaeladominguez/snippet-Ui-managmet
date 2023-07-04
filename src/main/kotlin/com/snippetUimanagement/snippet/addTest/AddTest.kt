package com.snippetUimanagement.snippet.addTest

import com.snippetUimanagement.classes.TestCase
import com.snippetUimanagement.repos.SnippetTestingScripts
import com.snippetUimanagement.repos.dto.CreateTestCaseDto
import java.util.UUID

class AddTest {
    companion object {
        fun addTest(token: String, snippetId: UUID, createTestDTO: CreateTestCaseDto): TestCase {
            return SnippetTestingScripts.addTest(token, snippetId, createTestDTO)
        }
    }

}