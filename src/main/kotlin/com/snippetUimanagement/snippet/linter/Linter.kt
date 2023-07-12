package com.snippetUimanagement.snippet.linter

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.*

class Linter {
    companion object {
        fun lint(token:String,snippetId: UUID): Any?{
            try {
                val snippetRole = SnippetManageRepositories.canAccessASnippet(snippetId, token)
                if(snippetRole != null){
                    return SnippetManagmentServiceSnippet.getLintedSnippet(snippetId, token)
                }
                return null
            }catch (e: Throwable){
                return e
            }

        }
    }
}