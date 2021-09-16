package com.alian.forum.models

import javax.persistence.*


@Entity
data class Users(
    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "user_sequence"
    )
    val id: Long,

    @Column(unique = true)
    var username: String,

    @Column(unique = true)
    var email: String,
    val password: String,

    @Column(name = "full_name")
    var fullName: String
)
