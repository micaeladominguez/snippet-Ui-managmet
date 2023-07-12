package com.snippetUimanagement.snippet.getAllSnippets

import com.snippetUimanagement.classes.CompleteSnippet
import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetAllSnippets {
    companion object {
        fun getAllSnippets(token: String, url: String): Any{
            return try {
                val snippetsWithRole = SnippetManageRepositories.getSnippetsThatCanSee(token, url)
                val snippetIds = snippetsWithRole.map { it.id }
                val snippetsByIds = SnippetManagmentServiceSnippet.getAllThisSnippets(token,snippetIds, url)
                snippetsByIds.mapNotNull { snippetById ->
                    val snippetRole = snippetsWithRole.firstOrNull { it.id == snippetById.id }
                    snippetRole?.let { CompleteSnippet(snippetById.id, snippetById.code, snippetById.name,  it.role, snippetById.staticCodeCorrect, snippetById.linesErrors) }
                }
            }catch (e: Throwable){
                ErrorResponse(e.message ?: "An error occurred")
            }
        }
    }

}