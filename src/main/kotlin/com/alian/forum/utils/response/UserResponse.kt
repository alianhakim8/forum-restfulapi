package com.alian.forum.utils.response

import com.alian.forum.models.Users

data class UserResponse(
    val status: Boolean,
    val count: Int,
    val users: MutableList<Users>
)
