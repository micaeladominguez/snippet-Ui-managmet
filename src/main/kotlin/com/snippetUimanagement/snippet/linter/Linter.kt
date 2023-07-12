package com.snippetUimanagement.snippet.linter

import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.*

class Linter {
    companion object {
        fun lint(token:String,snippetId: UUID, url: String): Any?{
            try {
                val snippetRole = SnippetManageRepositories.canAccessASnippet(snippetId, token, url)
                if(snippetRole != null){
                    return SnippetManagmentServiceSnippet.getLintedSnippet(snippetId, token, url)
                }
                return null
            }catch (e: Throwable){
                return  ErrorResponse(e.message ?: "An error occurred")
            }

        }
    }
}