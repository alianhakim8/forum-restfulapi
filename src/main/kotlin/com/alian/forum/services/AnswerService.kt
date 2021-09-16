package com.alian.forum.services

import com.alian.forum.models.Answer
import com.alian.forum.repositories.AnswerRepository
import com.alian.forum.utils.response.Response
import javassist.NotFoundException

class AnswerService(private val repository: AnswerRepository) {

    fun addQuestion(answer: Answer): Response {
        return if (answer.answer.isNotEmpty()) {
            repository.save(answer)
            Response(true, "answer successfully added")
        } else {
            Response(false, "answer failed to added")
        }
    }

    fun getAnswerByQuestionId(id: Long): MutableList<Answer> {
        val result = repository.getAnswerByQuestionId(id)
        if (result.isPresent) {
            return result.get()
        } else {
            throw NotFoundException("answer with question_id $id not found")
        }
    }

    fun deleteAnswerById(id: Long): Response {
        val result = repository.findById(id)
        return if (result.isPresent) {
            repository.deleteById(id)
            Response(true, "successfully deleted")
        } else {
            throw NotFoundException("answer with $id not found")
        }
    }

    fun getAnswerCount(id: Long): Int {
        return repository.getCountAnswer(id)
    }
}