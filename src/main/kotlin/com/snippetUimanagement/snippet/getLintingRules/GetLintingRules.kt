package com.snippetUimanagement.snippet.getLintingRules

import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetLintingRules {
    companion object {
        fun getLintingRules(userUuid: String, url: String): Any {
            return try {
                SnippetManagmentServiceSnippet.getAllLintingRules(userUuid, url)

            }catch(e: Throwable){
                e
            }
        }
    }
}
