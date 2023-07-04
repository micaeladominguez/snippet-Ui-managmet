package com.snippetUimanagement.repos.dto

import java.util.UUID

data class RuleUpdated(
    val ruleId: UUID,
    val value: String
)
data class UpdateRules(
    val rules: List<RuleUpdated>
)