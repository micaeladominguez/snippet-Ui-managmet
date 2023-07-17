package com.snippetUimanagement.snippet.editTest

import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.repos.SnippetTestingScripts
import com.snippetUimanagement.repos.dto.CreateTestCaseDto
import java.util.UUID

class EditTest {
    companion object {
        fun editTest(token: String, testId: UUID, createTestDTO: CreateTestCaseDto, url: String): Any {
            return try {
                SnippetTestingScripts.editTest(token, testId, createTestDTO, url)
            }catch(e: Throwable){
                ErrorResponse(e.message ?: "An error occurred")
            }
        }
    }

}