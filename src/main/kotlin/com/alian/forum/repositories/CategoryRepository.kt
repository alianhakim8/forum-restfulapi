package com.alian.forum.repositories

import com.alian.forum.models.Category
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface CategoryRepository : JpaRepository<Category, Long> {
    @Query("SELECT c FROM Category c WHERE c.category=?1")
    fun findByCategoryName(category: String): Optional<Category>
}