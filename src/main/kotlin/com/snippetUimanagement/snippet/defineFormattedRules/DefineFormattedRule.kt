package com.snippetUimanagement.snippet.defineFormattedRules

import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.dto.UpdateRules

class DefineFormattedRule {
    companion object {
        fun formatRules(token: String, updatedRules : UpdateRules) : Any{
            return try {
                SnippetManagmentServiceSnippet.updateFormatRules(token, updatedRules)
            }catch(e: Throwable){
                e
            }
        }
    }

}