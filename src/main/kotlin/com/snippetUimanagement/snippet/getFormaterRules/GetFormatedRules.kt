package com.snippetUimanagement.snippet.getFormaterRules

import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetFormattedRules {
    companion object {
        fun getFormattedRules(token: String)  : Any {
            return try {
                SnippetManagmentServiceSnippet.getAllFormatRules(token)
            }catch (e: Throwable){
                e
            }
        }
    }


}