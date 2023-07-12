package com.snippetUimanagement.snippet.getASnippet

import com.snippetUimanagement.classes.CompleteSnippet
import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.*

class GetASnippet {
    companion object {
        fun getASnippet(token: String, snippetUuid: UUID, url: String): Any?{
            try {
                val snippetRole =  SnippetManageRepositories.canAccessASnippet(snippetUuid, token, url)
                if(snippetRole != null){
                    val snippetById = SnippetManagmentServiceSnippet.getSnippet(snippetUuid, token, url)
                    return CompleteSnippet(snippetById.id, snippetById.code, snippetById.name,snippetRole.role, snippetById.staticCodeCorrect, snippetById.linesErrors )
                }
                return null
            }catch (e: Throwable){
                return  ErrorResponse(e.message ?: "An error occurred")
            }

        }
    }

}