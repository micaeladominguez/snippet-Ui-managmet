package com.snippetUimanagement.snippet.getFormaterRules

import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetFormattedRules {
    companion object {
        fun getFormattedRules(token: String, url: String)  : Any {
            return try {
                SnippetManagmentServiceSnippet.getAllFormatRules(token, url)
            }catch (e: Throwable){
                e
            }
        }
    }


}