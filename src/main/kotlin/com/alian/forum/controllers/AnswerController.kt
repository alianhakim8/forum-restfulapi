package com.alian.forum.controllers

import com.alian.forum.models.Answer
import com.alian.forum.repositories.AnswerRepository
import com.alian.forum.services.AnswerService
import com.alian.forum.utils.response.Response
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/question/answer")
class AnswerController(repository: AnswerRepository) {
    private val service = AnswerService(repository)

    @PostMapping
    fun add(@RequestBody answer: Answer): Response = service.addQuestion(answer)

    @GetMapping("/{question_id}")
    fun getById(@PathVariable("question_id") id: Long): MutableList<Answer> = service.getAnswerByQuestionId(id)

    @DeleteMapping("/{answer_id}")
    fun deleteById(@PathVariable("answer_id") id: Long): Response = service.deleteAnswerById(id)

    @GetMapping("/count/{question_id}")
    fun getAnswerCount(@PathVariable("question_id") id: Long): Int = service.getAnswerCount(id)
}