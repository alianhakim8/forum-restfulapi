package com.alian.forum.repositories

import com.alian.forum.models.Answer
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface AnswerRepository : JpaRepository<Answer, Long> {
    @Query("SELECT * FROM answer a WHERE a.question_id=:questionId", nativeQuery = true)
    fun getAnswerByQuestionId(questionId: Long): Optional<MutableList<Answer>>

    @Query("SELECT COUNT(*) FROM answer a WHERE a.question_id=:questionId", nativeQuery = true)
    fun getCountAnswer(questionId: Long): Int

}