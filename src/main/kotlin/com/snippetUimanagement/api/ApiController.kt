package com.snippetUimanagement.api

import com.snippetUimanagement.snippet.saveSnippet.SaveSnippet.Companion.saveSnippet
import com.snippetUimanagement.snippet.getAllSnippets.GetAllSnippets.Companion.getAllSnippets

import com.snippetUimanagement.repos.dto.SnippetCreateDTO
import com.snippetUimanagement.repos.dto.SnippetRoleUpdateDTO
import com.snippetUimanagement.snippet.format.Format.Companion.format
import com.snippetUimanagement.snippet.getASnippet.GetASnippet.Companion.getASnippet
import com.snippetUimanagement.snippet.interpreter.Interpreter.Companion.interpreter
import com.snippetUimanagement.snippet.linter.Linter.Companion.lint
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


}