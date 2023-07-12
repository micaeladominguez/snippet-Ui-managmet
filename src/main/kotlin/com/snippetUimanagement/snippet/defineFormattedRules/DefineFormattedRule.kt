package com.snippetUimanagement.snippet.defineFormattedRules

import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.dto.UpdateRules

class DefineFormattedRule {
    companion object {
        fun formatRules(token: String, updatedRules : UpdateRules, url: String) : Any{
            return try {
                SnippetManagmentServiceSnippet.updateFormatRules(token, updatedRules, url)
            }catch(e: Throwable){
                ErrorResponse(e.message ?: "An error occurred")
            }
        }
    }

}