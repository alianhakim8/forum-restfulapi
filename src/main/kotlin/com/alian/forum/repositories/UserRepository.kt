package com.alian.forum.repositories

import com.alian.forum.models.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface UserRepository : JpaRepository<Users, Long> {
    @Query("SELECT u FROM Users u WHERE u.username=?1")
    fun findByUsername(username: String): Optional<Users>
}