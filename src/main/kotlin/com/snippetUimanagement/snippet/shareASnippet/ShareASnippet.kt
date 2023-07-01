package com.snippetUimanagement.snippet.shareASnippet

import com.snippetUimanagement.repos.SnippetManageRepositories

class ShareASnippet {
    fun shareASnippet(snippetId: String, userId: String) {
        SnippetManageRepositories.givePermissions(snippetId, userId)
    }
}