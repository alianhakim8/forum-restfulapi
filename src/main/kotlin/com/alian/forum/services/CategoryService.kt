package com.alian.forum.services

import com.alian.forum.repositories.CategoryRepository
import com.alian.forum.models.Category
import com.alian.forum.utils.response.Response

class CategoryService(private val repository: CategoryRepository) {

    fun getAll(): MutableList<Category> {
        return repository.findAll()
    }

    fun add(category: Category): Response {
        val result = repository.findByCategoryName(category.category)
        return if (result.isEmpty) {
            repository.save(category)
            Response(true, "${category.category} added to category")
        } else {
            Response(true, "${category.category} already exists")
        }
    }
}