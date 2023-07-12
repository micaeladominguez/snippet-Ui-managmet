package com.snippetUimanagement.api

import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.snippet.addTest.AddTest.Companion.addTest
import com.snippetUimanagement.repos.dto.CreateTestCaseDto
import com.snippetUimanagement.snippet.saveSnippet.SaveSnippet.Companion.saveSnippet
import com.snippetUimanagement.snippet.getAllSnippets.GetAllSnippets.Companion.getAllSnippets

import com.snippetUimanagement.repos.dto.SnippetCreateDTO
import com.snippetUimanagement.repos.dto.SnippetRoleUpdateDTO
import com.snippetUimanagement.repos.dto.UpdateRules
import com.snippetUimanagement.snippet.defineFormattedRules.DefineFormattedRule.Companion.formatRules
import com.snippetUimanagement.snippet.defineLintingRules.DefineLintingRules.Companion.lintingRules
import com.snippetUimanagement.snippet.format.Format.Companion.format
import com.snippetUimanagement.snippet.getASnippet.GetASnippet.Companion.getASnippet
import com.snippetUimanagement.snippet.getFormaterRules.GetFormattedRules.Companion.getFormattedRules
import com.snippetUimanagement.snippet.getLintingRules.GetLintingRules.Companion.getLintingRules
import com.snippetUimanagement.snippet.interpreter.Interpreter.Companion.interpreter
import com.snippetUimanagement.snippet.linter.Linter.Companion.lint
import com.snippetUimanagement.snippet.runAllTests.RunAllTests.Companion.runAllTests
import com.snippetUimanagement.snippet.shareASnippet.ShareASnippet.Companion.shareASnippet
import com.snippetUimanagement.snippet.updateSnippet.UpdateSnippet.Companion.updateSnippetCode
import jakarta.servlet.http.HttpServletRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import java.util.*

@RestController
@RequestMapping("/api")
class ApiController {

    private fun cutUrlBeforeBackend(url: String): String {
        val validDomains = listOf("https://snippetsps-dev.ddns.net/", "https://snippetsps.ddns.net/")
        val matchingDomain = validDomains.find { url.contains(it) }
        return matchingDomain ?: "https://snippetsps-dev.ddns.net/"
    }
    @PostMapping("/snippets/create")
    fun postASnippet(@RequestHeader authorization : String, @RequestBody body : SnippetCreateDTO, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val snippet = saveSnippet(body, authorization, url)
            return ResponseEntity(snippet, HttpStatus.CREATED)
        }catch(e: Exception){
            return ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }

    }

    @PostMapping("/test/create")
    fun postATest(@RequestHeader authorization : String,@RequestParam snippetId: UUID,  @RequestBody body : CreateTestCaseDto, request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = addTest(authorization, snippetId, body, url)
            ResponseEntity(response, HttpStatus.CREATED)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }

    }

    @GetMapping("/snippets/runAllTests")
    fun getAllSnippetsGet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = runAllTests(authorization, snippetId, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }

    }

    @PutMapping("/test/update")
    fun updateTestPut(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = runAllTests(authorization, snippetId, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }

    }

    @GetMapping("/snippets")
    fun getAllSnippetsGet(@RequestHeader authorization : String, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = getAllSnippets(authorization, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }

    }


    @PutMapping("/share")
    fun shareASnippetPut(@RequestHeader authorization : String,  @RequestBody body : SnippetRoleUpdateDTO, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = shareASnippet(authorization, body, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/update")
    fun updateASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, @RequestBody code : String, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = updateSnippetCode(snippetId, code, authorization, url)
                ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/format")
    fun formatASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = format(authorization,snippetId, url) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/interpreter")
    fun runASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = interpreter(authorization,snippetId, url) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/lint")
    fun lintASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = lint(authorization,snippetId, url) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/snippet")
    fun getASnippetGet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = getASnippet(authorization,snippetId, url) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/rules/formatting")
    fun getFormattedRulesGet(@RequestHeader authorization : String, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = getFormattedRules(authorization, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/rules/linting")
    fun getLintingRulesGet(@RequestHeader authorization : String, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = getLintingRules(authorization, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/rules/formatting")
    fun updateFormattedRulesGet(@RequestHeader authorization : String, @RequestBody rules: UpdateRules, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = formatRules(authorization, rules, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/rules/linting")
    fun updateLintingRulesGet(@RequestHeader authorization : String, @RequestBody rules: UpdateRules, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = lintingRules(authorization, rules, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Exception){
            ResponseEntity(ErrorResponse(e.message ?: "An error occurred"), HttpStatus.BAD_REQUEST)
        }
    }


}