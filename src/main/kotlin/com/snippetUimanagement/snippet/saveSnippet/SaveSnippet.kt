package com.snippetUimanagement.snippet.saveSnippet

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.dto.SnippetCreateDTO

class SaveSnippet {
    companion object {
        fun saveSnippet(snippetCreateDTO: SnippetCreateDTO, token: String, url: String): Any {
            return try {
                val snippet = SnippetManagmentServiceSnippet.saveSnippet(snippetCreateDTO, token, url)
                SnippetManageRepositories.saveSnippetByRole(snippet.id, token, url)
                snippet
            }catch(e: Throwable){
                e
            }
        }
    }



}