package com.snippetUimanagement.snippet.defineLintingRules

import com.snippetUimanagement.classes.FormatInfoRule
import com.snippetUimanagement.classes.LintingRules
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.dto.UpdateRules

class DefineLintingRules {
    companion object {
        fun lintingRules(token: String, updatedRules : UpdateRules) : FormatInfoRule {
            return SnippetManagmentServiceSnippet.updateLintingRules(token, updatedRules)
        }
    }
}