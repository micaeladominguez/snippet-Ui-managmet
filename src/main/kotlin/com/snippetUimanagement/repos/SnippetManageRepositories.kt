package com.snippetUimanagement.repos

import com.fasterxml.jackson.databind.ObjectMapper
import com.snippetUimanagement.classes.Role
import com.snippetUimanagement.classes.SnippetInfo
import com.snippetUimanagement.classes.SnippetRole
import com.snippetUimanagement.repos.Requests.Companion.getManageRepositories
import com.snippetUimanagement.repos.Requests.Companion.postManageRepositories
import com.snippetUimanagement.repos.Requests.Companion.putManageRepositories
import com.snippetUimanagement.repos.dto.SnippetRoleCreationDTO
import com.snippetUimanagement.repos.dto.SnippetRoleUpdateDTO
import java.util.UUID

class SnippetManageRepositories {
    companion object {
        fun saveSnippetByRole(snippetUuid: UUID, token: String, url: String): String? {
            return postManageRepositories(token,"/authorization", SnippetRoleCreationDTO(snippetUuid),url)
        }

        fun getSnippetsThatCanSee(token: String, url: String) : List<SnippetRole>{
            val response =  getManageRepositories(token, "/authorization/all", url)
            val mapper = ObjectMapper()
            val snippetRole: Array<SnippetInfo> = mapper.readValue(response, Array<SnippetInfo>::class.java)
            val snippets = mutableListOf<SnippetRole>()
            for( snippet in snippetRole){
                snippets.add(SnippetRole(snippet.snippetUuid, getRoleByName(snippet.role.name)))
            }
            return snippets
        }

        private fun getRoleByName(name: String): Role? {
            return when (name) {
                "OWNER" -> Role.WRITE
                "SHARED" -> Role.READ
                else -> null
            }
        }

        fun canAccessASnippet(snippetId: UUID, token: String, url: String): SnippetRole? {
            val response = getManageRepositories(token, "/authorization/access?snippetId=$snippetId", url) ?: return null
            val mapper = ObjectMapper()
            val snippetRole: SnippetInfo = mapper.readValue(response, SnippetInfo::class.java)
            return SnippetRole(snippetRole.snippetUuid, getRoleByName(snippetRole.role.name))
        }

        fun givePermissions(snippetRoleUpdateDTO: SnippetRoleUpdateDTO, token: String, url: String): SnippetRole {
            val mapper = ObjectMapper()
            val response =  putManageRepositories(token, "/authorization", snippetRoleUpdateDTO, url)
            val snippetRole: SnippetInfo = mapper.readValue(response, SnippetInfo::class.java)
            return SnippetRole(snippetRole.snippetUuid, getRoleByName(snippetRole.role.name))
        }

        fun checkIfICanUpdate(token: String, snippetId: UUID, url: String): Boolean {
            val response = getManageRepositories(token, "/authorization/update?snippetId=$snippetId", url)
            return response == "true"
        }
    }
}