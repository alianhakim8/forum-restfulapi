package com.alian.forum.controllers

import com.alian.forum.models.Category
import com.alian.forum.repositories.CategoryRepository
import com.alian.forum.services.CategoryService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/")
class CategoryController(repository: CategoryRepository) {

    val service = CategoryService(repository)

    @GetMapping("categories")
    fun getCategories(): MutableList<Category> = service.getAll()

    @PostMapping("category")
    fun addCategory(@RequestBody category: Category) = service.add(category)
}