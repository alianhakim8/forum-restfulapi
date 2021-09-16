package com.alian.forum.repositories

import com.alian.forum.models.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*

interface QuestionRepository : JpaRepository<Question, Long> {
    @Query("SELECT * FROM question q WHERE q.user_id= :user_id", nativeQuery = true)
    fun getQuestionByUserId(user_id: Long): Optional<MutableList<Question>>
}