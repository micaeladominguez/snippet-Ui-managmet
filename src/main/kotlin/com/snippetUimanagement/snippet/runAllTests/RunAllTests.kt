package com.snippetUimanagement.snippet.runAllTests

import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.repos.SnippetTestingScripts
import java.util.UUID

class RunAllTests {
    companion object {
        fun runAllTests (token: String, snippetId: UUID, url: String ): Any {
            return try {
                 SnippetTestingScripts.runTestsBySnippetUuid(token, snippetId, url).toList()
            }catch (e: Throwable){
                ErrorResponse(e.message ?: "An error occurred")
            }
        }
    }

}