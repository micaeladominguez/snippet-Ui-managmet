package com.snippetUimanagement.testController

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/test")
class TestController {

    @GetMapping
    fun getTestJson(): String{
        return "Correct connection"
    }
}