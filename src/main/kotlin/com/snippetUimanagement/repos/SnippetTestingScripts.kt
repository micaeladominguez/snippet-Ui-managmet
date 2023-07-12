package com.snippetUimanagement.repos

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.snippetUimanagement.classes.TestCase
import com.snippetUimanagement.repos.Requests.Companion.postTestRepositories
import com.snippetUimanagement.repos.dto.CreateTestCaseDto
import com.snippetUimanagement.repos.dto.TestResultDto
import java.util.*


class SnippetTestingScripts {
    companion object {
        fun runTestsBySnippetUuid(token: String, snippetUuid: UUID, url : String) : Array<TestResultDto> {
            val response = postTestRepositories(token, "/testing/runAll?snippetId=$snippetUuid", "", url)
            println(response)
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, Array<TestResultDto> ::class.java)
        }

        fun runTestsBySnippetUuid(token: String,snippetUuid: UUID, result: String, url: String)  : Array<TestResultDto> {
            val response = postTestRepositories(token, "/testing/runAllUpdate?snippetId=$snippetUuid&testResult=$result", "", url)
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, Array<TestResultDto> ::class.java)
        }


        fun addTest(token: String, snippetUuid: UUID, createTestDTO: CreateTestCaseDto, url: String): TestCase{
            val response = postTestRepositories(token, "/testing?snippetId=$snippetUuid", createTestDTO, url)
            val objectMapper = ObjectMapper()
            objectMapper.registerModule(JavaTimeModule())
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, TestCase ::class.java)
        }

    }
}