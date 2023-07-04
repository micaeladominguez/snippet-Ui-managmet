package com.snippetUimanagement.snippet.getLintingRules

import com.snippetUimanagement.classes.FormatInfoRule
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetLintingRules {
    companion object {
        fun getLintingRules(userUuid: String): FormatInfoRule {
            return SnippetManagmentServiceSnippet.getAllLintingRules(userUuid)
        }
    }
}
