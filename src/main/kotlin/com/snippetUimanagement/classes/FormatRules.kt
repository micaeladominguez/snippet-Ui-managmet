package com.snippetUimanagement.classes

import org.apache.tomcat.util.digester.Rule
import java.util.UUID

data class FormatRules(
    val rule: String,
    val hasIt: Boolean
)

data class RuleFromUser(
    val id: UUID,
    val name: String,
    val typeOfRule: String
){
    constructor(): this(UUID.randomUUID(), "", "")
}
data class UserRule(
    val id: UUID,
    val userId: String,
    val rule: RuleFromUser,
    val value: String
){
    constructor(): this(UUID.randomUUID(), "", RuleFromUser(), "")
}

data class RuleValued(
    val ruleId: UUID,
    val ruleName: String,
    val value: String
){
    constructor(): this(UUID.randomUUID(), "", "")
}

data class FormatInfoRule(
    val userId: String,
    val rulesValued : ArrayList<RuleValued>
){
    constructor(): this("", ArrayList(emptyList<RuleValued>()))
}