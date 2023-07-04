package com.snippetUimanagement.snippet.shareASnippet

import com.snippetUimanagement.classes.SnippetRole
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.dto.SnippetRoleUpdateDTO

class ShareASnippet {
    companion object {
        fun shareASnippet(token: String, snippetRoleUpdateDTO: SnippetRoleUpdateDTO): SnippetRole {
            return SnippetManageRepositories.givePermissions(snippetRoleUpdateDTO, token)
        }
    }

}