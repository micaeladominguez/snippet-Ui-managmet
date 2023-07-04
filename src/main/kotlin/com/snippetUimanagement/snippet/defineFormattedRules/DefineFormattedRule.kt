package com.snippetUimanagement.snippet.defineFormattedRules

import com.snippetUimanagement.classes.FormatInfoRule
import com.snippetUimanagement.classes.FormatRules
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.dto.UpdateRules

class DefineFormattedRule {
    companion object {
        fun formatRules(token: String, updatedRules : UpdateRules) : FormatInfoRule{
            return SnippetManagmentServiceSnippet.updateFormatRules(token, updatedRules)
        }
    }

}