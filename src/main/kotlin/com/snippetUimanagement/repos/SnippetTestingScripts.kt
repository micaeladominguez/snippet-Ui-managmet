package com.snippetUimanagement.repos

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.ObjectMapper
import com.snippetUimanagement.classes.FormatInfoRule
import com.snippetUimanagement.classes.TestCase
import com.snippetUimanagement.repos.Requests.Companion.postTestRepositories
import com.snippetUimanagement.repos.dto.CreateTestCaseDto
import com.snippetUimanagement.repos.dto.TestResultDto
import java.util.UUID


class SnippetTestingScripts {
    companion object {
        fun runTestsBySnippetUuid(token: String, snippetUuid: UUID) : Array<TestResultDto> {
            val response = postTestRepositories(token, "/testing/runAll?snippetId=$snippetUuid", "")
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, Array<TestResultDto> ::class.java)
        }

        fun runTestsBySnippetUuid(token: String,snippetUuid: UUID, result: String)  : Array<TestResultDto> {
            val response = postTestRepositories(token, "/testing/runAllUpdate?snippetId=$snippetUuid&testResult=$result", "")
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, Array<TestResultDto> ::class.java)
        }


        fun addTest(token: String, snippetUuid: UUID, createTestDTO: CreateTestCaseDto): TestCase{
            val response = postTestRepositories(token, "/testing?snippetId=$snippetUuid", createTestDTO)
            val objectMapper = ObjectMapper()
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            return objectMapper.readValue(response, TestCase ::class.java)
        }

    }
}