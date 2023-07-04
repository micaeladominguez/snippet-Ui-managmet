package com.snippetUimanagement.snippet.getASnippet

import com.snippetUimanagement.classes.CompleteSnippet
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.*

class GetASnippet {
    companion object {
        fun getASnippet(token: String, snippetUuid: UUID): CompleteSnippet?{
            val snippetRole =  SnippetManageRepositories.canAccessASnippet(snippetUuid, token)
            if(snippetRole != null){
                val snippetById = SnippetManagmentServiceSnippet.getSnippet(snippetUuid, token)
                return CompleteSnippet(snippetById.id, snippetById.code, snippetRole.role, snippetById.staticCodeCorrect, snippetById.linesErrors )
            }
            return null
        }
    }

}