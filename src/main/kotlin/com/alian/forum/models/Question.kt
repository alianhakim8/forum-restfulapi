package com.alian.forum.models

import javax.persistence.*

@Entity
data class Question(

    @Id
    @SequenceGenerator(
        name = "question_sequence",
        sequenceName = "question_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "question_sequence"
    )
    val id: Long,

    @ManyToOne
    @JoinColumn(name = "user_id")
    val users: Users,

//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    val category: Category,

    @Column(nullable = false)
    var question: String,

    @Column(name = "question_desc", columnDefinition = "TEXT", nullable = false)
    var questionDesc: String,

//    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss.SSS")
//    @Temporal(TemporalType.TIMESTAMP)
//    @Column(name = "created_at", columnDefinition = "TIMESTAMP")
//    @CreationTimestamp
//    val createdAt: Date
)
