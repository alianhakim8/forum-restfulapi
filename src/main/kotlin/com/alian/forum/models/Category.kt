package com.alian.forum.models

import javax.persistence.*


@Entity
data class Category(
    @Id
    @SequenceGenerator(
        name = "category_sequence",
        sequenceName = "category_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "category_sequence"
    )
    val id: Long,
    val category: String
)
