package com.snippetUimanagement.snippet.interpreter

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.*

class Interpreter {
    companion object {
        fun interpreter(token:String,snippetId: UUID): String?{
            val snippetRole = SnippetManageRepositories.canAccessASnippet(snippetId, token)
            if(snippetRole != null){
                return SnippetManagmentServiceSnippet.getRunnedSnippet(snippetId, token).joinToString("\n")
            }
            return null
        }
    }
}