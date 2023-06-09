package com.snippetUimanagement.repos

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.snippetUimanagement.classes.*
import com.snippetUimanagement.repos.Requests.Companion.getSnippetRepositories
import com.snippetUimanagement.repos.Requests.Companion.postSnippetRepositories
import com.snippetUimanagement.repos.Requests.Companion.putSnippetRepositories
import com.snippetUimanagement.repos.dto.SnippetCreateDTO
import com.snippetUimanagement.repos.dto.UpdateRules
import com.snippetUimanagement.repos.dto.UpdateRulesAndSnippets
import com.snippetUimanagement.repos.dto.UpdateSnippetDTO
import java.util.UUID
import kotlin.collections.ArrayList

class SnippetManagmentServiceSnippet {
    companion object {
        fun saveSnippet(snippetCreateDTO: SnippetCreateDTO, token: String, url: String): Snippet {
            val response = postSnippetRepositories(token, "/snippets/create", snippetCreateDTO, url)
            val objectMapper = ObjectMapper()
            return objectMapper.readValue(response, Snippet::class.java)
        }

        fun updateSnippet(snippetId: UUID, code: String, token: String, url: String): Snippet {
            val response = putSnippetRepositories(token, "/snippets/update/snippet?uuid=$snippetId",code, url)
            val objectMapper = ObjectMapper()
            return objectMapper.readValue(response, Snippet::class.java)
        }

        fun getAllThisSnippets(token: String, snippetsId: List<UUID>, url: String) : List<SnippetById> {
            val snippetsUuids = snippetsId.joinToString(",")
            println("UUIDS " + snippetsUuids)
            val response = getSnippetRepositories(token, "/snippets?uuids=$snippetsUuids", url)
            val mapper = ObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            val snippetRole: Array<AnalyzedSnippet> = mapper.readValue(response, Array<AnalyzedSnippet>::class.java)
            val snippetById = mutableListOf<SnippetById>()
            for(snippet in snippetRole){
                snippetById.add(SnippetById(snippet.snippet.code, snippet.snippet.name, snippet.snippet.id, snippet.data.isValid, snippet.data.linesErrors))
            }
            return snippetById
        }

        fun getSnippet(snippetId: UUID, token: String, url: String): SnippetById {
            val response = getSnippetRepositories(token, "/snippets?uuids=$snippetId", url)
            val mapper = ObjectMapper()
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            val snippetRole: Array<AnalyzedSnippet> = mapper.readValue(response, Array<AnalyzedSnippet>::class.java)
            val snippetById = mutableListOf<SnippetById>()
            for(snippet in snippetRole){
                snippetById.add(SnippetById(snippet.snippet.code, snippet.snippet.name,snippet.snippet.id, snippet.data.isValid, snippet.data.linesErrors))
            }
            return snippetById[0]
        }


        fun getFormattedSnippet(snippetId: UUID, token: String, url: String) : Snippet {
            val response = putSnippetRepositories(token, "/snippets/format/snippet?uuid=$snippetId","", url)
            val objectMapper = ObjectMapper()
            return objectMapper.readValue(response, Snippet::class.java)
        }

        fun getLintedSnippet(snippetId: UUID, token: String, url: String) : AnalyzeData {
            val response = putSnippetRepositories(token, "/snippets/validate?uuid=$snippetId","", url)
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, AnalyzeData::class.java)
        }

        fun getRunnedSnippet(snippetId: UUID, token: String, url: String) : Array<String> {
            val response = putSnippetRepositories(token, "/snippets/run?uuid=$snippetId","", url)
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, Array<String>::class.java)

        }



        fun getAllFormatRules(token: String, url: String): FormatInfoRule {
            val response =  getSnippetRepositories(token, "/user/rules/formatted", url)
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, FormatInfoRule::class.java)
        }

        fun getAllLintingRules(token: String, url: String): FormatInfoRule {
            val response =  getSnippetRepositories(token, "/user/rules/linted", url)
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, FormatInfoRule::class.java)
        }


        fun updateFormatRules(token: String, updateRules: UpdateRules, url: String): FormatInfoRule {
            val response =  putSnippetRepositories(token, "/user/rules/formatted", updateRules, url)
            return castRules(response)
        }

        fun updateLintingRules(token: String, updateRules: UpdateRulesAndSnippets, url: String): FormatInfoRule {
            val response =  putSnippetRepositories(token, "/user/rules/linted", updateRules, url)
            return castRules(response)
        }


        private fun castRules(response: String?): FormatInfoRule {
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            val userRules : Array<UserRule>  = objectMapper.readValue(response,  Array<UserRule>::class.java)
            if(userRules.isNotEmpty()){
                val userId = userRules[0].userId
                val rulesValued = ArrayList<RuleValued>()
                for(rule in userRules){
                    rulesValued.add(RuleValued(rule.rule.id, rule.rule.name, rule.value))
                }
                return FormatInfoRule(userId, rulesValued)
            }
            return FormatInfoRule()
        }
    }


}