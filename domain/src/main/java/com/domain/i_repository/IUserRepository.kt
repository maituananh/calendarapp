package com.domain.i_repository

import com.domain.model.user.User

interface IUserRepository {
    suspend fun authentication(username: String, password: String): User
}