package com.snippetUimanagement.snippet.getASnippet

import com.snippetUimanagement.entities.Snippet
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetASnippet {
    fun getASnippet(userUuid: String, snippetUuid: String){
        val snippetRole =  SnippetManageRepositories.canAccessASnippet(snippetUuid, userUuid)
        if(snippetRole != null){
            val snippetById = SnippetManagmentServiceSnippet.getSnippet(snippetUuid)
            val snippet = Snippet(snippetById.snippetId, snippetById.snippetCode, snippetRole.role, snippetById.staticCodeCorrect )
        }
    }
}