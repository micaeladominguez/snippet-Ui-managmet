package com.snippetUimanagement.repos

import com.snippetUimanagement.classes.FormatRules
import com.snippetUimanagement.classes.LintingRules
import com.snippetUimanagement.classes.SnippetById
import com.snippetUimanagement.classes.SnippetInterpreterResult

class SnippetManagmentServiceSnippet {
    companion object {
        fun sendSnippet(snippetName: String, snippetType: String, snippetCode: String, snippetText: String): String {
            return "SnippetUUID"
        }

        fun updateSnippet(snippetUuid: String, snippetCode: String) {
        }

        fun getAllThisSnippets(snippetsId: List<String>) : List<SnippetById> {
            return mutableListOf()
        }

        fun getSnippet(snippetUuid: String): SnippetById {
            return SnippetById("", "snippetId", true)
        }

        fun getResult(snippetUuid: String) : SnippetInterpreterResult {
            return SnippetInterpreterResult("snippetId", "")
        }

        fun getFormattedSnippet(snippetUuid: String) : SnippetById {
            return SnippetById("", "snippetId", true)
        }


        fun getAllFormatRules(userUuid: String): List<FormatRules> {
            return mutableListOf()
        }

        fun getAllLintingRules(userUuid: String): List<LintingRules> {
            return mutableListOf()
        }


        fun updateFormatRules(userUuid: String, formatRules: List<FormatRules>): List<FormatRules> {
            return mutableListOf()
        }

        fun updateLintingRules(userUuid: String, lintingRules: List<LintingRules>): List<LintingRules> {
            return mutableListOf()
        }
    }


}