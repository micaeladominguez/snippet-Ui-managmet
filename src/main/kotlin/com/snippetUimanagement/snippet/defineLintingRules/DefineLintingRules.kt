package com.snippetUimanagement.snippet.defineLintingRules

import com.snippetUimanagement.repos.SnippetManageRepositories
import com.snippetUimanagement.repos.SnippetManagmentServiceSnippet
import com.snippetUimanagement.repos.dto.UpdateRules
import com.snippetUimanagement.repos.dto.UpdateRulesAndSnippets

class DefineLintingRules {
    companion object {
        fun lintingRules(token: String, updatedRules : UpdateRules) : Any {
            return try {
                val snippetsWithRole = SnippetManageRepositories.getSnippetsThatCanSee(token)
                val snippetIds = snippetsWithRole.map { it.id }
                val rules = UpdateRulesAndSnippets(updatedRules.rules, snippetIds)
                SnippetManagmentServiceSnippet.updateLintingRules(token, rules)
            }catch (e: Throwable){
                e
            }
        }
    }
}