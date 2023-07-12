package com.snippetUimanagement.snippet.format

import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.UUID

class Format {
    companion object {
        fun format(token: String, snippetId: UUID, url: String): Any? {
            try {
                val canUpdate = SnippetManageRepositories.checkIfICanUpdate(token, snippetId, url)
                if (canUpdate) {
                    return SnippetManagmentServiceSnippet.getFormattedSnippet(snippetId, token, url)
                }
                return null
            } catch (e: Throwable) {
                return  ErrorResponse(e.message ?: "An error occurred")
            }
        }
    }

}