package com.snippetUimanagement.snippet.saveSnippet

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.dto.SnippetCreateDTO

class SaveSnippet {
    companion object {
        fun saveSnippet(snippetCreateDTO: SnippetCreateDTO, token: String) {
            //llama al snippet repo
            val snippet = SnippetManagmentServiceSnippet.saveSnippet(snippetCreateDTO, token)
            //llama al manage repo
            val snippetRoleSaved = SnippetManageRepositories.saveSnippetByRole(snippet.id, token)
        }
    }



}