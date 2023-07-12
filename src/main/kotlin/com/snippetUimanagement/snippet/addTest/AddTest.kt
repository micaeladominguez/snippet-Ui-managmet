package com.snippetUimanagement.snippet.addTest

import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.repos.SnippetTestingScripts
import com.snippetUimanagement.repos.dto.CreateTestCaseDto
import java.util.UUID

class AddTest {
    companion object {
        fun addTest(token: String, snippetId: UUID, createTestDTO: CreateTestCaseDto, url: String): Any {
            return try {
                SnippetTestingScripts.addTest(token, snippetId, createTestDTO, url)
            }catch(e: Throwable){
                ErrorResponse(e.message ?: "An error occurred")
            }
        }
    }

}