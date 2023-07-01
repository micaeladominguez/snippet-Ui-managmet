package com.snippetUimanagement.snippet.addTest

import com.snippetUimanagement.repos.SnippetTestingScripts

class AddTest {
    fun addTest(snippetId: String, outPutResult: String) {
        SnippetTestingScripts.addTest(snippetId, outPutResult)
    }
}