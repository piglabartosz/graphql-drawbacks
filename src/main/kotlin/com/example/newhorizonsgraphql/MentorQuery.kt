package com.example.newhorizonsgraphql

import com.expediagroup.graphql.server.operations.Query
import org.springframework.stereotype.Component

@Component
class MentorQuery : Query {
    fun mentor() = Mentor("John")
}

data class Mentor(val name: String)