package com.snippetUimanagement.snippet.updateSnippet

import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.SnippetTestingScripts

class UpdateSnippet {
    fun updateSnippetCode(snippetUuid: String, snippetCode: String) {
        //llama al snippet repo
        SnippetManagmentServiceSnippet.updateSnippet(snippetUuid, snippetCode)
        //llama al snippet test
        SnippetTestingScripts.runTestsBySnippetUuid(snippetUuid)
    }

}