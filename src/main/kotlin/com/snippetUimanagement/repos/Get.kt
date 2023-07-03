package com.snippetUimanagement.repos

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.web.client.RestTemplate


class Get {
    fun get() {
        val headers = HttpHeaders()
        headers.set("Authorization", "Bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6IlJYTmxaRmhiMl9OSWxTSUxCcVZhVSJ9.eyJuaWNrbmFtZSI6Im1pY2Fkb205IiwibmFtZSI6Im1pY2Fkb205QGdtYWlsLmNvbSIsInBpY3R1cmUiOiJodHRwczovL3MuZ3JhdmF0YXIuY29tL2F2YXRhci82Y2U1NjMxNDI0OWE5MTkyOTI5YmMxN2YzNmQzOTIwMT9zPTQ4MCZyPXBnJmQ9aHR0cHMlM0ElMkYlMkZjZG4uYXV0aDAuY29tJTJGYXZhdGFycyUyRm1pLnBuZyIsInVwZGF0ZWRfYXQiOiIyMDIzLTA3LTAzVDE5OjE0OjA5Ljk2NVoiLCJlbWFpbCI6Im1pY2Fkb205QGdtYWlsLmNvbSIsImVtYWlsX3ZlcmlmaWVkIjp0cnVlLCJpc3MiOiJodHRwczovL2Rldi1jNGw0M28ybmRjZGlrcWFyLnVzLmF1dGgwLmNvbS8iLCJhdWQiOiIxRTRiSHdHakRzR2hna0xIY3JsazB4ZW1yZXVTWHBuZCIsImlhdCI6MTY4ODQyNDMyNiwiZXhwIjoxNjg4NDYwMzI2LCJzdWIiOiJhdXRoMHw2NGEyZGU3M2E2MTVlNWM5YjFlMGE3OWIiLCJzaWQiOiJZZVVTLWFtd2NmUjhpeUMxdTY0dnpxWkVpQkN5Q0hpSCIsIm5vbmNlIjoibDhoWjh3ZlZSOGhMV1JOdEFxWlhLNm1iU0RBVi1SeWtzejlycUJxaGhaayJ9.iZdYAiyJjw57HqZ2_6fQQ81OOTJFprYesd99p6m3Iv6QWwTsZj-MwmggSOFMdvvZ9zafsB4UTHG8hEU7K8ZqHMdZIXPsjpz18FlRQ4pkw7fC206dBvbD3ze0XORxcH2h51Lqj0dTnxTWCNZxgsRApM84jfOOcOfGzIa_4b7OolZXpNVVfWD6ahq8J_QHLkxvEpr8VMvC3Wsp1lGQt9qxZCXO22cDJaPY-tnBzAEI5dQcJo9sYnAEdra0UAsgQH63Y4u3qyBfxa4yuRaBTbTzD0PVY4cPnbRZpUec9Ocqa4KDPSYsIexPQdvzGBKzyRcm9z86jlaS-FxiJ4OYQeoXgg")
        val entity = HttpEntity<String>(headers)
        val restTemplate = RestTemplate()
        val fooResourceUrl = "https://snippetsps-dev.ddns.net/api/repos/role"
        val response = restTemplate.exchange("$fooResourceUrl", HttpMethod.GET, entity, String::class.java)
        println("RESPONSE $response")
    }

    fun post(){

    }
}