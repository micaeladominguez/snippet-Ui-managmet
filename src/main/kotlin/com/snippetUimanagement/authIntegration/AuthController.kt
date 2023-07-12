package com.snippetUimanagement.authIntegration


import com.snippetUimanagement.classes.User
import io.github.cdimascio.dotenv.Dotenv
import io.github.cdimascio.dotenv.dotenv
import kong.unirest.HttpResponse
import kong.unirest.JsonNode
import kong.unirest.Unirest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

val dotenv: Dotenv = dotenv()

@RestController
@RequestMapping("/auth")
class AuthController() {
    @GetMapping
    fun getAuth(): ResponseEntity<out Any> {
        val tokenResponse: HttpResponse<JsonNode> = Unirest.post("https://dev-c4l43o2ndcdikqar.us.auth0.com/oauth/token")
            .header("Content-Type", "application/json")
            .body("{\"client_id\":\"${dotenv["AUTH_CLIENT_ID"]}\",\"client_secret\":\"${dotenv["AUTH_CLIENT_SECRET"]}\",\"audience\":\"https://dev-c4l43o2ndcdikqar.us.auth0.com/api/v2/\",\"grant_type\":\"client_credentials\"}")
            .asJson()
        val accessToken = tokenResponse.body.getObject().getString("access_token")
        println("Access token  $accessToken")
        val apiResponse: HttpResponse<JsonNode>? =  Unirest.get("https://dev-c4l43o2ndcdikqar.us.auth0.com/api/v2/users")
            .header("Authorization", "Bearer $accessToken").asJson()
        return if(apiResponse !== null){
            val apiResponseObject = apiResponse.body.array
            println("Api response to string $apiResponseObject")
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