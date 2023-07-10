package com.snippetUimanagement.snippet.updateSnippet

import com.snippetUimanagement.classes.SnippetUpdateWithTests
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.SnippetTestingScripts
import java.util.UUID

class UpdateSnippet {
    companion object {
        fun updateSnippetCode(snippetId: UUID, code: String, token:String) : SnippetUpdateWithTests?{
            val canUpdate = SnippetManageRepositories.checkIfICanUpdate(token, snippetId)
            if(canUpdate){
                val snippet = SnippetManagmentServiceSnippet.updateSnippet(snippetId, code, token)
                val interpretedSnippet = SnippetManagmentServiceSnippet.getRunnedSnippet(snippetId, token)
                var result = ""
                if(interpretedSnippet.isNotEmpty() && interpretedSnippet[0] == "No messages")
                    result = ""
                else
                    result = interpretedSnippet.joinToString("\n")
                val testResults = SnippetTestingScripts.runTestsBySnippetUuid(token,snippetId, result)
                return SnippetUpdateWithTests(snippet, testResults.toList())
            }else{
                return null
            }

        }
    }


}