package com.snippetUimanagement.snippet.getFormaterRules

import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetFormatedRules {

    fun getFormattedRules(userUuid: String) {
        SnippetManagmentServiceSnippet.getAllFormatRules(userUuid)
    }
}