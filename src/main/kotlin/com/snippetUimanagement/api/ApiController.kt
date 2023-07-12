package com.snippetUimanagement.api

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
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import java.util.*

@RestController
@RequestMapping("/api")
class ApiController {

    @PostMapping("/snippets/create")
    fun postASnippet(@RequestHeader authorization : String, @RequestBody body : SnippetCreateDTO): ResponseEntity<String> {
        return try {
            saveSnippet(body, authorization)
            ResponseEntity("Created ", HttpStatus.CREATED)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }

    }

    @PostMapping("/test/create")
    fun postATest(@RequestHeader authorization : String,@RequestParam snippetId: UUID,  @RequestBody body : CreateTestCaseDto): ResponseEntity<Any> {
        return try {
            val response = addTest(authorization, snippetId, body)
            ResponseEntity(response, HttpStatus.CREATED)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }

    }

    @GetMapping("/snippets/runAllTests")
    fun getAllSnippetsGet(@RequestHeader authorization : String, @RequestParam snippetId: UUID): ResponseEntity<out Any> {
        return try {
            val response = runAllTests(authorization, snippetId)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }

    }

    @PutMapping("/test/update")
    fun updateTestPut(@RequestHeader authorization : String, @RequestParam snippetId: UUID): ResponseEntity<out Any> {
        return try {
            val response = runAllTests(authorization, snippetId)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }

    }

    @GetMapping("/snippets")
    fun getAllSnippetsGet(@RequestHeader authorization : String): ResponseEntity<out Any> {
        return try {
            val response = getAllSnippets(authorization)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }

    }


    @PutMapping("/share")
    fun shareASnippetPut(@RequestHeader authorization : String,  @RequestBody body : SnippetRoleUpdateDTO): ResponseEntity<out Any> {
        return try {
            val response = shareASnippet(authorization, body)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/update")
    fun updateASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, @RequestBody code : String): ResponseEntity<out Any> {
        return try {
            val response = updateSnippetCode(snippetId, code, authorization)
                ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/format")
    fun formatASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID): ResponseEntity<out Any> {
        return try {
            val response = format(authorization,snippetId) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/interpreter")
    fun runASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID): ResponseEntity<out Any> {
        return try {
            val response = interpreter(authorization,snippetId) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/lint")
    fun lintASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID): ResponseEntity<out Any> {
        return try {
            val response = lint(authorization,snippetId) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/snippet")
    fun getASnippetGet(@RequestHeader authorization : String, @RequestParam snippetId: UUID): ResponseEntity<out Any> {
        return try {
            val response = getASnippet(authorization,snippetId) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/rules/formatting")
    fun getFormattedRulesGet(@RequestHeader authorization : String): ResponseEntity<out Any> {
        return try {
            val response = getFormattedRules(authorization)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/rules/linting")
    fun getLintingRulesGet(@RequestHeader authorization : String): ResponseEntity<out Any> {
        return try {
            val response = getLintingRules(authorization)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/rules/formatting")
    fun updateFormattedRulesGet(@RequestHeader authorization : String, @RequestBody rules: UpdateRules): ResponseEntity<out Any> {
        return try {
            val response = formatRules(authorization, rules)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }

    @PutMapping("/rules/linting")
    fun updateLintingRulesGet(@RequestHeader authorization : String, @RequestBody rules: UpdateRules): ResponseEntity<out Any> {
        return try {
            val response = lintingRules(authorization, rules)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Error){
            ResponseEntity(e.message, HttpStatus.BAD_REQUEST)
        }
    }


}