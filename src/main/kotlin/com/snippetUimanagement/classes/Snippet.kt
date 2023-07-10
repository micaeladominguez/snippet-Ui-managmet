package com.snippetUimanagement.classes

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.databind.annotation.JsonDeserialize
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers
import java.util.UUID

data class Snippet(
    val id: UUID,
    val name: String,
    val type: String,
    val code: String,
    val linesFailed: String
){
    constructor(): this(UUID.randomUUID(),"","","", "")
}

data class AnalyzeData(
    @JsonDeserialize(using = NumberDeserializers.BooleanDeserializer::class)
    val isValid: Boolean ,
    val linesErrors: String
){
    constructor(): this(true, "")
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class AnalyzedSnippet(
    val snippet: Snippet,
    val data: AnalyzeData
){
    constructor(): this(Snippet(), AnalyzeData())
}
