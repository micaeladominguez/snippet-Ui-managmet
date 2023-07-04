package com.snippetUimanagement.repos.dto

import java.util.UUID
import kotlin.contracts.contract

data class CreateTestCaseDto(
    val input: String,
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
