package com.snippetUimanagement.snippet.getFormaterRules

import com.snippetUimanagement.classes.FormatInfoRule
import com.snippetUimanagement.classes.FormatRules
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetFormattedRules {
    companion object {
        fun getFormattedRules(token: String)  : FormatInfoRule {
            return SnippetManagmentServiceSnippet.getAllFormatRules(token)
        }
    }


}