package com.example.picpaychallenge.Model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id")
    val id:Int,
    @SerializedName("name")
    val name:String,
    @SerializedName("img")
    val img:String,
    @SerializedName("username")
    val username:String)
