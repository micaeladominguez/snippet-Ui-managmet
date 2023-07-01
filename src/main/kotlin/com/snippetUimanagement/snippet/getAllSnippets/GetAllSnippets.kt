package com.snippetUimanagement.snippet.getAllSnippets

import com.snippetUimanagement.entities.Snippet
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetAllSnippets {
    fun getAllSnippets(userUuid: String){
        val snippetsWithRole = SnippetManageRepositories.getSnippetsThatCanSee(userUuid)
        val snippetIds = snippetsWithRole.map { it.snippetId }
        val snippetsByIds = SnippetManagmentServiceSnippet.getAllThisSnippets(snippetIds)
        val snippets = snippetsByIds.mapNotNull { snippetById ->
            val snippetRole = snippetsWithRole.firstOrNull { it.snippetId == snippetById.snippetId }
            snippetRole?.let { Snippet(snippetById.snippetId, snippetById.snippetCode, it.role, true) }
        }
    }
}