package com.example.growthtrack.api

import com.example.growthtrack.pindahan.Article
import com.example.growthtrack.pindahan.ArticleResponse
import com.example.growthtrack.pindahan.LoginResponse
import com.example.growthtrack.pindahan.SignInRequest
import com.example.growthtrack.pindahan.SignUpRequest
import com.example.growthtrack.pindahan.SignUpResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("predictionHistory/")
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJUazVKRkZMNkpoaFlucGtZQ0tJUFFzNm1PaEozIiwiZW1haWwiOiJ5YXNpcmh1ZGFAZ21haWwuY29tIiwiaWF0IjoxNzE4NzAyNTMzLCJleHAiOjE3MTg3MDYxMzN9.7ahvcAz3FkEy5HEAc9Z2h2OuChjJpEstlQvaUtZpvD4")
    fun getHistory():Call<Response>


    @POST("predict")
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJUazVKRkZMNkpoaFlucGtZQ0tJUFFzNm1PaEozIiwiZW1haWwiOiJ5YXNpcmh1ZGFAZ21haWwuY29tIiwiaWF0IjoxNzE4NzAyNTMzLCJleHAiOjE3MTg3MDYxMzN9.7ahvcAz3FkEy5HEAc9Z2h2OuChjJpEstlQvaUtZpvD4")
    fun postpredict(
        @Body request: PredictRequest
    ):Call<Postpredict>

    @GET("users/{id}")
    @Headers("Authorization: eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1aWQiOiJUazVKRkZMNkpoaFlucGtZQ0tJUFFzNm1PaEozIiwiZW1haWwiOiJ5YXNpcmh1ZGFAZ21haWwuY29tIiwiaWF0IjoxNzE4NzAyNTMzLCJleHAiOjE3MTg3MDYxMzN9.7ahvcAz3FkEy5HEAc9Z2h2OuChjJpEstlQvaUtZpvD4")
    fun getuser(
        @Path("id") id: String
    ): Call<UsersResponse>
    @FormUrlEncoded
    @POST("resetpassword")
    fun sendEmail(
        @Field("email") email: String
    ): Call<ResetRespon>

    @POST("register")
    suspend fun register(
        @Body register: SignUpRequest
    ): SignUpResponse

    @POST("login")
    suspend fun login(
        @Body loginRequest: SignInRequest
    ): LoginResponse

    @GET("articles")
    suspend fun getAllArticles(): ArticleResponse

    @GET("articles/{uid}")
    suspend fun getArticleByUID(
        @Path("uid") uid: String
    ): Article

    @GET("article")
    suspend fun getArticle(
        @Header("Authorization") uid: String,
        @Query("page") page: Int = 1,
        @Query("size") size: Int = 20,
    ): ArticleResponse
}