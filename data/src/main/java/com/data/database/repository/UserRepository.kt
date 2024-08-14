package com.data.database.repository

import com.domain.i_repository.IUserRepository
import com.domain.model.user.User

class UserRepository : IUserRepository {
    override suspend fun authentication(username: String, password: String): User {
        return User("1", "admin", "token")
    }
}