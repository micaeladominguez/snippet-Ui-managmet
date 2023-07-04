package com.snippetUimanagement.classes

import java.time.LocalDateTime
import java.util.*

data class TestCase(

    val id: UUID,

    var snippetId: UUID,

    var input: String,

    var output: String,

    val createdAt: LocalDateTime ,

    var updatedAt: LocalDateTime?,

    var deletedAt: LocalDateTime?,
){
    constructor(): this(UUID.randomUUID(), UUID.randomUUID(), "", "", LocalDateTime.MAX,null,null)
}