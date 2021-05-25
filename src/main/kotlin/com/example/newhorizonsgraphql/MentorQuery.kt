package com.example.newhorizonsgraphql

import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component
import java.time.ZonedDateTime

@Component
class MentorQuery : Query {
    private val mentors = listOf(
        Mentor(
            name = "John",
            age = 18,
            sessions = listOf(
                Session(
                    time = ZonedDateTime.now(),
                    secretData = "secret"
                )
            )
        )
    )

    fun mentor(maxAge: Int): List<Mentor> =
        mentors.filter { it.age <= maxAge }
}

data class Mentor(
    val name: String,
    val age: Int,
    val sessions: List<Session>
)

data class Session(
    val time: ZonedDateTime,
    val secretData: String
)