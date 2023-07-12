package com.snippetUimanagement.api

import com.snippetUimanagement.classes.ErrorResponse
import com.snippetUimanagement.classes.User
import com.snippetUimanagement.repos.dto.*
import com.snippetUimanagement.snippet.addTest.AddTest.Companion.addTest
import com.snippetUimanagement.snippet.saveSnippet.SaveSnippet.Companion.saveSnippet
import com.snippetUimanagement.snippet.getAllSnippets.GetAllSnippets.Companion.getAllSnippets

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
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import jakarta.servlet.http.HttpServletRequest
import kong.unirest.HttpResponse
import kong.unirest.JsonNode
import kong.unirest.Unirest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity
import java.util.*

val dotenv: Dotenv = dotenv()

@RestController
@RequestMapping("/api")
class ApiController {

    private fun cutUrlBeforeBackend(url: String): String {
        val validDomains = listOf("snippetsps-dev.ddns.net", "snippetsps.ddns.net")
        val matchingDomain = validDomains.find { url.contains(it) }

        return matchingDomain?.let { "https://$it/" } ?: "https://snippetsps-dev.ddns.net/"
    }

    @PostMapping("/snippets/create")
    fun postASnippet(@RequestHeader authorization : String, @RequestBody body : SnippetCreateDTO, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val snippet = saveSnippet(body, authorization, url)
            ResponseEntity(snippet, HttpStatus.CREATED)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/test/create")
    fun postATest(@RequestHeader authorization : String,@RequestParam snippetId: UUID,  @RequestBody body : CreateTestCaseDto, request: HttpServletRequest): ResponseEntity<Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = addTest(authorization, snippetId, body, url)
            ResponseEntity(response, HttpStatus.CREATED)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }

    }

    @GetMapping("/snippets/runAllTests")
    fun getAllSnippetsGet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = runAllTests(authorization, snippetId, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }

    }

    @PatchMapping("/test/update")
    fun updateTestPut(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = runAllTests(authorization, snippetId, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }

    }

    @GetMapping("/snippets")
    fun getAllSnippetsGet(@RequestHeader authorization : String, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = getAllSnippets(authorization, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }

    }

    @PostMapping("/share")
    fun shareASnippetPut(@RequestHeader authorization : String,  @RequestBody body : SnippetRoleUpdateDTO, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = shareASnippet(authorization, body, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/update")
    fun updateASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, @RequestBody code : String, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = updateSnippetCode(snippetId, code, authorization, url)
                ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/format")
    fun formatASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = format(authorization,snippetId, url) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/interpreter")
    fun runASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = interpreter(authorization,snippetId, url) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/lint")
    fun lintASnippet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = lint(authorization,snippetId, url) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/snippet")
    fun getASnippetGet(@RequestHeader authorization : String, @RequestParam snippetId: UUID, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = getASnippet(authorization,snippetId, url) ?: return ResponseEntity("No permissions", HttpStatus.BAD_REQUEST)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/rules/formatting")
    fun getFormattedRulesGet(@RequestHeader authorization : String, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = getFormattedRules(authorization, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }

    @GetMapping("/rules/linting")
    fun getLintingRulesGet(@RequestHeader authorization : String, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = getLintingRules(authorization, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/rules/formatting")
    fun updateFormattedRulesGet(@RequestHeader authorization : String, @RequestBody rules: UpdateRules, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = formatRules(authorization, rules, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }

    @PatchMapping("/rules/linting")
    fun updateLintingRulesGet(@RequestHeader authorization : String, @RequestBody rules: UpdateRules, request: HttpServletRequest): ResponseEntity<out Any> {
        return try {
            val url = cutUrlBeforeBackend(request.requestURL.toString())
            val response = lintingRules(authorization, rules, url)
            ResponseEntity(response, HttpStatus.OK)
        }catch(e: Throwable){
            ResponseEntity(e.message?.let { ErrorResponse(it) }, HttpStatus.BAD_REQUEST)
        }
    }


    @GetMapping("/auth")
    fun getAuth(request: HttpServletRequest): ResponseEntity<out Any> {
        val tokenResponse: HttpResponse<JsonNode> = Unirest.post("https://dev-c4l43o2ndcdikqar.us.auth0.com/oauth/token")
            .header("Content-Type", "application/json")
            .body("{\"client_id\":\"${dotenv["AUTH0_CLIENT_ID"]}\",\"client_secret\":\"${dotenv["AUTH0_CLIENT_SECRET"]}\",\"audience\":\"https://dev-c4l43o2ndcdikqar.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
            .asJson()
        val accessToken = tokenResponse.body.getObject().getString("access_token")
        val apiResponse: HttpResponse<JsonNode>? =  Unirest.get("https://dev-c4l43o2ndcdikqar.us.auth0.com/api/v2/users")
            .header("Authorization", "Bearer $accessToken").asJson()
        return if(apiResponse !== null){
            val apiResponseObject = apiResponse.body.array
            val users = ArrayList<User>()
            for (i in 0 until apiResponseObject.length()) {
                val user = apiResponseObject.getJSONObject(i)
                val email = user.get("email").toString()
                val name = user.get("name").toString()
                val nickName = user.get("nickname").toString()
                val userId = user.get("user_id").toString()
                users.add(User(email, name, nickName, userId))
            }
            return ResponseEntity(users, HttpStatus.OK)
        }else{
            ResponseEntity( "apI RESPONSE NULL", HttpStatus.OK)
        }
    }


}
