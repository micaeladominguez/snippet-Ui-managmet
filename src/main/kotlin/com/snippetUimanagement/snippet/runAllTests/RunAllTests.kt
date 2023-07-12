package com.snippetUimanagement.snippet.runAllTests

import com.snippetUimanagement.repos.SnippetTestingScripts
import java.util.UUID

class RunAllTests {
    companion object {
        fun runAllTests (token: String, snippetId: UUID ): Any {
            return try {
                 SnippetTestingScripts.runTestsBySnippetUuid(token, snippetId).toList()
            }catch (e: Throwable){
                e
            }
        }
    }

}