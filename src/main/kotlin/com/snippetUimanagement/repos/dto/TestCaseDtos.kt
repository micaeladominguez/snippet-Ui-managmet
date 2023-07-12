package com.snippetUimanagement.repos.dto

import java.util.UUID

data class CreateTestCaseDto(
    val output: String
)

data class TestResultDto(
    val expectedOutput: String,
    val realOutput: String,
    val hasError: Boolean,
    val testId: UUID,
){
    constructor(): this("","", true, UUID.randomUUID())
}
