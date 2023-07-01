package com.snippetUimanagement.snippet.defineLintingRules

import com.snippetUimanagement.entities.LintingRules
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class DefineLintingRules {
    fun lintingRules(userUuid: String, updatedRules : List<LintingRules>) {
        SnippetManagmentServiceSnippet.updateLintingRules(userUuid, updatedRules)
    }
}