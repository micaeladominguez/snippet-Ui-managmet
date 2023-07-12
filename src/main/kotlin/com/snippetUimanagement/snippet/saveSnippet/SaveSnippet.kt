package com.snippetUimanagement.snippet.saveSnippet

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.dto.SnippetCreateDTO

class SaveSnippet {
    companion object {
        fun saveSnippet(snippetCreateDTO: SnippetCreateDTO, token: String): Any {
            return try {
                val snippet = SnippetManagmentServiceSnippet.saveSnippet(snippetCreateDTO, token)
                SnippetManageRepositories.saveSnippetByRole(snippet.id, token)
                snippet
            }catch(e: Throwable){
                e
            }
        }
    }



}