package com.snippetUimanagement.repos

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.web.client.RestTemplate


class Requests {
    companion object {
        private const val manageRepository = "services/manage-repositories"
        //private const val manageRepository = "http://localhost:8082/"

        private const val snippetRepository = "services/management-service"
        //private const val snippetRepository = "http://localhost:8083/"

        private const val testRepository = "services/testing-scripts"

        //private const val testRepository = "http://localhost:8081/"



        fun getManageRepositories(token : String, extraUrl: String, url: String) : String?{
            return getMethod(token, extraUrl, manageRepository, url)
        }

        fun getSnippetRepositories(token : String, extraUrl: String, url: String): String? {
            return getMethod(token, extraUrl, snippetRepository, url)
        }


        fun postManageRepositories(token : String, extraUrl: String, requestBody: Any,  url: String): String? {
            return postMethod(token, extraUrl, manageRepository, requestBody, url)
        }

        fun postSnippetRepositories(token : String, extraUrl: String, requestBody: Any, url: String): String? {
           return postMethod(token, extraUrl, snippetRepository, requestBody, url)
        }

        fun postTestRepositories(token : String, extraUrl: String,  requestBody: Any, url: String): String? {
            return postMethod(token, extraUrl, testRepository, requestBody, url)
        }

        fun putManageRepositories(token : String, extraUrl: String, requestBody: Any, url: String) : String?{
            return putMethod(token, extraUrl, manageRepository, requestBody, url)
        }

        fun putSnippetRepositories(token : String, extraUrl: String, requestBody: Any, url: String) : String?{
            return putMethod(token, extraUrl, snippetRepository, requestBody, url)
        }


        private fun getMethod(token : String, extraUrl: String, uri: String, url: String): String?{
            val headers = HttpHeaders()
            headers.set("Authorization", token)
            val entity = HttpEntity<String>(headers)
            val restTemplate = RestTemplate()
            println("${uri}${extraUrl}")
            val response = restTemplate.exchange("${url}${uri}${extraUrl}", HttpMethod.GET, entity, String::class.java)
            println("RESPONSE ${response.body}")
            return response.body
        }

        private fun postMethod(token : String, extraUrl: String, uri: String, requestBody: Any, url: String): String? {
            val headers = HttpHeaders()
            headers.set("Authorization", token)
            val entity = HttpEntity(requestBody,headers)
            println("ENTITY $entity")
            val restTemplate = RestTemplate()
            println("URI " + "${url}${uri}${extraUrl}")
            val response = restTemplate.postForEntity("${url}${uri}${extraUrl}", entity, String::class.java)
            println("RESPONSE $response")
            return response.body
        }

        private fun putMethod(token : String, extraUrl: String, uri: String, requestBody: Any, url:String ): String?{
            val headers = HttpHeaders()
            headers.set("Authorization", token)
            val entity = HttpEntity(requestBody,headers)
            val restTemplate = RestTemplate()
            println("${url}${uri}${extraUrl}")
            val response = restTemplate.exchange("${url}${uri}${extraUrl}", HttpMethod.PUT, entity, String::class.java)
            println("RESPONSE $response")
            return response.body
        }
    }

}