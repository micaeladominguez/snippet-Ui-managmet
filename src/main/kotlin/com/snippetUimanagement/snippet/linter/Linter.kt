package com.snippetUimanagement.snippet.linter

import com.snippetUimanagement.classes.AnalyzeData
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.*

class Linter {
    companion object {
        fun lint(token:String,snippetId: UUID): AnalyzeData?{
            val snippetRole = SnippetManageRepositories.canAccessASnippet(snippetId, token)
            if(snippetRole != null){
                return SnippetManagmentServiceSnippet.getLintedSnippet(snippetId, token)
            }
            return null
        }
    }
}