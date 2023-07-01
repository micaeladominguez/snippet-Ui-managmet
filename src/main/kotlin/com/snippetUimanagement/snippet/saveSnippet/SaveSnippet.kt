package com.snippetUimanagement.snippet.saveSnippet

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class SaveSnippet {

    fun saveSnippet( snippetName: String, snippetType: String, snippetCode: String, snippetText: String, userUuid: String) {
        //llama al snippet repo
        val snippetUuid = SnippetManagmentServiceSnippet.sendSnippet(snippetName, snippetType, snippetCode, snippetText)
        //llama al manage repo
        val snippetRoleSaved = SnippetManageRepositories.saveSnippetByRole(snippetUuid, userUuid)
    }


}