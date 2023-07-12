package com.snippetUimanagement.snippet.interpreter

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.*

class Interpreter {
    companion object {
        fun interpreter(token:String,snippetId: UUID, url: String): Any?{
            try {
                val snippetRole = SnippetManageRepositories.canAccessASnippet(snippetId, token, url)
                if(snippetRole != null){
                    return SnippetManagmentServiceSnippet.getRunnedSnippet(snippetId, token, url).joinToString("\n")
                }
                return null
            }catch (e: Throwable){
                return e
            }
        }
    }
}