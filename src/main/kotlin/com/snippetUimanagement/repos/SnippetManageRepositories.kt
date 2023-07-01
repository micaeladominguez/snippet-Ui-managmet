package com.snippetUimanagement.repos

import com.snippetUimanagement.entities.Role
import com.snippetUimanagement.entities.SnippetRole

class SnippetManageRepositories {
    companion object {
        fun saveSnippetByRole(snippetUuid: String, userUuid: String) {

        }

        fun getSnippetsThatCanSee(userUuid: String) : List<SnippetRole>{
            val snippets = mutableListOf<SnippetRole>()
            return snippets
        }


        fun canAccessASnippet(snippetUuid: String, userUuid: String): SnippetRole? {
            return SnippetRole("snippetId", Role.WRITE)
        }

        fun givePermissions(snippetUuid: String, userUuid: String): SnippetRole? {
            return SnippetRole("snippetId", Role.READ)
        }
    }
}