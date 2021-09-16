package com.alian.forum.services

import com.alian.forum.models.Question
import com.alian.forum.utils.request.UpdateQuestion
import com.alian.forum.repositories.QuestionRepository
import com.alian.forum.utils.response.Response

class QuestionService(private val repository: QuestionRepository) {
    fun add(question: Question): Response {
        repository.save(question)
        return Response(true, "Successfully added")
    }

    fun getAll(): MutableList<Question> {
        val result = repository.findAll()
        return if (result.isEmpty()) {
            throw IllegalArgumentException("no question")
        } else {
            result
        }
    }

    fun getQuestionByUserId(user_id: Long): MutableList<Question> {
        val result = repository.getQuestionByUserId(user_id)
        return if (result.isPresent) {
            result.get()
        } else {
            throw IllegalArgumentException("no question")
        }
    }

    fun getQuestionById(id: Long): Question {
        val result = repository.getById(id)
        return result
    }

    fun deleteById(id: Long): Response {
        val result = repository.findById(id)
        return if (result.isPresent) {
            repository.deleteById(id)
            Response(true, "question with $id deleted")
        } else {
            throw IllegalArgumentException("question with $id not exists")
        }
    }

    fun updateById(id: Long, question: UpdateQuestion): Response {
        val result = repository.findById(id)
        return if (result.isPresent) {
            if (question.question.isNotEmpty() && question.question != result.get().question) {
                result.get().question = question.question
            }

            if (question.questionDesc.isNotEmpty() && question.questionDesc != result.get().questionDesc) {
                result.get().questionDesc = question.questionDesc
            }

            val new = Question(result.get().id, result.get().users, question.question, question.questionDesc)
            repository.save(new)
            Response(true, "Updated Success")
        } else {
            throw IllegalArgumentException("question with $id not exists")
        }
    }
}