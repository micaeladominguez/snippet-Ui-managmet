package com.snippetUimanagement.snippet.runAllTests

import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.SnippetTestingScripts

class RunAllTests {
    fun runAllTests (snippetId: String ) {
        val snippetResult = SnippetManagmentServiceSnippet.getResult(snippetId)
        val snippetTests = SnippetTestingScripts.runTestsBySnippetUuid(snippetId, snippetResult.interpreterResult)
    }
}