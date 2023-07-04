package com.snippetUimanagement.repos

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.snippetUimanagement.classes.*
import com.snippetUimanagement.repos.Requests.Companion.getSnippetRepositories
import com.snippetUimanagement.repos.Requests.Companion.postSnippetRepositories
import com.snippetUimanagement.repos.Requests.Companion.putSnippetRepositories
import com.snippetUimanagement.repos.dto.SnippetCreateDTO
import java.util.UUID

class SnippetManagmentServiceSnippet {
    companion object {
        fun saveSnippet(snippetCreateDTO: SnippetCreateDTO, token: String): Snippet {
            val response = postSnippetRepositories(token, "/snippets/create", snippetCreateDTO)
            val objectMapper = ObjectMapper()
            return objectMapper.readValue(response, Snippet::class.java)
        }

        fun updateSnippet(snippetId: UUID, code: String, token: String): Snippet {
            val response = putSnippetRepositories(token, "snippets/update/snippet?uuid=$snippetId",code)
            val objectMapper = ObjectMapper()
            return objectMapper.readValue(response, Snippet::class.java)
        }

        fun getAllThisSnippets(token: String, snippetsId: List<UUID>) : List<SnippetById> {
            val snippetsUuids = snippetsId.joinToString(",")
            val response = getSnippetRepositories(token, "/snippets?uuids=$snippetsUuids")
            val mapper = ObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            val snippetRole: Array<AnalyzedSnippet> = mapper.readValue(response, Array<AnalyzedSnippet>::class.java)
            val snippetById = mutableListOf<SnippetById>()
            for(snippet in snippetRole){
                snippetById.add(SnippetById(snippet.snippet.code, snippet.snippet.id, snippet.data.isValid, snippet.data.linesErrors))
            }
            return snippetById
        }

        fun getSnippet(snippetId: UUID, token: String): SnippetById {
            val response = getSnippetRepositories(token, "/snippets?uuids=$snippetId")
            val mapper = ObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            val snippetRole: Array<AnalyzedSnippet> = mapper.readValue(response, Array<AnalyzedSnippet>::class.java)
            val snippetById = mutableListOf<SnippetById>()
            for(snippet in snippetRole){
                snippetById.add(SnippetById(snippet.snippet.code, snippet.snippet.id, snippet.data.isValid, snippet.data.linesErrors))
            }
            return snippetById[0]
        }

        fun getResult(snippetUuid: String) : SnippetInterpreterResult {
            return SnippetInterpreterResult("snippetId", "")
        }

        fun getFormattedSnippet(snippetId: UUID, token: String) : Snippet {
            val response = putSnippetRepositories(token, "snippets/format/snippet?uuid=$snippetId","")
            val objectMapper = ObjectMapper()
            return objectMapper.readValue(response, Snippet::class.java)
        }

        fun getLintedSnippet(snippetId: UUID, token: String) : AnalyzeData {
            val response = putSnippetRepositories(token, "snippets/validate?uuid=$snippetId","")
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, AnalyzeData::class.java)
        }

        fun getRunnedSnippet(snippetId: UUID, token: String) : String? {
            return putSnippetRepositories(token, "snippets/run?uuid=$snippetId","")
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