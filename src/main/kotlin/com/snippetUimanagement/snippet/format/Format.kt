package com.snippetUimanagement.snippet.format

import com.snippetUimanagement.classes.Snippet
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.UUID

class Format {
    companion object {
        fun format(token:String,snippetId: UUID): Snippet?{
            val canUpdate = SnippetManageRepositories.checkIfICanUpdate(token, snippetId)
            if(canUpdate){
                return SnippetManagmentServiceSnippet.getFormattedSnippet(snippetId, token)
            }
            return null
        }
    }

}