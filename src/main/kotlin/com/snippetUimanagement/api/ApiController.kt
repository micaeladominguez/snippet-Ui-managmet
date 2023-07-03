package com.snippetUimanagement.api

import com.snippetUimanagement.repos.Get
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class ApiController {

    @GetMapping
    fun getTest(){
        val get = Get()
        get.get()
    }
}