package com.example.newhorizonsgraphql

import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class MentorQuery : Query {
    fun mentor() = Mentor(
        name = "John",
        sessions = listOf(
            Session(
                time = Instant.now(),
                secretData = "secret"
            )
        )
    )
}

data class Mentor(
    val name: String,
    val sessions: List<Session>
)

data class Session(
    val time: Instant,
    val secretData: String
)