package com.alian.forum.services

import com.alian.forum.models.Users
import com.alian.forum.repositories.UserRepository
import com.alian.forum.utils.request.UpdateUser
import com.alian.forum.utils.response.UserResponse
import com.alian.forum.utils.response.Response
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

class UserService(private val repository: UserRepository) {

    fun add(users: Users): Response {
        return if (repository.findById(users.id).isEmpty) {
            val passwordEncoder = BCryptPasswordEncoder()
            val hashedPassword = passwordEncoder.encode(users.password)
            val newUser = Users(users.id, users.username, users.email, hashedPassword, users.fullName)
            repository.save(newUser)
            Response(true, "Successfully added")
        } else {
            Response(true, "users already exists")
        }
    }

    fun getAll(): UserResponse {
        val users = repository.findAll()
        val size = users.size
        return UserResponse(true, size, users)
    }

    fun getById(id: Long): Users {
        val user = repository.findById(id)
        return if (user.isPresent) {
            user.get()
        } else {
            throw IllegalArgumentException("user with id $id not found")
        }
    }

    fun deleteById(id: Long): Response {
        val user = repository.findById(id)
        return if (user.isPresent) {
            repository.deleteById(id)
            Response(true, "user has been deleted")
        } else {
            Response(false, "user with id $id not found")
        }
    }

    fun update(id: Long, user: UpdateUser): Response {
        val userFromDb = repository.findById(id)
        return if (userFromDb.isPresent) {
            if (user.email != null && user.email != userFromDb.get().email) {
                userFromDb.get().email = user.email.toString()
            }

            if (user.username != null && user.username != userFromDb.get().username) {
                userFromDb.get().username = user.username
            }

            if (user.fullName != null && user.fullName != userFromDb.get().fullName) {
                userFromDb.get().fullName = user.fullName
            }
            repository.save(userFromDb.get())
            Response(true, "Successfully update")
        } else {
            Response(false, "User not exists")
        }
    }

    fun login(username: String, password: String): Users {
        val user = repository.findByUsername(username)
        return if (user.isPresent) {
            val encoder = BCryptPasswordEncoder()
            val passwordFromDb = user.get().password
            if (encoder.matches(password, passwordFromDb)) {
                Users(user.get().id, user.get().username, user.get().email, user.get().password, user.get().fullName)
            } else {
                throw IllegalArgumentException("password incorrect")
            }
        } else {
            throw IllegalArgumentException("user not exists")
        }
    }
}