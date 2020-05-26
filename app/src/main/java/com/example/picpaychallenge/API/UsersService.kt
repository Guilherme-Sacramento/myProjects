package com.example.picpaychallenge.API

import com.example.picpaychallenge.Model.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET

public interface UsersService {

    @GET("users")
    fun getUsers(): Call<MutableList<User>>
}