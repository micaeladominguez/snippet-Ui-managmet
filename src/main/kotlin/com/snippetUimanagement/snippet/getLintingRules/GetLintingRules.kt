package com.snippetUimanagement.snippet.getLintingRules

import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetLintingRules {
    fun getLintingRules(userUuid: String) {
        SnippetManagmentServiceSnippet.getAllLintingRules(userUuid)
    }
}