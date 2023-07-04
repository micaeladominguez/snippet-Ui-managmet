package com.snippetUimanagement.repos

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate


class Requests {
    companion object {
        private const val manageRepository = "http://localhost:8082/"
        private const val snippetRepository = "http://localhost:8080/"
        private const val testRepository = "http://localhost:8080/"

        fun getManageRepositories(token : String, extraUrl: String) : String?{
            return getMethod(token, extraUrl, manageRepository)
        }

        fun getSnippetRepositories(token : String, extraUrl: String): String? {
            return getMethod(token, extraUrl, snippetRepository)
        }

        fun getTestRepositories(token : String, extraUrl: String) {
            getMethod(token, extraUrl, testRepository)
        }

        fun postManageRepositories(token : String, extraUrl: String, requestBody: Any): String? {
            return postMethod(token, extraUrl, manageRepository, requestBody)
        }

        fun postSnippetRepositories(token : String, extraUrl: String, requestBody: Any): String? {
           return postMethod(token, extraUrl, snippetRepository, requestBody)
        }

        fun postTestRepositories(token : String, extraUrl: String,  requestBody: Any) {
            postMethod(token, extraUrl, testRepository, requestBody)
        }

        fun putManageRepositories(token : String, extraUrl: String, requestBody: Any) : String?{
            return putMethod(token, extraUrl, manageRepository, requestBody)
        }

        fun putSnippetRepositories(token : String, extraUrl: String, requestBody: Any) : String?{
            println(requestBody)
            return putMethod(token, extraUrl, snippetRepository, requestBody)
        }

        fun putTestRepositories(token : String, extraUrl: String,  requestBody: Any) {
            putMethod(token, extraUrl, testRepository, requestBody)
        }

        private fun getMethod(token : String, extraUrl: String, uri: String): String?{
            val headers = HttpHeaders()
            headers.set("Authorization", token)
            val entity = HttpEntity<String>(headers)
            val restTemplate = RestTemplate()
            val response = restTemplate.exchange("${uri}${extraUrl}", HttpMethod.GET, entity, String::class.java)
            println("RESPONSE ${response.body}")
            return response.body
        }

        private fun postMethod(token : String, extraUrl: String, uri: String, requestBody: Any): String? {
            val headers = HttpHeaders()
            headers.set("Authorization", token)
            val entity = HttpEntity(requestBody,headers)
            val restTemplate = RestTemplate()
            val response = restTemplate.postForEntity("${uri}${extraUrl}", entity, String::class.java)
            println("RESPONSE $response")
            return response.body
        }

        private fun putMethod(token : String, extraUrl: String, uri: String, requestBody: Any): String?{
            val headers = HttpHeaders()
            headers.set("Authorization", token)
            val entity = HttpEntity(requestBody,headers)
            val restTemplate = RestTemplate()
            val response = restTemplate.exchange("${uri}${extraUrl}", HttpMethod.PUT, entity, String::class.java)
            println("RESPONSE $response")
            return response.body
        }
    }

}