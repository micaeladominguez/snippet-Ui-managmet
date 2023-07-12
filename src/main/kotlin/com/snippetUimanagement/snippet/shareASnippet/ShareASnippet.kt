package com.snippetUimanagement.snippet.shareASnippet

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.dto.SnippetRoleUpdateDTO

class ShareASnippet {
    companion object {
        fun shareASnippet(token: String, snippetRoleUpdateDTO: SnippetRoleUpdateDTO, url: String): Any {
            return try {
                SnippetManageRepositories.givePermissions(snippetRoleUpdateDTO, token, url)
            }catch (e: Throwable){
                e
            }
        }
    }

}