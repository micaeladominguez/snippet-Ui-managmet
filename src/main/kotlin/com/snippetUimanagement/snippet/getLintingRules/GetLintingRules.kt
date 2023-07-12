package com.snippetUimanagement.snippet.getLintingRules

import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet

class GetLintingRules {
    companion object {
        fun getLintingRules(userUuid: String): Any {
            return try {
                SnippetManagmentServiceSnippet.getAllLintingRules(userUuid)

            }catch(e: Throwable){
                e
            }
        }
    }
}
