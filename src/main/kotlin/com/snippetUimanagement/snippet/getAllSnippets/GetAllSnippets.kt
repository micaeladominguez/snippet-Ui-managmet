package com.snippetUimanagement.snippet.getAllSnippets

import com.snippetUimanagement.classes.CompleteSnippet
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetAllSnippets {
    companion object {
        fun getAllSnippets(token: String): List<CompleteSnippet>{
            val snippetsWithRole = SnippetManageRepositories.getSnippetsThatCanSee(token)
            val snippetIds = snippetsWithRole.map { it.id }
            val snippetsByIds = SnippetManagmentServiceSnippet.getAllThisSnippets(token,snippetIds)
            return snippetsByIds.mapNotNull { snippetById ->
                val snippetRole = snippetsWithRole.firstOrNull { it.id == snippetById.id }
                snippetRole?.let { CompleteSnippet(snippetById.id, snippetById.code, it.role, snippetById.staticCodeCorrect, snippetById.linesErrors) }
            }
        }
    }

}