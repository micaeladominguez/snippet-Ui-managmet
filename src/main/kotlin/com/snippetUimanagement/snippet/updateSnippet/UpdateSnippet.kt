package com.snippetUimanagement.snippet.updateSnippet

import com.snippetUimanagement.classes.SnippetUpdateWithTests
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.SnippetTestingScripts
import java.util.UUID

class UpdateSnippet {
    companion object {
        fun updateSnippetCode(snippetId: UUID, code: String, token:String) : Any?{
            try {
                val canUpdate = SnippetManageRepositories.checkIfICanUpdate(token, snippetId)
                return if(canUpdate){
                    val snippet = SnippetManagmentServiceSnippet.updateSnippet(snippetId, code, token)
                    val interpretedSnippet = SnippetManagmentServiceSnippet.getRunnedSnippet(snippetId, token)
                    val result = if(interpretedSnippet.isNotEmpty() && interpretedSnippet[0] == "No messages")
                        ""
                    else
                        interpretedSnippet.joinToString("\n")
                    val testResults = SnippetTestingScripts.runTestsBySnippetUuid(token,snippetId, result)
                    SnippetUpdateWithTests(snippet, testResults.toList())
                }else{
                    null
                }
            }catch (e: Throwable){
                return e
            }
        }
    }


}