package com.alian.forum.models

import com.fasterxml.jackson.annotation.JsonFormat
import java.util.*
import javax.persistence.*

@Entity
data class Answer(
    @Id
    @SequenceGenerator(
        name = "answer_sequence",
        sequenceName = "answer_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "answer_sequence"
    )
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "question_id")
    val question: Question,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val users: Users,

    @Column(columnDefinition = "TEXT")
    val answer: String,

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_at", columnDefinition = "TIMESTAMP WITHOUT TIME ZONE")
//    val createdAt: Date
)
