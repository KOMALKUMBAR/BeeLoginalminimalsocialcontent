package com.chat.beelogincompanyassigment.data.models


data class Post(
    val userName: String,
    val timestamp: String,
    val location: String,
    val imageRes: Int,
    val title: String,
    val description: String,
    var likes: Int,
    var comments: Int,
    var isLiked: Boolean = false
)