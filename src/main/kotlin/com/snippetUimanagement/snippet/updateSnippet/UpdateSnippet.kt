package com.snippetUimanagement.snippet.updateSnippet

import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.classes.SnippetUpdateWithTests
import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.SnippetTestingScripts
import com.snippetUimanagement.repos.dto.UpdateSnippetDTO
import java.util.UUID

class UpdateSnippet {
    companion object {
        fun updateSnippetCode(snippetId: UUID, code: String, token:String, url: String) : Any?{
            try {
                val canUpdate = SnippetManageRepositories.checkIfICanUpdate(token, snippetId, url)
                return if(canUpdate){
                    val snippet = SnippetManagmentServiceSnippet.updateSnippet(snippetId, code, token, url)
                    val interpretedSnippet = SnippetManagmentServiceSnippet.getRunnedSnippet(snippetId, token, url)
                    val result = if(interpretedSnippet.isNotEmpty() && interpretedSnippet[0] == "No messages")
                        ""
                    else
                        interpretedSnippet.joinToString("\n")
                    val testResults = SnippetTestingScripts.runTestsBySnippetUuid(token,snippetId, result, url)
                    println(testResults)
                    SnippetUpdateWithTests(snippet, testResults.toList())
                }else{
                    null
                }
            }catch (e: Throwable){
                return  ErrorResponse(e.message ?: "An error occurred")
            }
        }
    }


}