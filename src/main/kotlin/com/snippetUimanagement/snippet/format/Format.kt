package com.snippetUimanagement.snippet.format

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import java.util.UUID

class Format {
    companion object {
        fun format(token: String, snippetId: UUID): Any? {
            try {
                val canUpdate = SnippetManageRepositories.checkIfICanUpdate(token, snippetId)
                if (canUpdate) {
                    return SnippetManagmentServiceSnippet.getFormattedSnippet(snippetId, token)
                }
                return null
            } catch (e: Throwable) {
                return e
            }
        }
    }

}