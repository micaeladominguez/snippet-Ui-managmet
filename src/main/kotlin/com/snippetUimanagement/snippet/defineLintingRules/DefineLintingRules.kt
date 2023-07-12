package com.snippetUimanagement.snippet.defineLintingRules

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.dto.UpdateRules
import com.snippetUimanagement.repos.dto.UpdateRulesAndSnippets

class DefineLintingRules {
    companion object {
        fun lintingRules(token: String, updatedRules : UpdateRules, url: String) : Any {
            return try {
                val snippetsWithRole = SnippetManageRepositories.getSnippetsThatCanSee(token, url)
                val snippetIds = snippetsWithRole.map { it.id }
                val rules = UpdateRulesAndSnippets(updatedRules.rules, snippetIds)
                SnippetManagmentServiceSnippet.updateLintingRules(token, rules, url)
            }catch (e: Throwable){
                e
            }
        }
    }
}