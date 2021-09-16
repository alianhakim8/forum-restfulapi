package com.alian.forum.controllers

import com.alian.forum.models.Question
import com.alian.forum.repositories.QuestionRepository
import com.alian.forum.services.QuestionService
import com.alian.forum.utils.request.UpdateQuestion
import com.alian.forum.utils.response.Response
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/v1/")
class QuestionController(repository: QuestionRepository) {

    private val service = QuestionService(repository)

    @PostMapping("question")
    fun addQuestion(@RequestBody question: Question): Response = service.add(question)

    @GetMapping("questions")
    fun getALlQuestion(): MutableList<Question> = service.getAll()

    @GetMapping("question/{user_id}")
    fun getByUserId(@PathVariable("user_id") user_id: Long): MutableList<Question> =
        service.getQuestionByUserId(user_id)

    @GetMapping("user/question/{id}")
    fun getQuestionById(@PathVariable("id") id: Long): Question = service.getQuestionById(id)

    @DeleteMapping("question/{id}")
    fun deleteQuestionById(@PathVariable("id") id: Long): Response = service.deleteById(id)

    @PutMapping("question/{id}")
    fun updateQuestionById(@PathVariable("id") id: Long, @RequestBody question: UpdateQuestion): Response =
        service.updateById(id, question)
}