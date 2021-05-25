package com.example.newhorizonsgraphql

import org.springframework.data.annotation.Id
import java.time.Instant

data class Mentor(
    val name: String,
    val age: Int,
    val sessions: List<Session>,
    @Id val id: String? = null
)

data class Session(
    val time: Instant,
    val secretData: String
)