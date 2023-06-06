package com.snippetUimanagement.authIntegration

import kong.unirest.GetRequest
import kong.unirest.HttpResponse
import kong.unirest.JsonNode
import kong.unirest.Unirest
import kong.unirest.json.JSONObject
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/auth")
class AuthController {
    @GetMapping
    fun getAuth(): Any{

        val tokenResponse: HttpResponse<JsonNode> = Unirest.post("https://dev-c4l43o2ndcdikqar.us.auth0.com/oauth/token")
            .header("Content-Type", "application/json")
            .body("{\"client_id\":\"vC01XXLywtCpGacKDL4Eh8mftU7FWXGo\",\"client_secret\":\"BmRroNocMjGOeae1EpqYXXoDqy4b6r5hcUndq9IgQQ3RDqEZUoPbzcPVm-GCEG-r\",\"audience\":\"https://dev-c4l43o2ndcdikqar.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
            .asJson()
        val accessToken = tokenResponse.body.getObject().getString("access_token")
        println("Access token  $accessToken ")
        val apiResponse: HttpResponse<JsonNode>? =  Unirest.get("https://dev-c4l43o2ndcdikqar.us.auth0.com/api/v2/users")
            .header("Authorization", "Bearer $accessToken").asJson()
        return if(apiResponse !== null){
            val apiResponseObject = apiResponse.body.array
            println("Api response to string $apiResponseObject")
            for (i in 0 until apiResponseObject.length()) {
                val user = apiResponseObject.getJSONObject(i)
                println(user)
            }
        }else{
            "apI RESPONSE NULL"
        }

    }
}