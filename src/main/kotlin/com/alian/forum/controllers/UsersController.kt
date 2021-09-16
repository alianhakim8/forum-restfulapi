package com.alian.forum.controllers

import com.alian.forum.models.Users
import com.alian.forum.repositories.UserRepository
import com.alian.forum.services.UserService
import com.alian.forum.utils.request.UpdateUser
import com.alian.forum.utils.response.UserResponse
import com.alian.forum.utils.response.Response
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/")
class UsersController(repository: UserRepository) {

    private val service = UserService(repository)

    @GetMapping("users")
    fun getAllUser(): UserResponse = service.getAll()

    @GetMapping("users/{id}")
    fun getUserById(@PathVariable("id") id: Long): Users = service.getById(id)

    @PostMapping("users")
    fun addUser(@RequestBody users: Users) = service.add(users)

    @DeleteMapping("users/{id}")
    fun deleteById(@PathVariable("id") id: Long): Response = service.deleteById(id)

    @PostMapping("users/login")
    fun login(@RequestParam username: String, @RequestParam password: String): Users =
        service.login(username, password)

    @PutMapping("users/{id}")
    fun updateUser(@PathVariable("id") id: Long, @RequestBody user: UpdateUser): Response = service.update(id, user)
}