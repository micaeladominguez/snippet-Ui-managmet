package com.snippetUimanagement.snippet.getFormaterRules

import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetFormattedRules {
    companion object {
        fun getFormattedRules(token: String, url: String)  : Any {
            return try {
                SnippetManagmentServiceSnippet.getAllFormatRules(token, url)
            }catch (e: Throwable){
                ErrorResponse(e.message ?: "An error occurred")
            }
        }
    }


}