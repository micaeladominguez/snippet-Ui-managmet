package com.snippetUimanagement.snippet.format

import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class Format {
    fun format(snippetId: String){
        SnippetManagmentServiceSnippet.getFormattedSnippet(snippetId)
    }
}