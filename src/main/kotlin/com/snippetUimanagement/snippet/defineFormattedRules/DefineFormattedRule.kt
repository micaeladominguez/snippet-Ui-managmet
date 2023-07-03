package com.snippetUimanagement.snippet.defineFormattedRules

import com.snippetUimanagement.classes.FormatRules
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class DefineFormattedRule {
    fun formatRules(userUuid: String, updatedRules : List<FormatRules>) {
        SnippetManagmentServiceSnippet.updateFormatRules(userUuid, updatedRules)
    }
}